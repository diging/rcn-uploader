package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.LocalDescription;
import edu.asu.diging.eaccpf.model.LocalDescriptions;
import edu.asu.diging.eaccpf.model.impl.LocalDescriptionImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILocalDescriptionTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionsTagParser;

@Component
public class LocalDescriptionParser implements DescriptionTagParser, LocalDescriptionsTagParser {
    
    @Autowired
    private ILocalDescriptionTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "localDescription";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getLocalDescriptions() == null) {
            desc.setLocalDescriptions(new ArrayList<>());
        }       
        desc.getLocalDescriptions().add(parseLocalDescription(node));
    }
    
    @Override
    public void parse(Node node, LocalDescriptions desc) {
        desc.getLocalDescriptions().add(parseLocalDescription(node));
    }

    protected LocalDescription parseLocalDescription(Node node) {
        LocalDescription desc = new LocalDescriptionImpl();
        desc.setCitations(new ArrayList<>());
        desc.setDateRanges(new ArrayList<>());
        desc.setDates(new ArrayList<>());
        desc.setDateSets(new ArrayList<>());
        desc.setDescriptiveNote(new ArrayList<>());
        desc.setPlaceEntries(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), desc);
        }
        
        return desc;
    }
}
