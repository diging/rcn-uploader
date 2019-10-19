package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LegalStatus;

public interface ILegalStatusTagParserRegistry {

    void parseRecordTag(Node node, LegalStatus status);

}