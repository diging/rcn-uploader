package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Places;

public interface IPlacesTagParserRegistry {

    void parseRecordTag(Node node, Places places);

}