package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ChronItem;
import edu.asu.diging.eaccpf.model.Date;
import edu.asu.diging.eaccpf.model.DateSet;
import edu.asu.diging.eaccpf.model.ExistDates;
import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.eaccpf.model.LocalControl;
import edu.asu.diging.eaccpf.model.LocalDescription;
import edu.asu.diging.eaccpf.model.Mandate;
import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.UseDates;
import edu.asu.diging.eaccpf.model.impl.DateImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DateSetTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ExistDatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalControlTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandateTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.UseDatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist.ChronItemTagParser;

@Component
public class DateParser
        implements UseDatesTagParser, DateSetTagParser, LocalControlTagParser, ChronItemTagParser, ExistDatesTagParser,
        FunctionTagParser, LegalStatusTagParser, LocalDescriptionTagParser, MandateTagParser, PlaceTagParser {

    @Override
    public String handlesTag() {
        return "date";
    }

    protected Date parseDate(Node node) {
        Date date = new DateImpl();
        date.setDate(node.getTextContent());
        date.setNotBefore(((Element) node).getAttribute("notBefore"));
        date.setStandardDate(((Element) node).getAttribute("standardDate"));
        date.setNotAfter(((Element) node).getAttribute("notAfter"));
        return date;
    }

    @Override
    public void parse(Node node, UseDates useDates) {
        useDates.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, DateSet dateSet) {
        dateSet.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, LocalControl control) {
        control.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, ChronItem item) {
        item.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, ExistDates dates) {
        dates.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, Function function) {
        function.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, LegalStatus status) {
        status.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, LocalDescription desc) {
        desc.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, Mandate mandate) {
        mandate.getDates().add(parseDate(node));
    }

    @Override
    public void parse(Node node, Place place) {
        place.getDates().add(parseDate(node));
    }

}
