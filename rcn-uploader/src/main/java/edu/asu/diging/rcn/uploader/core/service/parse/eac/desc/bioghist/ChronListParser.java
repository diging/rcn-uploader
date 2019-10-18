package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.eaccpf.model.ChronItem;
import edu.asu.diging.eaccpf.model.impl.ChronItemImpl;
import edu.asu.diging.eaccpf.model.impl.ChronListImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;

@Component
public class ChronListParser implements BiogHistTagParser {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private IChronItemTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "chronList";
    }

   
    @Override
    public void parse(Node node, BiogHist bio) {
        bio.setChronList(new ChronListImpl());
        bio.getChronList().setLocalType(((Element)node).getAttribute("localType"));
        bio.getChronList().setChronItems(new ArrayList<ChronItem>());
        
        NodeList items = node.getChildNodes();
        for(int i=0; i<items.getLength(); i++) {
            Node item = items.item(i);
            if(item.getNodeName().equals("chronItem")) {
                ChronItem chronItem = new ChronItemImpl();
                chronItem.setLocalType(((Element)node).getAttribute("localType"));
                chronItem.setDateRanges(new ArrayList<>());
                chronItem.setDates(new ArrayList<>());
                chronItem.setPlaceEntries(new ArrayList<>());
                
                bio.getChronList().getChronItems().add(chronItem);
                
                NodeList itemChildren = item.getChildNodes();
                for(int j=0; j<itemChildren.getLength(); j++) {
                    parserRegistry.parseRecordTag(itemChildren.item(j), chronItem);
                }
            }
        }
    }

    

}
