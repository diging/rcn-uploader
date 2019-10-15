package edu.asu.diging.rcn.uploader.core.kafka;
import edu.asu.diging.rcn.kafka.messages.model.KafkaJobReturnMessage;
import edu.asu.diging.rcn.uploader.core.exception.MessageCreationException;

public interface IJsonMessageCreator {

    public String createMessage(KafkaJobReturnMessage ms) throws MessageCreationException;
}