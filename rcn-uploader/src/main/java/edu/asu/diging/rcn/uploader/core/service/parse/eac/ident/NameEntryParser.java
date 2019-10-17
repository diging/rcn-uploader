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
import edu.asu.diging.rcn.uploader.core.service.parse.eac.INameEntryTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IdentityTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryParallelTagParser;

@Component
public class NameEntryParser implements IdentityTagParser, NameEntryParallelTagParser {
    
    @Autowired
    private INameEntryTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "nameEntry";
    }

    @Override
    public void parse(Node node, Identity identity) {
        if(identity.getNameEntries() == null) {
            identity.setNameEntries(new ArrayList<>());
        }
        identity.getNameEntries().add(parseNameEntry(node));        
    }

    @Override
    public void parse(Node node, NameEntryParallel entry) {
        entry.getNameEntries().add(parseNameEntry(node));
    }
    
    protected NameEntry parseNameEntry(Node node) {
        NameEntry nameEntry = new NameEntryImpl();
        nameEntry.setLocalType(((Element)node).getAttribute("localType"));
        nameEntry.setScriptCode(((Element)node).getAttribute("scriptCode"));
        nameEntry.setTransliteration(((Element)node).getAttribute("transliteration"));
        
        nameEntry.setAlternativeForms(new ArrayList<>());
        nameEntry.setAuthorizedForms(new ArrayList<>());
        nameEntry.setPreferredForms(new ArrayList<>());
        nameEntry.setParts(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for (int i=0; i<children.getLength(); i++) {
            Node child = children.item(i);
            parserRegistry.parseRecordTag(child, nameEntry);
        }
        return nameEntry;
    }
}
