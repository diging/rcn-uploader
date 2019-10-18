package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ChronItem;
import edu.asu.diging.eaccpf.model.PlaceEntry;
import edu.asu.diging.eaccpf.model.impl.PlaceEntryImpl;

@Component
public class PlaceEntryParser implements ChronItemTagParser {
    
    @Override
    public String handlesTag() {
        return "placeEntry";
    }

   
    @Override
    public void parse(Node node, ChronItem item) {
        PlaceEntry entry = new PlaceEntryImpl();
        entry.setText(node.getTextContent());
        entry.setAccuracy(((Element)node).getAttribute("accuracy"));
        entry.setAltitude(((Element)node).getAttribute("altitude"));
        entry.setCountryCode(((Element)node).getAttribute("countryCode"));
        entry.setLatitude(((Element)node).getAttribute("latitude"));
        entry.setLocalType(((Element)node).getAttribute("localType"));
        entry.setLongitude(((Element)node).getAttribute("longitude"));
        entry.setScriptCode(((Element)node).getAttribute("scriptCode"));
        entry.setTransliteration(((Element)node).getAttribute("transliteration"));
        entry.setVocabularySource(((Element)node).getAttribute("vocabularySource"));
        item.getPlaceEntries().add(entry);
    }

}
