package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LocalControl;

public interface ILocalControlTagParserRegistry {

    void parseRecordTag(Node node, LocalControl control);

}