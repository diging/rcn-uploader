package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.MaintenanceAgency;

public interface IMaintenanceAgencyTagParserRegistry {

    void parseRecordTag(Node node, MaintenanceAgency agency);

}