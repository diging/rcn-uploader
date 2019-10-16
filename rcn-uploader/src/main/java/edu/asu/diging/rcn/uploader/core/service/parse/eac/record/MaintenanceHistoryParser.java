package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.MaintenanceEvent;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.MaintenanceEventImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IMaintenanceEventTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class MaintenanceHistoryParser implements RecordTagParser {
    
    @Autowired
    private IMaintenanceEventTagParserRegistry maintEventTagParsers;

    @Override
    public String handlesTag() {
        return "maintenanceHistory";
    }

    @Override
    public void parse(Node node, Record record) {
        if (record.getMaintenanceEventHistory() == null) {
            record.setMaintenanceEventHistory(new ArrayList<MaintenanceEvent>());
        }
        NodeList nodeList = ((Element)node).getElementsByTagName("maintenanceEvent");
        
        for(int i = 0; i<nodeList.getLength(); i++) {
            MaintenanceEvent event  = new MaintenanceEventImpl();
            record.getMaintenanceEventHistory().add(event);
            
            NodeList eventChildren = nodeList.item(i).getChildNodes();
            for (int j=0; j<eventChildren.getLength(); j++) {
                maintEventTagParsers.parseRecordTag(eventChildren.item(j), event);
            }
        }
    }

}
