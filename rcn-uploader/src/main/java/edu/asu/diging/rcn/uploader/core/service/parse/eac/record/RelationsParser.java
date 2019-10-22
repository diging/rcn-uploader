package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.Relations;
import edu.asu.diging.eaccpf.model.impl.RelationsImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IRelationsTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class RelationsParser implements RecordTagParser {
    
    @Autowired
    private IRelationsTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "relations";
    }

    @Override
    public void parse(Node node, Record record) {
        Relations relations = new RelationsImpl();
        relations.setCpfRelations(new ArrayList<>());
        relations.setFunctionRelations(new ArrayList<>());
        relations.setResourceRelations(new ArrayList<>());
        
        if (record.getRelations() == null) {
            record.setRelations(new ArrayList<>());
        }
        record.getRelations().add(relations);
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), relations);
        }
    }

}
