package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.StructureOrGenealogy;
import edu.asu.diging.eaccpf.model.impl.StructureOrGenealogyImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IStructureOrGenealogyTagParserRegistry;

@Component
public class StructureOrGenealogyParser implements DescriptionTagParser {
    
    @Autowired
    private IStructureOrGenealogyTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "structureOrGenealogy";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getStructureGenealogy() == null) {
            desc.setStructureGenealogy(new ArrayList<>());
        }
        
        StructureOrGenealogy structure = new StructureOrGenealogyImpl();
        structure.setCitations(new ArrayList<>());
        structure.setItemLists(new ArrayList<>());
        structure.setOutlines(new ArrayList<>());
        structure.setPs(new ArrayList<>());
        desc.getStructureGenealogy().add(structure);
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), structure);
        }
    }

}
