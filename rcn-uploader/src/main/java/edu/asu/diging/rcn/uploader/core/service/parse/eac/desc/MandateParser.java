package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Mandate;
import edu.asu.diging.eaccpf.model.Mandates;
import edu.asu.diging.eaccpf.model.impl.MandateImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IMandateTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandatesTagParser;

@Component
public class MandateParser implements DescriptionTagParser, MandatesTagParser {
    
    @Autowired
    private IMandateTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "mandate";
    }

    
    protected Mandate parseMandate(Node node) {
        Mandate mandate = new MandateImpl();
        mandate.setDateRanges(new ArrayList<>());
        mandate.setCitations(new ArrayList<>());
        mandate.setDates(new ArrayList<>());
        mandate.setDateSets(new ArrayList<>());
        mandate.setDescriptiveNote(new ArrayList<>());
        mandate.setPlaceEntries(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), mandate);
        }
        
        return mandate;
    }

    @Override
    public void parse(Node node, Description desc) {
        if (desc.getMandates() == null) {
            desc.setMandates(new ArrayList<>());
        }
        desc.getMandates().add(parseMandate(node));
    }
    
    @Override
    public void parse(Node node, Mandates mandates) {
        mandates.getMandates().add(parseMandate(node));
    }
}
