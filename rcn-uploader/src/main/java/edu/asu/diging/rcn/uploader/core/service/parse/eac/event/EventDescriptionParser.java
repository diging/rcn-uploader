package edu.asu.diging.rcn.uploader.core.service.parse.eac.event;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.MaintenanceEvent;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MaintenanceEventTagParser;

@Component
public class EventDescriptionParser implements MaintenanceEventTagParser {

    @Override
    public String handlesTag() {
        return "eventDescription";
    }

    @Override
    public void parse(Node node, MaintenanceEvent event) {
        if (event.getEventDescription() == null) {
            event.setEventDescription(new ArrayList<String>());
        }
        
        event.getEventDescription().add(node.getTextContent());
    }

}
