package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.SetComponent;
import edu.asu.diging.eaccpf.model.impl.SetComponentImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ISetComponentTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class AlternativeSetParser implements RecordTagParser {
    
    @Autowired
    private ISetComponentTagParserRegistry tagParsers;

    @Override
    public String handlesTag() {
        return "alternativeSet";
    }

    @Override
    public void parse(Node node, Record record) {
        if (record.getSetComponents() == null) {
            record.setSetComponents(new ArrayList<>());
        }
        
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            if (node.getNodeName().equals("setComponent")) {
                SetComponent compo = new SetComponentImpl();
                compo.setComponentEntries(new ArrayList<>());
                compo.setDescriptiveNote(new ArrayList<>());
                compo.setXlinkHref(((Element)child).getAttribute("xlink:href"));
                compo.setLastDateTimeVerified(((Element)child).getAttribute("lastDateTimeVerified"));
                record.getSetComponents().add(compo);
                
                tagParsers.parseRecordTag(nodeList.item(i), compo);
            }
        }
    }

}
