package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Occupations;

public interface IOccupationsTagParserRegistry {

    void parseRecordTag(Node node, Occupations occupations);

}