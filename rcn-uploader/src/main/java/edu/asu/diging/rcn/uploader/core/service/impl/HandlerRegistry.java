package edu.asu.diging.rcn.uploader.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import edu.asu.diging.rcn.uploader.core.exception.HandlerTestException;
import edu.asu.diging.rcn.uploader.core.exception.IteratorCreationException;
import edu.asu.diging.rcn.uploader.core.service.RecordIterator;
import edu.asu.diging.rcn.uploader.core.service.FileHandler;
import edu.asu.diging.rcn.uploader.core.service.IHandlerRegistry;

@Service
public class HandlerRegistry implements IHandlerRegistry {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ApplicationContext ctx;

    private List<FileHandler> handlers;
    
    @PostConstruct
    public void init() {
        handlers = new ArrayList<>();
        
        Map<String, FileHandler> allHandlers = ctx.getBeansOfType(FileHandler.class);
        allHandlers.values().forEach(h -> handlers.add(h));        
    }
    
    /* (non-Javadoc)
     * @see edu.asu.diging.citesphere.importer.core.service.parse.impl.IHandlerRegistry#handleFile(edu.asu.diging.citesphere.importer.core.service.impl.JobInfo, java.lang.String)
     */
    @Override
    public RecordIterator getRecordIterator(String datasetId, String filePath) throws IteratorCreationException {
        for (FileHandler handler: handlers) {
            try {
                if (handler.canHandle(filePath)) {
                    return handler.getIterator(filePath, this, datasetId);
                }
            } catch (HandlerTestException e) {
                logger.error("Could not test if handable.", e);
            }
        }
        return null;
    }
}