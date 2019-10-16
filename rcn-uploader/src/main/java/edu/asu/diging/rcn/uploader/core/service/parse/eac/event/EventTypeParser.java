package edu.asu.diging.rcn.uploader.core.service.parse.eac.event;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.MaintenanceEvent;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MaintenanceEventTagParser;

@Component
public class EventTypeParser implements MaintenanceEventTagParser {

    @Override
    public String handlesTag() {
        return "eventType";
    }

    @Override
    public void parse(Node node, MaintenanceEvent event) {
        event.setEventType(node.getTextContent());
    }

}
