package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.NameEntry;
import edu.asu.diging.eaccpf.model.NameEntryParallel;
import edu.asu.diging.eaccpf.model.UseDates;
import edu.asu.diging.eaccpf.model.impl.UseDatesImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IUseDatesTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryParallelTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryTagParser;

@Component
public class UseDatesParser implements NameEntryTagParser, NameEntryParallelTagParser {
    
    @Autowired
    private IUseDatesTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "useDates";
    }

    @Override
    public void parse(Node node, NameEntry entry) {
        entry.setUseDates(parseUseDates(node));
    }

    @Override
    public void parse(Node node, NameEntryParallel entry) {
        entry.setUseDates(parseUseDates(node));
    }
    
    public UseDates parseUseDates(Node node) {
        UseDates useDates = new UseDatesImpl();
        
        useDates.setDateRanges(new ArrayList<>());
        useDates.setDates(new ArrayList<>());
        useDates.setDateSets(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i< children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), useDates);
        }
        
        return useDates;
    }
}
