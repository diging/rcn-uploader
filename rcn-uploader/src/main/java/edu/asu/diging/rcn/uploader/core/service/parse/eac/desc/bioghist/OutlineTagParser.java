package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Outline;

public interface OutlineTagParser {

    String handlesTag();

    void parse(Node node, Outline outline);

}