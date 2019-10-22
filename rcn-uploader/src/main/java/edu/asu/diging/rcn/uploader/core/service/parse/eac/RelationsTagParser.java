package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Relations;

public interface RelationsTagParser {

    String handlesTag();

    void parse(Node node, Relations relations);

}