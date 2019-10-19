package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Place;

public interface IPlaceTagParserRegistry {

    void parseRecordTag(Node node, Place place);

}