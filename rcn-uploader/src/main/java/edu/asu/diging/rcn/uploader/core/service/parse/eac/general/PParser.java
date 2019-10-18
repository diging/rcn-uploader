package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;

@Component
public class PParser implements BiogHistTagParser, FunctionsTagParser {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String handlesTag() {
        return "p";
    }

    @Override
    public void parse(Node node, BiogHist bio) {
        bio.getPs().add(getPText(node));
    }
    
    @Override
    public void parse(Node node, Functions functions) {
        functions.getPs().add(getPText(node));
    }

    private String getPText(Node node) throws TransformerFactoryConfigurationError {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            logger.error("Could not create transformer to read abstract.", e);
            return "";
        }
        StringWriter buffer = new StringWriter();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        
        NodeList children = node.getChildNodes();
        if (children.getLength() > 0) {
            for (int i = 0; i<children.getLength(); i++) {
                Node child = children.item(i);
                try {
                    transformer.transform(new DOMSource(child),
                          new StreamResult(buffer));
                } catch (TransformerException e) {
                    logger.error("Could not extract abstract tag content.", e);
                    continue;
                }
            }
        }
        
        return buffer.toString();
    }

}
