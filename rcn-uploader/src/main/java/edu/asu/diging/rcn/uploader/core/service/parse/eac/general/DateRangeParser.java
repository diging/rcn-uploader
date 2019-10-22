package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.ChronItem;
import edu.asu.diging.eaccpf.model.CpfRelation;
import edu.asu.diging.eaccpf.model.DateRange;
import edu.asu.diging.eaccpf.model.DateSet;
import edu.asu.diging.eaccpf.model.ExistDates;
import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.FunctionRelation;
import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.eaccpf.model.LocalControl;
import edu.asu.diging.eaccpf.model.LocalDescription;
import edu.asu.diging.eaccpf.model.Mandate;
import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.ResourceRelation;
import edu.asu.diging.eaccpf.model.UseDates;
import edu.asu.diging.eaccpf.model.impl.DateRangeImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.CpfRelationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DateSetTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ExistDatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionRelationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalControlTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandateTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ResourceRelationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.UseDatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist.ChronItemTagParser;

@Component
public class DateRangeParser implements LocalControlTagParser, UseDatesTagParser, DateSetTagParser, ChronItemTagParser,
        ExistDatesTagParser, FunctionTagParser, LegalStatusTagParser, LocalDescriptionTagParser, MandateTagParser,
        PlaceTagParser, CpfRelationTagParser, FunctionRelationsTagParser, ResourceRelationTagParser {

    @Autowired
    private DateParser dateParser;

    @Override
    public String handlesTag() {
        return "dateRange";
    }

    protected DateRange parseRange(Node node) {
        DateRange dateRange = new DateRangeImpl();
        dateRange.setLocalType(((Element) node).getAttribute("localType"));

        NodeList fromDates = ((Element) node).getElementsByTagName("fromDate");
        if (fromDates.getLength() > 0) {
            // there can only be one
            Node from = fromDates.item(0);
            dateRange.setFromDate(dateParser.parseDate(from));
        }

        NodeList toDates = ((Element) node).getElementsByTagName("toDate");
        if (toDates.getLength() > 0) {
            // there can only be one
            Node to = toDates.item(0);
            dateRange.setToDate(dateParser.parseDate(to));
        }

        return dateRange;
    }

    @Override
    public void parse(Node node, LocalControl control) {
        if (control.getDateRanges() == null) {
            control.setDateRanges(new ArrayList<DateRange>());
        }

        control.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, UseDates useDates) {
        useDates.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, DateSet dateSet) {
        dateSet.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, ChronItem item) {
        item.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, ExistDates dates) {
        dates.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, Function function) {
        function.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, LegalStatus status) {
        status.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, LocalDescription desc) {
        desc.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, Mandate mandate) {
        mandate.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, Place place) {
        place.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, CpfRelation relations) {
        relations.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, FunctionRelation relations) {
        relations.getDateRanges().add(parseRange(node));
    }

    @Override
    public void parse(Node node, ResourceRelation relations) {
        relations.getDateRanges().add(parseRange(node));
    }

}
