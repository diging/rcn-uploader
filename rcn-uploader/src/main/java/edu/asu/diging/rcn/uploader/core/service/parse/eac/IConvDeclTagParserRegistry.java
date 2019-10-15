package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ConventionDeclaration;

public interface IConvDeclTagParserRegistry {

    void parseRecordTag(Node node, ConventionDeclaration convDecl);

}