package edu.asu.diging.rcn.uploader.core.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.asu.diging.rcn.uploader.core.exception.AuthmatcherCommunicationException;
import edu.asu.diging.rcn.uploader.core.service.IRcnMatcherConnector;

@Service
@PropertySource("classpath:/config.properties")
public class RcnMatcherConnector implements IRcnMatcherConnector {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Value("${_rcnmatcher_base_uri}")
    private String rcnmatcherBaseUri;
    
    @Value("${_rcnmatcher_client_key}")
    private String rcnmatcherClientKey;
    
    @Value("${_rcnmatcher_client_secret}")
    private String rcnmatcherClientSecret;
    
    @Value("${_rcnmatcher_download_path}")
    private String downloadPath;
    
    private RestTemplate restTemplate;
    
    private String accessToken;
    
    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        if (!rcnmatcherBaseUri.endsWith("/")) {
            rcnmatcherBaseUri += "/";
        }
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(rcnmatcherBaseUri));
        ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler() {

            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus status = response.getStatusCode();
                if (HttpStatus.Series.valueOf(status) == HttpStatus.Series.SUCCESSFUL || status == HttpStatus.UNAUTHORIZED) {
                    return false;
                }
                return true;
            }
            
        };
        restTemplate.setErrorHandler(errorHandler);
    }


    
    /* (non-Javadoc)
     * @see edu.asu.diging.citesphere.importer.core.service.impl.ICitesphereConnector#getUploadeFile(java.lang.String)
     */
    @Override
    public String getUploadeFile(String jobId) throws AuthmatcherCommunicationException {
        
        ResponseExtractor<Map<HttpStatus, String>> responseExtractor = response -> {
            if(response.getStatusCode() == HttpStatus.OK) {
                String disposition = response.getHeaders().getFirst("Content-Disposition");
                String fileName = disposition.replaceFirst("(?i)^.*filename=\"?([^\"]+)\"?.*$", "$1");
                
                File downloadFolder = new File(downloadPath + File.separator + "UP" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMdd-HHmmss")) + "-" + UUID.randomUUID().toString().hashCode());
                downloadFolder.mkdirs();
                String filePath = downloadFolder.getAbsolutePath() + File.separator + fileName;
                Path path = Paths.get(filePath);
                Files.copy(response.getBody(), path);
                return new HashMap<HttpStatus, String>() {
                    {
                        put(response.getStatusCode(), filePath);
                    }
                };
            }
            return new HashMap<HttpStatus, String>() {
                {
                    put(response.getStatusCode(), IOUtils.toString(response.getBody(), StandardCharsets.UTF_8.name()));
                }
            };
        };
        
        RequestCallback callback = new RequestCallback() {
            
            @Override
            public void doWithRequest(ClientHttpRequest request) throws IOException {
                if (accessToken == null) {
                    accessToken = getAccessToken();
                }
                
                HttpHeaders headers = request.getHeaders();
                headers.setBearerAuth(accessToken);
            }
        };
        
        Map<HttpStatus, String> response;
        try {
            response = restTemplate.execute("api/v1/upload/" + jobId, HttpMethod.GET, callback, responseExtractor);
        } catch (RestClientException ex) {
            throw new AuthmatcherCommunicationException("Could not understand server.", ex);
        }
        
        if (response.keySet().contains(HttpStatus.UNAUTHORIZED)) {
            String responseBody = response.get(HttpStatus.UNAUTHORIZED).toString();
            if (!refreshToken(responseBody)) {
                throw new AuthmatcherCommunicationException("Could not understand returned error message: " + responseBody);
            }
            
            // let's try again after getting a new OAuth token
            response = restTemplate.execute("api/v1/upload/" + jobId, HttpMethod.GET, callback, responseExtractor);
        }
        
        String filepath = null;
        if (response.keySet().contains(HttpStatus.OK)) {
            filepath = response.get(HttpStatus.OK);
        } else {
            throw new AuthmatcherCommunicationException("Could not communicate with RCN Authmatcher properly. Got " + response.keySet());
        }
        return filepath;
    }
    
//    private ResponseEntity<?> makeApiCall(String url, String apiToken, Class<?> responseType) throws AuthmatcherCommunicationException {
//        HttpEntity<String> entity = buildHeaders(apiToken);
//        ResponseEntity<?> response;
//        try {
//            response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
//        } catch (RestClientException ex) {
//            throw new AuthmatcherCommunicationException("Could not understand server.", ex);
//        }
//        HttpStatus status = response.getStatusCode();
//        
//        if (status == HttpStatus.UNAUTHORIZED) {
//            String responseBody = response.getBody().toString();
//            if (!refreshToken(responseBody)) {
//                throw new AuthmatcherCommunicationException("Could not understand returned error message: " + responseBody);
//            }
//            
//            // let's try again after getting a new OAuth token
//            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        }
//        return response;
//    }

//    private HttpEntity<String> buildHeaders(String apiToken) {
//        if (accessToken == null) {
//            accessToken = getAccessToken();
//        }
//        
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(accessToken);
//        HttpEntity<String> entity = new HttpEntity<>("body", headers);
//        return entity;
//    }
    
    protected String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(rcnmatcherClientKey, rcnmatcherClientSecret);
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        // not working? FIXME
        ResponseEntity<String> response = restTemplate.exchange("api/v1/oauth/token?grant_type=client_credentials", HttpMethod.POST, entity, String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(response.getBody());
        } catch (IOException e) {
            logger.error("Could not read JSON message.", e);
            return null;
        }
        
        return node.get("access_token").asText();
    }
    
    private boolean refreshToken(String errorMessage) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(errorMessage);
        } catch (IOException e) {
            logger.error("Could not read JSON message.", e);
            return false;
        }
        
        if (node.get("error") != null && node.hasNonNull("error") && node.get("error").asText().equals("invalid_token")) {
            accessToken = getAccessToken();
            return true;
        }
        
        return false;
    }
}