package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.SetComponent;

public interface ISetComponentTagParserRegistry {

    void parseRecordTag(Node node, SetComponent component);

}