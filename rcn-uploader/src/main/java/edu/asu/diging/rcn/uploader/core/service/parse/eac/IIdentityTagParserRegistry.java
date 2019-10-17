package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Identity;

public interface IIdentityTagParserRegistry {

    void parseRecordTag(Node node, Identity identity);

}