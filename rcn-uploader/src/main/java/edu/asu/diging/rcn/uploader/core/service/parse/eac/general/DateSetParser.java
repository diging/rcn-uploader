package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.DateSet;
import edu.asu.diging.eaccpf.model.ExistDates;
import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.eaccpf.model.LocalDescription;
import edu.asu.diging.eaccpf.model.Mandate;
import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.UseDates;
import edu.asu.diging.eaccpf.model.impl.DateSetImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ExistDatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IDateSetTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandateTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.UseDatesTagParser;

@Component
public class DateSetParser implements UseDatesTagParser, ExistDatesTagParser, FunctionTagParser, LegalStatusTagParser,
        LocalDescriptionTagParser, MandateTagParser, PlaceTagParser {

    @Autowired
    private IDateSetTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "dateSet";
    }
    
    protected DateSet parseDateSet(Node node) {
        DateSet dateSet = new DateSetImpl();
        dateSet.setLocalType(((Element) node).getAttribute("localType"));

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), dateSet);
        }

        return dateSet;
    }

    @Override
    public void parse(Node node, ExistDates dates) {
        dates.getDateSets().add(parseDateSet(node));
    }

    @Override
    public void parse(Node node, UseDates useDates) {
        useDates.getDateSets().add(parseDateSet(node));
    }

    @Override
    public void parse(Node node, Function function) {
        function.getDateSets().add(parseDateSet(node));
    }

    @Override
    public void parse(Node node, LegalStatus status) {
        status.getDateSets().add(parseDateSet(node));
    }

    @Override
    public void parse(Node node, LocalDescription desc) {
        desc.getDateSets().add(parseDateSet(node));
    }

    @Override
    public void parse(Node node, Mandate mandate) {
        mandate.getDateSets().add(parseDateSet(node));
    }

    @Override
    public void parse(Node node, Place place) {
        place.getDateSets().add(parseDateSet(node));
    }
    

}
