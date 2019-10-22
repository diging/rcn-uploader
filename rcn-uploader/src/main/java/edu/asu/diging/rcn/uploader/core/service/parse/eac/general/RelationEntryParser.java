package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.CpfRelation;
import edu.asu.diging.eaccpf.model.FunctionRelation;
import edu.asu.diging.eaccpf.model.RelationEntry;
import edu.asu.diging.eaccpf.model.ResourceRelation;
import edu.asu.diging.eaccpf.model.impl.RelationEntryImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.CpfRelationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionRelationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ResourceRelationTagParser;

@Component
public class RelationEntryParser implements CpfRelationTagParser, FunctionRelationsTagParser, ResourceRelationTagParser {
    
    @Override
    public String handlesTag() {
        return "relationEntry";
    }
    
    protected RelationEntry parseEntry(Node node) {
        RelationEntry entry = new RelationEntryImpl();
        entry.setLocalType(((Element)node).getAttribute("localType"));
        entry.setScriptCode(((Element)node).getAttribute("scriptCode"));
        entry.setTransliteration(((Element)node).getAttribute("transliteration"));
        entry.setText(node.getTextContent());
        return entry;
    }

    @Override
    public void parse(Node node, CpfRelation relation) {
        relation.getRelationEntries().add(parseEntry(node));
    }

    @Override
    public void parse(Node node, FunctionRelation relations) {
        relations.getRelationEntries().add(parseEntry(node));
    }

    @Override
    public void parse(Node node, ResourceRelation relations) {
        relations.getRelationEntries().add(parseEntry(node));
    }

}
