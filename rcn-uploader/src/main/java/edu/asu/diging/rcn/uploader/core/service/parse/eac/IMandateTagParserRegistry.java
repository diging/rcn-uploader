package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Mandate;

public interface IMandateTagParserRegistry {

    void parseRecordTag(Node node, Mandate mandate);

}