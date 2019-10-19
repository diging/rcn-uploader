package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LocalDescription;

public interface ILocalDescriptionTagParserRegistry {

    void parseRecordTag(Node node, LocalDescription desc);

}