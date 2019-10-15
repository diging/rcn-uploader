package edu.asu.diging.rcn.uploader.core.service.parse;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.RecordImpl;
import edu.asu.diging.rcn.uploader.core.service.RecordIterator;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry;

public class EacRecordIterator implements RecordIterator {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private boolean iteratorDone = false;
    private Record record;
    private String filepath;
    private ITagParserRegistry parserRegistry;
    
    public EacRecordIterator(String filepath, ITagParserRegistry parserRegistry) {
        this.filepath = filepath;
        this.parserRegistry = parserRegistry;
        init();
    }
    
    private void init() {
        parseDocument();        
    }
    
    private void parseDocument() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        DocumentBuilder dBuilder;
        Document doc;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(filepath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Could not parse XML.", e);
            return;
        } 
        
        record = new RecordImpl();
        parseTags(doc.getDocumentElement(), record);
    }
    
    private void parseTags(Element element, Record record) {
        NodeList control = element.getElementsByTagName("control");
        if (control.getLength() > 0) {
            if (control.item(0).hasChildNodes()) {
                NodeList nodeList = control.item(0).getChildNodes();
                for(int i=0; i<nodeList.getLength(); i++) {
                    parserRegistry.parseRecordTag(nodeList.item(i), record);
                }
            }
        }
    }
    
    @Override
    public Record next() {
        if (iteratorDone) {
            return null;
        }
        iteratorDone = true;
        return record;
    }

    @Override
    public boolean hasNext() {
        return !iteratorDone;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

}
