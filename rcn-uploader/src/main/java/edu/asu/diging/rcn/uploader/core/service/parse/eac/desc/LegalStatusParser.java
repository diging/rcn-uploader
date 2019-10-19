package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.eaccpf.model.LegalStatuses;
import edu.asu.diging.eaccpf.model.impl.LegalStatusImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILegalStatusTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusesTagParser;

@Component
public class LegalStatusParser implements DescriptionTagParser, LegalStatusesTagParser {

    @Autowired
    private ILegalStatusTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "legalStatus";
    }

    @Override
    public void parse(Node node, Description desc) {
        if (desc.getLegalStatuses() == null) {
            desc.setLegalStatuses(new ArrayList<>());
        }
        desc.getLegalStatuses().add(parseLegalStatus(node));
    }

    @Override
    public void parse(Node node, LegalStatuses status) {
        status.getLegalStatuses().add(parseLegalStatus(node));
    }

    protected LegalStatus parseLegalStatus(Node node) {
        LegalStatus status = new LegalStatusImpl();
        status.setCitations(new ArrayList<>());
        status.setDateRanges(new ArrayList<>());
        status.setDates(new ArrayList<>());
        status.setDateSets(new ArrayList<>());
        status.setDescriptiveNote(new ArrayList<>());
        status.setPlaceEntry(new ArrayList<>());

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), status);
        }

        return status;
    }

}
