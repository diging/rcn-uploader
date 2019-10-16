package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.RightsDeclaration;

public interface IRightsDeclTagParserRegistry {

    void parseRecordTag(Node node, RightsDeclaration rights);

}