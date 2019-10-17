package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.EntityId;
import edu.asu.diging.eaccpf.model.Identity;
import edu.asu.diging.eaccpf.model.impl.EntityIdImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IdentityTagParser;

@Component
public class EntityIdParser implements IdentityTagParser {

    @Override
    public String handlesTag() {
        return "entityId";
    }

    @Override
    public void parse(Node node, Identity identity) {
        if (identity.getEntityIds() == null) {
            identity.setEntityIds(new ArrayList<EntityId>());
        }
        EntityId entityId = new EntityIdImpl();
        entityId.setEntityId(node.getTextContent());
        entityId.setLocalType(((Element)node).getAttribute("localType"));
        identity.getEntityIds().add(entityId);
    }
}
