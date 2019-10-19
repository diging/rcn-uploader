package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.LocalDescriptions;
import edu.asu.diging.eaccpf.model.impl.LocalDescriptionsImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILocalDescriptionsTagParserRegistry;

@Component
public class LocalDescriptionsParser implements DescriptionTagParser {
    
    @Autowired
    private ILocalDescriptionsTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "localDescriptions";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getLocalDescriptionGroups() == null) {
            desc.setLocalDescriptions(new ArrayList<>());
        }       
        desc.getLocalDescriptionGroups().add(parseLocalDescription(node));
    }

    protected LocalDescriptions parseLocalDescription(Node node) {
        LocalDescriptions desc = new LocalDescriptionsImpl();
        desc.setCitations(new ArrayList<>());
        desc.setDescriptiveNote(new ArrayList<>()); 
        desc.setItemList(new ArrayList<>());
        desc.setLocalDescriptions(new ArrayList<>());
        desc.setOutline(new ArrayList<>());
        desc.setPs(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), desc);
        }
        
        return desc;
    }
}
