package edu.asu.diging.rcn.uploader.core.service.parse.eac.relations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.CpfRelation;
import edu.asu.diging.eaccpf.model.Relations;
import edu.asu.diging.eaccpf.model.impl.CpfRelationImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ICpfRelationsTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RelationsTagParser;

@Component
public class CpfRelationsParser implements RelationsTagParser {
    
    @Autowired
    private ICpfRelationsTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "cpfRelation";
    }

    @Override
    public void parse(Node node, Relations relations) {
        CpfRelation cpfRel = new CpfRelationImpl();
        cpfRel.setCpfRelationType(((Element)node).getAttribute("cpfRelationType"));
        cpfRel.setLastDateTimeVerified(((Element)node).getAttribute("lastDateTimeVerified"));
        cpfRel.setHref(((Element)node).getAttributeNS("http://www.w3.org/1999/xlink", "href"));
        cpfRel.setDates(new ArrayList<>());
        cpfRel.setDateRanges(new ArrayList<>());
        cpfRel.setDateSets(new ArrayList<>());
        cpfRel.setDescriptiveNote(new ArrayList<>());
        cpfRel.setPlaceEntries(new ArrayList<>());
        cpfRel.setRelationEntries(new ArrayList<>());
        
        if (relations.getCpfRelations() == null) {
            relations.setCpfRelations(new ArrayList<>());
        }
        relations.getCpfRelations().add(cpfRel);
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), cpfRel);
        }
    }

}
