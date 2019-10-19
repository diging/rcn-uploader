package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ChronItem;
import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.eaccpf.model.LocalDescription;
import edu.asu.diging.eaccpf.model.Mandate;
import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.PlaceEntry;
import edu.asu.diging.eaccpf.model.impl.PlaceEntryImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandateTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist.ChronItemTagParser;

@Component
public class PlaceEntryParser implements ChronItemTagParser, FunctionTagParser, LegalStatusTagParser,
        LocalDescriptionTagParser, MandateTagParser, PlaceTagParser {

    @Override
    public String handlesTag() {
        return "placeEntry";
    }

    protected PlaceEntry parsePlaceEntry(Node node) {
        PlaceEntry entry = new PlaceEntryImpl();
        entry.setText(node.getTextContent());
        entry.setAccuracy(((Element) node).getAttribute("accuracy"));
        entry.setAltitude(((Element) node).getAttribute("altitude"));
        entry.setCountryCode(((Element) node).getAttribute("countryCode"));
        entry.setLatitude(((Element) node).getAttribute("latitude"));
        entry.setLocalType(((Element) node).getAttribute("localType"));
        entry.setLongitude(((Element) node).getAttribute("longitude"));
        entry.setScriptCode(((Element) node).getAttribute("scriptCode"));
        entry.setTransliteration(((Element) node).getAttribute("transliteration"));
        entry.setVocabularySource(((Element) node).getAttribute("vocabularySource"));
        return entry;
    }

    @Override
    public void parse(Node node, ChronItem item) {
        item.getPlaceEntries().add(parsePlaceEntry(node));
    }

    @Override
    public void parse(Node node, Function function) {
        function.getPlaceEntries().add(parsePlaceEntry(node));
    }

    @Override
    public void parse(Node node, LegalStatus status) {
        status.getPlaceEntry().add(parsePlaceEntry(node));
    }

    @Override
    public void parse(Node node, LocalDescription desc) {
        desc.getPlaceEntries().add(parsePlaceEntry(node));
    }

    @Override
    public void parse(Node node, Mandate mandate) {
        mandate.getPlaceEntries().add(parsePlaceEntry(node));
    }

    @Override
    public void parse(Node node, Place place) {
        place.getPlaceEntries().add(parsePlaceEntry(node));
    }

}
