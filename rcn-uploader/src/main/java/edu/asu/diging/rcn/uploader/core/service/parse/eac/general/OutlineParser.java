package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.Outline;
import edu.asu.diging.eaccpf.model.impl.OutlineImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist.IOutlineTagParserRegistry;

@Component
public class OutlineParser implements BiogHistTagParser, FunctionsTagParser {
    
    @Autowired
    private IOutlineTagParserRegistry parserRegistry;
    
    @Override
    public String handlesTag() {
        return "outline";
    }

    @Override
    public void parse(Node node, BiogHist bio) {
        bio.getOutlines().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, Functions functions) {
        functions.getOutlines().add(parseOutline(node));
    }

    protected Outline parseOutline(Node node) {
        Outline outline = new OutlineImpl();
        outline.setLocalType(((Element)node).getAttribute("localType"));
        outline.setLevels(new ArrayList<>());
        
        NodeList items = node.getChildNodes();
        for(int i=0; i<items.getLength(); i++) {
            parserRegistry.parseRecordTag(items.item(i), outline);
        }
        
        return outline;
    }
}
