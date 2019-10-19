package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LocalDescriptions;

public interface ILocalDescriptionsTagParserRegistry {

    void parseRecordTag(Node node, LocalDescriptions desc);

}