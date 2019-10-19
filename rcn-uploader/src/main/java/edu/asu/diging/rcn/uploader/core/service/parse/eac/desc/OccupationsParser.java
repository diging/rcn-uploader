package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Occupations;
import edu.asu.diging.eaccpf.model.impl.OccupationsImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IOccupationsTagParserRegistry;

@Component
public class OccupationsParser implements DescriptionTagParser {
    
    @Autowired
    private IOccupationsTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "occupations";
    }

    @Override
    public void parse(Node node, Description desc) {
        if (desc.getOccupationGroups()== null) {
            desc.setOccupationGroups(new ArrayList<>());
        }
        desc.getOccupationGroups().add(parseOccupations(node));
    }
    
    protected Occupations parseOccupations(Node node) {
        Occupations occupations = new OccupationsImpl();
        occupations.setCitations(new ArrayList<>());
        occupations.setDescriptiveNote(new ArrayList<>());
        occupations.setItemLists(new ArrayList<>());
        occupations.setOccupations(new ArrayList<>());
        occupations.setOutlines(new ArrayList<>());
        occupations.setPs(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), occupations);
        }
        
        return occupations;
    }
}
