package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LocalTypeDeclaration;

public interface ILocalTypeDeclTagParserRegistry {

    void parseRecordTag(Node node, LocalTypeDeclaration localTypeDecl);

}