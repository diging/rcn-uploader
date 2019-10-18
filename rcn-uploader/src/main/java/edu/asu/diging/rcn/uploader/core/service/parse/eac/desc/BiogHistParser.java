package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.impl.BiogHistImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IBiogHistTagParserRegistry;

@Component
public class BiogHistParser implements DescriptionTagParser {
    
    @Autowired
    private IBiogHistTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "biogHist";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getBiogHists() == null) {
            desc.setBiogHists(new ArrayList<>());
        }
        
        BiogHist bio = new BiogHistImpl();
        bio.setLocalType(((Element)node).getAttribute("localType"));
        bio.setPs(new ArrayList<>());
        bio.setCitations(new ArrayList<>());
        bio.setOutlines(new ArrayList<>());
        
        desc.getBiogHists().add(bio);
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), bio);
        }
    }

}
