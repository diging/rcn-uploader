package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.LegalStatuses;
import edu.asu.diging.eaccpf.model.impl.LegalStatusesImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILegalStatusesTagParserRegistry;

@Component
public class LegalStatusesParser implements DescriptionTagParser {

    @Autowired
    private ILegalStatusesTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "legalStatuses";
    }

    @Override
    public void parse(Node node, Description desc) {
        if (desc.getLegalStatusGroups() == null) {
            desc.setLegalStatuseGroups(new ArrayList<>());
        }
        desc.getLegalStatusGroups().add(parseLegalStatus(node));
    }

    protected LegalStatuses parseLegalStatus(Node node) {
        LegalStatuses status = new LegalStatusesImpl();
        status.setCitations(new ArrayList<>());
        status.setDescriptiveNote(new ArrayList<>());
        status.setItemList(new ArrayList<>());
        status.setLegalStatuses(new ArrayList<>());
        status.setOutline(new ArrayList<>());
        status.setPs(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), status);
        }

        return status;
    }

}
