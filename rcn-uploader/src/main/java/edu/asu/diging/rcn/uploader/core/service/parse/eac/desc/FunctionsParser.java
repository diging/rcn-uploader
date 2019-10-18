package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.impl.FunctionsImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IFunctionsTagParserRegistry;

@Component
public class FunctionsParser implements DescriptionTagParser {
    
    @Autowired
    private IFunctionsTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "functions";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getFunctionsElement() == null) {
            desc.setFunctionsElement(new ArrayList<>());
        }
        
        Functions functions = new FunctionsImpl();
        functions.setCitations(new ArrayList<>());
        functions.setDescriptiveNote(new ArrayList<>());
        functions.setFunctions(new ArrayList<>());
        functions.setPs(new ArrayList<>());
        functions.setItemLists(new ArrayList<>());
        functions.setOutlines(new ArrayList<>());
        functions.setLocalType(((Element)node).getAttribute("localType"));
         
        desc.getFunctionsElement().add(functions);
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), functions);
        }
    }

}
