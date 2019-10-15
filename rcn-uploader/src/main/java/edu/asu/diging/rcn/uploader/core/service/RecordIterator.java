package edu.asu.diging.rcn.uploader.core.service;

import edu.asu.diging.eaccpf.model.Record;

public interface RecordIterator {

    Record next();
    
    boolean hasNext();
    
    void close();

}