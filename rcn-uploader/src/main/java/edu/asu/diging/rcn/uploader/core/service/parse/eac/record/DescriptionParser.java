package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.DescriptionImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IDescriptionTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class DescriptionParser implements RecordTagParser {
    
    @Autowired
    private IDescriptionTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "description";
    }

    @Override
    public void parse(Node node, Record record) {
        Description desc = new DescriptionImpl();
        record.setDescription(desc);
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), desc);
        }
    }

}
