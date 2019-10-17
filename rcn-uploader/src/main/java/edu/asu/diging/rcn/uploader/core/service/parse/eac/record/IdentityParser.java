package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Identity;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.IdentityImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IIdentityTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class IdentityParser implements RecordTagParser {
    
    @Autowired
    private IIdentityTagParserRegistry identityTagParsers;

    @Override
    public String handlesTag() {
        return "identity";
    }

    @Override
    public void parse(Node node, Record record) {
        Identity identity = new IdentityImpl();
        identity.setLocalType(((Element)node).getAttribute("localType"));
        identity.setIdentityType(((Element)node).getAttribute("identityType"));
        record.setIdentity(identity);
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            // there should only be one
            identityTagParsers.parseRecordTag(nodeList.item(i), identity);
        }
    }

}
