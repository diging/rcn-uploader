package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.Places;
import edu.asu.diging.eaccpf.model.impl.PlaceImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IPlaceTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlacesTagParser;

@Component
public class PlaceParser implements DescriptionTagParser, PlacesTagParser {
    
    @Autowired
    private IPlaceTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "place";
    }

    protected Place parsePlace(Node node) {
        Place place = new PlaceImpl();
        place.setCitations(new ArrayList<>());
        place.setDateRanges(new ArrayList<>());
        place.setDates(new ArrayList<>());
        place.setDateSets(new ArrayList<>());
        place.setDescriptiveNote(new ArrayList<>());
        place.setPlaceEntries(new ArrayList<>());
        place.setPlaceRoles(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), place);
        }
        
        return place;
    }

    @Override
    public void parse(Node node, Description desc) {
        if (desc.getPlaces() == null) {
            desc.setPlaces(new ArrayList<>());
        }
        desc.getPlaces().add(parsePlace(node));
    }


    @Override
    public void parse(Node node, Places places) {
        places.getPlaces().add(parsePlace(node));
    }


}
