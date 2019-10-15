package edu.asu.diging.rcn.uploader.core.service;

import edu.asu.diging.rcn.uploader.core.exception.IteratorCreationException;

public interface IHandlerRegistry {

    RecordIterator getRecordIterator(String datasetId, String filePath) throws IteratorCreationException;

}