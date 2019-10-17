package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Identity;
import edu.asu.diging.eaccpf.model.NameEntry;
import edu.asu.diging.eaccpf.model.NameEntryParallel;
import edu.asu.diging.eaccpf.model.impl.NameEntryImpl;
import edu.asu.diging.eaccpf.model.impl.NameEntryParallelImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.INameEntryParallelTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.INameEntryTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IdentityTagParser;

@Component
public class NameEntryParallelParser implements IdentityTagParser {
    
    @Autowired
    private INameEntryParallelTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "nameEntryParallel";
    }

    @Override
    public void parse(Node node, Identity identity) {
        if(identity.getNameEntriesParallel() == null) {
            identity.setNameEntriesParallel(new ArrayList<>());
        }
        
        NameEntryParallel nameEntry = new NameEntryParallelImpl();
        nameEntry.setLocalType(((Element)node).getAttribute("localType"));
        
        nameEntry.setAlternativeForms(new ArrayList<>());
        nameEntry.setAuthorizedForms(new ArrayList<>());
        nameEntry.setNameEntries(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for (int i=0; i<children.getLength(); i++) {
            Node child = children.item(i);
            parserRegistry.parseRecordTag(child, nameEntry);
        }
        
    }
}
