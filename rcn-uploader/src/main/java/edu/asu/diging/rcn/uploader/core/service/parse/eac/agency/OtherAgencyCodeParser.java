package edu.asu.diging.rcn.uploader.core.service.parse.eac.agency;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.MaintenanceAgency;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MaintAgencyTagParser;

@Component
public class OtherAgencyCodeParser implements MaintAgencyTagParser {

    @Override
    public String handlesTag() {
        return "otherAgencyCode";
    }

    @Override
    public void parse(Node node, MaintenanceAgency agency) {
        agency.setOtherAgencyCode(node.getTextContent());
    }


}
