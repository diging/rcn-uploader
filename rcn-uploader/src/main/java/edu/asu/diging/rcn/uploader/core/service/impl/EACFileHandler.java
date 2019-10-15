package edu.asu.diging.rcn.uploader.core.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.asu.diging.rcn.uploader.core.exception.IteratorCreationException;
import edu.asu.diging.rcn.uploader.core.service.FileHandler;
import edu.asu.diging.rcn.uploader.core.service.IHandlerRegistry;
import edu.asu.diging.rcn.uploader.core.service.RecordIterator;
import edu.asu.diging.rcn.uploader.core.service.parse.EacRecordIterator;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry;

@Service
public class EACFileHandler implements FileHandler {
    
    @Autowired
    @Qualifier("tagParserRegistry")
    private ITagParserRegistry parserRegistry;
    
    /* (non-Javadoc)
     * @see edu.asu.diging.citesphere.importer.core.service.parse.impl.FileHandler#canHandle(java.lang.String)
     */
    @Override
    public boolean canHandle(String path) {
        File file = new File(path);
        // exclude hidden files
        if (path.toLowerCase().endsWith(".xml") && !file.getName().startsWith(".")) {
            return true;
        }
        return false;
    }

    @Override
    public RecordIterator getIterator(String path, IHandlerRegistry callback, String datasetId) throws IteratorCreationException {
        return new EacRecordIterator(path, parserRegistry);
    }
    
}