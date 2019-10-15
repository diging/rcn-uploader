package edu.asu.diging.rcn.uploader.core.service;

import edu.asu.diging.rcn.uploader.core.exception.HandlerTestException;
import edu.asu.diging.rcn.uploader.core.exception.IteratorCreationException;

public interface FileHandler {

    boolean canHandle(String path) throws HandlerTestException;

    RecordIterator getIterator(String path, IHandlerRegistry callback, String datasetId) throws IteratorCreationException;

}