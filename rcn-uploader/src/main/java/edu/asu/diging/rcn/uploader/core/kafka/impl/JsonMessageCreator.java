package edu.asu.diging.rcn.uploader.core.kafka.impl;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.asu.diging.rcn.kafka.messages.model.KafkaJobReturnMessage;
import edu.asu.diging.rcn.uploader.core.exception.MessageCreationException;
import edu.asu.diging.rcn.uploader.core.kafka.IJsonMessageCreator;

@Component
public class JsonMessageCreator implements IJsonMessageCreator {

    /* (non-Javadoc)
     * @see edu.asu.giles.service.kafka.impl.IJsonMessageCreator#createMessage(edu.asu.giles.service.requests.IRequest)
     */
    @Override
    public String createMessage(KafkaJobReturnMessage msg) throws MessageCreationException {
        ObjectMapper mapper = new ObjectMapper(); 
        try {
            return mapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            throw new MessageCreationException("Could not create JSON.", e);
        }
    }
}