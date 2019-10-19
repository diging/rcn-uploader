package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.GeneralContext;
import edu.asu.diging.eaccpf.model.impl.GeneralContextImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IGeneralContextTagParserRegistry;

@Component
public class GeneralContextParser implements DescriptionTagParser {
    
    @Autowired
    private IGeneralContextTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "generalContext";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getGeneralContexts() == null) {
            desc.setGeneralContexts(new ArrayList<>());
        }       
        desc.getGeneralContexts().add(parseGeneralContext(node));
    }

    protected GeneralContext parseGeneralContext(Node node) {
        GeneralContext context = new GeneralContextImpl();
        context.setCitations(new ArrayList<>());
        context.setItemLists(new ArrayList<>());
        context.setOutlines(new ArrayList<>());
        context.setPs(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), context);
        }
        
        return context;
    }
}
