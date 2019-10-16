package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.MaintenanceAgency;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.MaintenanceAgencyImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IMaintenanceAgencyTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class MaintenanceAgencyParser implements RecordTagParser {
    
    @Autowired
    private IMaintenanceAgencyTagParserRegistry maintenanceAgencyTagParsers;

    @Override
    public String handlesTag() {
        return "maintenanceAgency";
    }

    @Override
    public void parse(Node node, Record record) {
        MaintenanceAgency agency = new MaintenanceAgencyImpl();
        record.setMaintenanceAgency(agency);
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            // there should only be one
            maintenanceAgencyTagParsers.parseRecordTag(nodeList.item(i), agency);
        }
    }

}
