package edu.asu.diging.rcn.uploader.core.service.parse.eac.relations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.FunctionRelation;
import edu.asu.diging.eaccpf.model.Relations;
import edu.asu.diging.eaccpf.model.impl.FunctionRelationImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IFunctionRelationTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RelationsTagParser;

@Component
public class FunctionRelationsParser implements RelationsTagParser {
    
    @Autowired
    private IFunctionRelationTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "functionRelation";
    }

    @Override
    public void parse(Node node, Relations relations) {
        FunctionRelation funcRel = new FunctionRelationImpl();
        funcRel.setDates(new ArrayList<>());
        funcRel.setDateRanges(new ArrayList<>());
        funcRel.setDateSets(new ArrayList<>());
        funcRel.setDescriptiveNote(new ArrayList<>());
        funcRel.setPlaceEntries(new ArrayList<>());
        funcRel.setRelationEntries(new ArrayList<>());
        
        if (relations.getCpfRelations() == null) {
            relations.setCpfRelations(new ArrayList<>());
        }
        relations.getFunctionRelations().add(funcRel);
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), funcRel);
        }
    }

}
