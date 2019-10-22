package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ResourceRelation;

public interface ResourceRelationTagParser {

    String handlesTag();

    void parse(Node node, ResourceRelation relations);

}