package edu.asu.diging.rcn.uploader.core.service.parse.eac.dates;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Date;
import edu.asu.diging.eaccpf.model.DateSet;
import edu.asu.diging.eaccpf.model.LocalControl;
import edu.asu.diging.eaccpf.model.UseDates;
import edu.asu.diging.eaccpf.model.impl.DateImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DateSetTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalControlTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.UseDatesTagParser;

@Component
public class DateParser implements UseDatesTagParser, DateSetTagParser, LocalControlTagParser {
    
    @Override
    public String handlesTag() {
        return "date";
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
    
    protected Date parseDate(Node node) {
        Date date = new DateImpl();
        date.setDate(node.getTextContent());
        date.setNotBefore(((Element)node).getAttribute("notBefore"));
        date.setStandardDate(((Element)node).getAttribute("standardDate"));
        date.setNotAfter(((Element)node).getAttribute("notAfter"));
        return date;
    }

}
