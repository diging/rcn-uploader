package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.StructureOrGenealogy;

public interface IStructureOrGenealogyTagParserRegistry {

    void parseRecordTag(Node node, StructureOrGenealogy structure);

}