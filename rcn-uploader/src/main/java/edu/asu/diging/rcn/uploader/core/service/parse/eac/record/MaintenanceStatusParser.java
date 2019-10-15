package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class MaintenanceStatusParser implements RecordTagParser {

    @Override
    public String handlesTag() {
        return "maintenanceStatus";
    }

    @Override
    public void parse(Node node, Record record) {
        record.setMaintenanceStatus(node.getTextContent());
    }

}
