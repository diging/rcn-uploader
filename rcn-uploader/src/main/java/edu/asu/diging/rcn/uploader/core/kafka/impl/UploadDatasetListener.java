package edu.asu.diging.rcn.uploader.core.kafka.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.asu.diging.rcn.kafka.messages.KafkaTopics;
import edu.asu.diging.rcn.kafka.messages.model.KafkaJobMessage;
import edu.asu.diging.rcn.uploader.core.service.IDatasetProcessor;

public class UploadDatasetListener {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IDatasetProcessor datasetProcessor;
    
    @KafkaListener(topics = KafkaTopics.UPLOAD_DATASET_TOPIC)
    public void receiveMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        KafkaJobMessage msg = null;
        try {
            msg = mapper.readValue(message, KafkaJobMessage.class);
        } catch (IOException e) {
            logger.error("Could not unmarshall message.", e);
            // FIXME: handle this case
            return;
        }
        
        datasetProcessor.process(msg);
    }
}