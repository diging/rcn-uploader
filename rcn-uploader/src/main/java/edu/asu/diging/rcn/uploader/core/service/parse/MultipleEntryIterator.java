package edu.asu.diging.rcn.uploader.core.service.parse;

import java.util.ArrayList;
import java.util.List;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.rcn.uploader.core.service.RecordIterator;


public class MultipleEntryIterator implements RecordIterator {
    
    private List<RecordIterator> iterators;
    private RecordIterator currentIterator;
    
    public MultipleEntryIterator() {
        iterators = new ArrayList<>();
    }

    @Override
    public Record next() {
        if (currentIterator != null && currentIterator.hasNext()) {
            return currentIterator.next();
        }
        
        if (currentIterator == null || !currentIterator.hasNext()) {
            if (iterators.size() > 0) {
                currentIterator = iterators.get(0);
                iterators.remove(0);
                if (currentIterator.hasNext()) {
                    return currentIterator.next();
                } else {
                    return next();
                }
            }
        }
        
        return null;
    }

    @Override
    public boolean hasNext() {
        if (currentIterator != null && currentIterator.hasNext()) {
            return true;
        }
        if (currentIterator == null || !currentIterator.hasNext()) {
            if (iterators.size() > 0 && iterators.get(0).hasNext()) {
                return true;
            }
        }
        return false;
    }
    
    public void addIterator(RecordIterator iterator) {
        if (iterator != null) {
            iterators.add(iterator);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }

}