package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Functions;

public interface IFunctionsTagParserRegistry {

    void parseRecordTag(Node node, Functions functions);

}