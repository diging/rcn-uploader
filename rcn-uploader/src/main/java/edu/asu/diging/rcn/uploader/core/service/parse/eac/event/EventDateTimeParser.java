package edu.asu.diging.rcn.uploader.core.service.parse.eac.event;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.MaintenanceEvent;
import edu.asu.diging.eaccpf.model.impl.DateTimeImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MaintenanceEventTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.general.DateTimeParser;

@Component
public class EventDateTimeParser extends DateTimeParser implements MaintenanceEventTagParser {

    @Override
    public String handlesTag() {
        return "eventDateTime";
    }

    @Override
    public void parse(Node node, MaintenanceEvent event) {
        event.setEventDateTime(new DateTimeImpl());
        fillDateTime(node, event.getEventDateTime());
    }

}
