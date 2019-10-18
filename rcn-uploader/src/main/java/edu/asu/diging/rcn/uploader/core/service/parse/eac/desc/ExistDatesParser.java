package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.ExistDates;
import edu.asu.diging.eaccpf.model.impl.ExistDatesImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IExistDatesTagParserRegistry;

@Component
public class ExistDatesParser implements DescriptionTagParser {
    
    @Autowired
    private IExistDatesTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "existDates";
    }

    @Override
    public void parse(Node node, Description desc) {
        ExistDates dates = new ExistDatesImpl();
        desc.setExistDates(dates);
        
        dates.setLocalType(((Element)node).getAttribute("localType"));
        dates.setDateRanges(new ArrayList<>());
        dates.setDates(new ArrayList<>());
        dates.setDateSets(new ArrayList<>());
        dates.setDescriptiveNote(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), desc.getExistDates());
        }
    }

}
