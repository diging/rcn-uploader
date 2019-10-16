package edu.asu.diging.rcn.uploader.core.service.parse.eac.control;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LocalControl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalControlTagParser;

@Component
public class DateParser implements LocalControlTagParser {

    @Override
    public String handlesTag() {
        return "date";
    }

    @Override
    public void parse(Node node, LocalControl control) {
        control.setDate(node.getTextContent());
    }


}
