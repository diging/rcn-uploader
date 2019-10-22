package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.Source;
import edu.asu.diging.eaccpf.model.impl.SourceImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ISourceTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class SourceParser implements RecordTagParser {
    
    @Autowired
    private ISourceTagParserRegistry sourceTagParsers;

    @Override
    public String handlesTag() {
        return "sources";
    }

    @Override
    public void parse(Node node, Record record) {
        if (record.getSources() == null) {
            record.setSources(new ArrayList<Source>());
        }
        
        NodeList nodeList = ((Element)node).getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeName().equals("source")) {
                Source source = new SourceImpl();
                record.getSources().add(source);
                
                Node sourceNode = nodeList.item(i);
                source.setLastDateTimeVerified(((Element)node).getAttribute("lastDateTimeVerified"));
                NodeList sourceChildren = sourceNode.getChildNodes();
                for (int j=0; j<sourceChildren.getLength(); j++) {
                    sourceTagParsers.parseRecordTag(sourceChildren.item(j), source);
                }
            }
        }
    }

}
