package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.MaintenanceEvent;

public interface IMaintenanceEventTagParserRegistry {

    void parseRecordTag(Node node, MaintenanceEvent agency);

}