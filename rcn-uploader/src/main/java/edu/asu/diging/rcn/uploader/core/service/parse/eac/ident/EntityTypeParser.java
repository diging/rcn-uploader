package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Identity;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IdentityTagParser;

@Component
public class EntityTypeParser implements IdentityTagParser {

    @Override
    public String handlesTag() {
        return "entityType";
    }

    @Override
    public void parse(Node node, Identity identity) {
        identity.setEntityType(node.getTextContent());
    }
}
