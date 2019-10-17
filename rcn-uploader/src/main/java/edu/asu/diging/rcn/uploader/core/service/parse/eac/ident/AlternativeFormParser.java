package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.NameEntry;
import edu.asu.diging.eaccpf.model.NameEntryParallel;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryParallelTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryTagParser;

@Component
public class AlternativeFormParser implements NameEntryTagParser, NameEntryParallelTagParser {

    @Override
    public String handlesTag() {
        return "alternativeForm";
    }

    @Override
    public void parse(Node node, NameEntry entry) {
        entry.getAlternativeForms().add(node.getTextContent());
    }

    @Override
    public void parse(Node node, NameEntryParallel entry) {
        entry.getAlternativeForms().add(node.getTextContent());
    }
}
