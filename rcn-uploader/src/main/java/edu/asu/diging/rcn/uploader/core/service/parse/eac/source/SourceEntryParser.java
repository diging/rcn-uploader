package edu.asu.diging.rcn.uploader.core.service.parse.eac.source;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
        if (source.getSourceEntries() == null) {
            source.setSourceEntries(new ArrayList<>());
        }

        SourceEntry entry = new SourceEntryImpl();
        entry.setText(node.getTextContent());
        entry.setScriptCode(((Element) node).getAttribute("scriptCode"));
        entry.setTransliteration(((Element) node).getAttribute("transliteration"));
        source.getSourceEntries().add(entry);
    }

}
