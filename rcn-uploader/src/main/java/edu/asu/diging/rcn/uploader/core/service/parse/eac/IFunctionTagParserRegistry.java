package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Function;

public interface IFunctionTagParserRegistry {

    void parseRecordTag(Node node, Function function);

}