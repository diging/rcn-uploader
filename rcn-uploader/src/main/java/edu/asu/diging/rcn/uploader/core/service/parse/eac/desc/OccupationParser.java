package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Occupation;
import edu.asu.diging.eaccpf.model.Occupations;
import edu.asu.diging.eaccpf.model.impl.OccupationImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IOccupationTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.OccupationsTagParser;

@Component
public class OccupationParser implements DescriptionTagParser, OccupationsTagParser {
    
    @Autowired
    private IOccupationTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "occupation";
    }

    
    protected Occupation parseOccupation(Node node) {
        Occupation occupation = new OccupationImpl();
        occupation.setCitations(new ArrayList<>());
        occupation.setDateRanges(new ArrayList<>());
        occupation.setDates(new ArrayList<>());
        occupation.setDateSets(new ArrayList<>());
        occupation.setDescriptiveNote(new ArrayList<>());
        occupation.setPlaceEntries(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), occupation);
        }
        
        return occupation;
    }

    @Override
    public void parse(Node node, Description desc) {
        if (desc.getOccupations() == null) {
            desc.setOccupations(new ArrayList<>());
        }
        desc.getOccupations().add(parseOccupation(node));
    }


    @Override
    public void parse(Node node, Occupations occupations) {
        occupations.getOccupations().add(parseOccupation(node));
    }

}
