package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Description;

public interface DescriptionTagParser {

    String handlesTag();

    void parse(Node node, Description description);

}