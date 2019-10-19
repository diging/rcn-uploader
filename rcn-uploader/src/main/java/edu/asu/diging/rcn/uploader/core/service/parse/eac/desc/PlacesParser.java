package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Places;
import edu.asu.diging.eaccpf.model.impl.PlacesImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IPlacesTagParserRegistry;

@Component
public class PlacesParser implements DescriptionTagParser {
    
    @Autowired
    private IPlacesTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "places";
    }

    
    protected Places parsePlace(Node node) {
        Places place = new PlacesImpl();
        place.setCitations(new ArrayList<>());
        place.setDescriptiveNote(new ArrayList<>());
        place.setItemLists(new ArrayList<>());
        place.setOutlines(new ArrayList<>());
        place.setPs(new ArrayList<>());
        place.setPlaces(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), place);
        }
        
        return place;
    }

    @Override
    public void parse(Node node, Description desc) {
        if (desc.getPlaceGroups() == null) {
            desc.setPlaceGroups(new ArrayList<>());
        }
        desc.getPlaceGroups().add(parsePlace(node));
    }



}
