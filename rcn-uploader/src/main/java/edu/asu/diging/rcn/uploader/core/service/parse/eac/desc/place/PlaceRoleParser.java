package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.place;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.PlaceRole;
import edu.asu.diging.eaccpf.model.impl.PlaceRoleImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;

@Component
public class PlaceRoleParser implements PlaceTagParser {
    
    @Override
    public String handlesTag() {
        return "placeRole";
    }

    @Override
    public void parse(Node node, Place place) {
        PlaceRole role = new PlaceRoleImpl();
        role.setLastDateTimeVerified(((Element)node).getAttribute("lastDateTimeVerified"));
        role.setPlaceRole(((Element)node).getTextContent());
        role.setScriptCode(((Element)node).getAttribute("scriptCode"));
        role.setTransliteration(((Element)node).getAttribute("transliteration"));
        role.setVocabularySource(((Element)node).getAttribute("vocabularySource"));
        
        place.getPlaceRoles().add(role);
    }

}
