package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Occupation;

public interface IOccupationTagParserRegistry {

    void parseRecordTag(Node node, Occupation occupation);

}