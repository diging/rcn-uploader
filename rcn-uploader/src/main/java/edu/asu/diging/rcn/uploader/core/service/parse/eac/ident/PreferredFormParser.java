package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.NameEntry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryTagParser;

@Component
public class PreferredFormParser implements NameEntryTagParser {

    @Override
    public String handlesTag() {
        return "preferredForm";
    }

    @Override
    public void parse(Node node, NameEntry entry) {
        entry.getPreferredForms().add(node.getTextContent());
    }
}
