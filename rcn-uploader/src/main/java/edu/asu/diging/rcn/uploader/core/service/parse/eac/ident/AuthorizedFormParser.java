package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.NameEntry;
import edu.asu.diging.eaccpf.model.NameEntryParallel;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryParallelTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryTagParser;

@Component
public class AuthorizedFormParser implements NameEntryTagParser, NameEntryParallelTagParser {

    @Override
    public String handlesTag() {
        return "authorizedForm";
    }

    @Override
    public void parse(Node node, NameEntry entry) {
        entry.getAuthorizedForms().add(node.getTextContent());
    }

    @Override
    public void parse(Node node, NameEntryParallel entry) {
        entry.getAuthorizedForms().add(node.getTextContent());
    }
}
