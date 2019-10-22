package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.CpfRelation;

public interface ICpfRelationsTagParserRegistry {

    void parseRecordTag(Node node, CpfRelation relation);

}