package edu.asu.diging.rcn.uploader.core.service.parse.eac.source;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Source;
import edu.asu.diging.eaccpf.model.SourceEntry;
import edu.asu.diging.eaccpf.model.impl.SourceEntryImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.SourceTagParser;

@Component
public class SourceEntryParser implements SourceTagParser {

    @Override
    public String handlesTag() {
        return "sourceEntry";
    }

    @Override
    public void parse(Node node, Source source) {
        if (source.getRelationEntries() == null) {
            source.setRelationEntries(new ArrayList<>());
        }
        
        NodeList sourceEntryNodes = ((Element)node).getElementsByTagName("sourceEntry");
        for(int i=0; i<sourceEntryNodes.getLength(); i++) {
            Element sourceEntryNode = (Element) sourceEntryNodes.item(i);
            SourceEntry entry = new SourceEntryImpl();
            entry.setText(sourceEntryNode.getTextContent());
            entry.setScriptCode(sourceEntryNode.getAttribute("scriptCode"));
            entry.setTransliteration(sourceEntryNode.getAttribute("transliteration"));
            source.getRelationEntries().add(entry);
        }
    }

}
