package edu.asu.diging.rcn.uploader.core.service;

import edu.asu.diging.rcn.kafka.messages.model.KafkaJobMessage;

public interface IDatasetProcessor {

    void process(KafkaJobMessage msg);

}