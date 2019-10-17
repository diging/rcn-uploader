package edu.asu.diging.rcn.uploader.core.service.parse.eac.dates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.DateSet;
import edu.asu.diging.eaccpf.model.UseDates;
import edu.asu.diging.eaccpf.model.impl.DateSetImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IDateSetTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.UseDatesTagParser;

@Component
public class DateSetParser implements UseDatesTagParser {

    @Autowired
    private IDateSetTagParserRegistry parserRegistry;
 
    @Override
    public String handlesTag() {
        return "dateSet";
    }

    @Override
    public void parse(Node node, UseDates useDates) {
       DateSet dateSet = new DateSetImpl();
       dateSet.setLocalType(((Element)node).getAttribute("localType"));
       useDates.getDateSets().add(dateSet);
       
       NodeList children = node.getChildNodes();
       for(int i=0; i<children.getLength(); i++) {
           parserRegistry.parseRecordTag(children.item(i), dateSet);
       }
    }
}
