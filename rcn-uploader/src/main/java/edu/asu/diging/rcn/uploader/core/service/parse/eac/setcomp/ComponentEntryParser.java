package edu.asu.diging.rcn.uploader.core.service.parse.eac.setcomp;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ComponentEntry;
import edu.asu.diging.eaccpf.model.SetComponent;
import edu.asu.diging.eaccpf.model.impl.ComponentEntryImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.SetComponentTagParser;

@Component
public class ComponentEntryParser implements SetComponentTagParser {
    
    @Override
    public String handlesTag() {
        return "componentEntry";
    }

    @Override
    public void parse(Node node, SetComponent component) {
        ComponentEntry entry = new ComponentEntryImpl();
        entry.setLocalType(((Element)node).getAttribute("localType"));
        entry.setScriptCode(((Element)node).getAttribute("scriptCode"));
        entry.setTransliteration(((Element)node).getAttribute("transliteration"));
        entry.setText(node.getTextContent());
        
        component.getComponentEntries().add(entry);
    }

}
