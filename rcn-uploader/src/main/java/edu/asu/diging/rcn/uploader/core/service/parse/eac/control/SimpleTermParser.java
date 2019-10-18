package edu.asu.diging.rcn.uploader.core.service.parse.eac.control;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LocalControl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalControlTagParser;

@Component
public class SimpleTermParser implements LocalControlTagParser {

    @Override
    public String handlesTag() {
        return "term";
    }

    @Override
    public void parse(Node node, LocalControl control) {
        control.setTerm(node.getTextContent());
    }


}
