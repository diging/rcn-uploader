package edu.asu.diging.rcn.uploader.core.service.parse.eac.control;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.DateRange;
import edu.asu.diging.eaccpf.model.LocalControl;
import edu.asu.diging.eaccpf.model.impl.DateRangeImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalControlTagParser;

@Component
public class DateRangeParser implements LocalControlTagParser {

    @Override
    public String handlesTag() {
        return "dateRange";
    }

    @Override
    public void parse(Node node, LocalControl control) {
        if (control.getDateRanges() == null) {
            control.setDateRanges(new ArrayList<DateRange>());
        }
        
        DateRange dateRange = new DateRangeImpl();
        control.getDateRanges().add(dateRange);
        dateRange.setLocalType(((Element)node).getAttribute("localType"));
        
        NodeList fromDates = ((Element)node).getElementsByTagName("fromDate");
        if (fromDates.getLength() > 0) {
            // there can only be one
            Node from = fromDates.item(0);
            dateRange.setFromDate(from.getTextContent());
        }
        
        NodeList toDates = ((Element)node).getElementsByTagName("toDate");
        if (toDates.getLength() > 0) {
            // there can only be one
            Node to = toDates.item(0);
            dateRange.setToDate(to.getTextContent());
        }
    }


}
