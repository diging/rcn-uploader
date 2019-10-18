package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Description;

public interface IDescriptionTagParserRegistry {

    void parseRecordTag(Node node, Description description);

}