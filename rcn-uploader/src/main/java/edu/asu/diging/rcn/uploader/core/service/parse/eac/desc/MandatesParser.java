package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Mandates;
import edu.asu.diging.eaccpf.model.impl.MandatesImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IMandatesTagParserRegistry;

@Component
public class MandatesParser implements DescriptionTagParser {
    
    @Autowired
    private IMandatesTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "mandates";
    }

    @Override
    public void parse(Node node, Description desc) {
        if (desc.getMandateGroups() == null) {
            desc.setMandateGroups(new ArrayList<>());
        }
        desc.getMandateGroups().add(parseMandates(node));
    }
    
    protected Mandates parseMandates(Node node) {
        Mandates mandates = new MandatesImpl();
        mandates.setCitations(new ArrayList<>());
        mandates.setDescriptiveNote(new ArrayList<>());
        mandates.setItemLists(new ArrayList<>());
        mandates.setOutlines(new ArrayList<>());
        mandates.setMandates(new ArrayList<>());
        mandates.setPs(new ArrayList<>());
        
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), mandates);
        }
        
        return mandates;
    }
}
