package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.BiogHist;

public interface IBiogHistTagParserRegistry {

    void parseRecordTag(Node node, BiogHist bio);

}