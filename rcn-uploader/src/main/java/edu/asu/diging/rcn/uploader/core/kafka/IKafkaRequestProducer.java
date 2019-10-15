package edu.asu.diging.rcn.uploader.core.kafka;

import edu.asu.diging.rcn.kafka.messages.model.KafkaJobReturnMessage;
import edu.asu.diging.rcn.uploader.core.exception.MessageCreationException;

public interface IKafkaRequestProducer {

    /* (non-Javadoc)
     * @see edu.asu.giles.service.kafka.impl.IOCRRequestProducer#sendOCRRequest(java.lang.String)
     */
    void sendRequest(KafkaJobReturnMessage msg, String topic) throws MessageCreationException;

}