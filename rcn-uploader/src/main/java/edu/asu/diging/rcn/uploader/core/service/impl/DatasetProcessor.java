package edu.asu.diging.rcn.uploader.core.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.asu.diging.eaccpf.data.DatasetRepository;
import edu.asu.diging.eaccpf.data.RecordRepository;
import edu.asu.diging.eaccpf.model.Dataset;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.DatasetImpl;
import edu.asu.diging.eaccpf.model.impl.RecordImpl;
import edu.asu.diging.rcn.kafka.messages.KafkaTopics;
import edu.asu.diging.rcn.kafka.messages.model.JobStatus;
import edu.asu.diging.rcn.kafka.messages.model.KafkaJobMessage;
import edu.asu.diging.rcn.kafka.messages.model.KafkaJobReturnMessage;
import edu.asu.diging.rcn.uploader.core.exception.AuthmatcherCommunicationException;
import edu.asu.diging.rcn.uploader.core.exception.IteratorCreationException;
import edu.asu.diging.rcn.uploader.core.exception.MessageCreationException;
import edu.asu.diging.rcn.uploader.core.kafka.IKafkaRequestProducer;
import edu.asu.diging.rcn.uploader.core.service.IDatasetProcessor;
import edu.asu.diging.rcn.uploader.core.service.IHandlerRegistry;
import edu.asu.diging.rcn.uploader.core.service.IRcnMatcherConnector;
import edu.asu.diging.rcn.uploader.core.service.RecordIterator;

@Service
@Transactional
public class DatasetProcessor implements IDatasetProcessor {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IRcnMatcherConnector connector;
    
    @Autowired
    private IKafkaRequestProducer requestProducer;
    
    @Autowired
    private IHandlerRegistry handlerRegistry;
    
    @Autowired
    private RecordRepository recordRepo;
    
    @Autowired
    private DatasetRepository datasetRepo;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.impl.IDatasetProcessor#process(edu.asu.diging.rcn.kafka.messages.model.KafkaJobMessage)
     */
    @Override
    public void process(KafkaJobMessage msg) {
        String filepath = downloadFile(msg);
        if (filepath == null) {
            sendMessage(msg.getJobId(), msg.getDatasetId(), JobStatus.FAILURE);
            return;
        }

        Optional<DatasetImpl> datasetOptional = datasetRepo.findById(msg.getDatasetId());
        if (!datasetOptional.isPresent()) {
            sendMessage(msg.getJobId(), msg.getDatasetId(), JobStatus.FAILURE);
            return;
        }
        
        Dataset dataset = datasetOptional.get();
        
        sendMessage(msg.getJobId(), msg.getDatasetId(), JobStatus.PROCESSING);
        
        RecordIterator recordIterator = null;
        try {
            recordIterator = handlerRegistry.getRecordIterator(msg.getDatasetId(), filepath);
        } catch (IteratorCreationException e1) {
            logger.error("Could not create iterator.", e1);
        }
        
        while (recordIterator.hasNext()) {
            Record record = recordIterator.next();
            record.setDatasetId(dataset.getId());
            recordRepo.save((RecordImpl)record);
            if (dataset.getRecords() == null) {
                dataset.setRecords(new ArrayList<Record>());
            }
            dataset.getRecords().add(record);
            datasetRepo.save((DatasetImpl)dataset);
        }
    }
    
    private void sendMessage(String jobId, String datasetId, JobStatus status) {
        KafkaJobReturnMessage returnMessage = new KafkaJobReturnMessage();
        returnMessage.setStatus(status);
        returnMessage.setJobId(jobId);
        returnMessage.setDatasetId(datasetId);
        try {
            requestProducer.sendRequest(returnMessage, KafkaTopics.UPLOAD_DATASET_STATUS_TOPIC);
        } catch (MessageCreationException e) {
            // FIXME handle this case
            logger.error("Exception sending message.", e);
        }
    }
    
    private String downloadFile(KafkaJobMessage message) {
        String file = null;
        try {
            file = connector.getUploadeFile(message.getJobId());
        } catch (AuthmatcherCommunicationException e) {
            // FIXME this needs to be handled better
            logger.error("Could not get Zotero info.", e);
            return null;
        }
        return file;
    }
}
