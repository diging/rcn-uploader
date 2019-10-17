package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.DateSet;

public interface IDateSetTagParserRegistry {

    void parseRecordTag(Node node, DateSet dateSet);

}