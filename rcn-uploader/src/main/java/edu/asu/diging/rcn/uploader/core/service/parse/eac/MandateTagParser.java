package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Mandate;

public interface MandateTagParser {

    String handlesTag();

    void parse(Node node, Mandate mandate);

}