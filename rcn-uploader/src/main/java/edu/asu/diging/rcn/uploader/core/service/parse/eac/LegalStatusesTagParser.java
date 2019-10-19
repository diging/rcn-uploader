package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LegalStatuses;

public interface LegalStatusesTagParser {

    String handlesTag();

    void parse(Node node, LegalStatuses status);

}