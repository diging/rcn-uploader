package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.NameEntry;
import edu.asu.diging.eaccpf.model.NamePart;
import edu.asu.diging.eaccpf.model.impl.NamePartImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryTagParser;

@Component
public class PartParser implements NameEntryTagParser {

    @Override
    public String handlesTag() {
        return "part";
    }

    @Override
    public void parse(Node node, NameEntry entry) {
        NamePart part = new NamePartImpl();
        part.setPart(node.getTextContent());
        part.setLocalType(((Element)node).getAttribute("localType"));
        entry.getParts().add(part);
    }
}
