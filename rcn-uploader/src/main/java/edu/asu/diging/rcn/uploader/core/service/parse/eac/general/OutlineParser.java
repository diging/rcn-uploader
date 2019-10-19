package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.GeneralContext;
import edu.asu.diging.eaccpf.model.LegalStatuses;
import edu.asu.diging.eaccpf.model.LocalDescriptions;
import edu.asu.diging.eaccpf.model.Mandates;
import edu.asu.diging.eaccpf.model.Occupations;
import edu.asu.diging.eaccpf.model.Outline;
import edu.asu.diging.eaccpf.model.Places;
import edu.asu.diging.eaccpf.model.StructureOrGenealogy;
import edu.asu.diging.eaccpf.model.impl.OutlineImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.GeneralContextTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.OccupationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlacesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.StructureOrGenealogyTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist.IOutlineTagParserRegistry;

@Component
public class OutlineParser implements BiogHistTagParser, FunctionsTagParser, GeneralContextTagParser,
        LegalStatusesTagParser, LocalDescriptionsTagParser, MandatesTagParser, OccupationsTagParser, PlacesTagParser,
        StructureOrGenealogyTagParser {

    @Autowired
    private IOutlineTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "outline";
    }

    protected Outline parseOutline(Node node) {
        Outline outline = new OutlineImpl();
        outline.setLocalType(((Element) node).getAttribute("localType"));
        outline.setLevels(new ArrayList<>());

        NodeList items = node.getChildNodes();
        for (int i = 0; i < items.getLength(); i++) {
            parserRegistry.parseRecordTag(items.item(i), outline);
        }

        return outline;
    }

    @Override
    public void parse(Node node, BiogHist bio) {
        bio.getOutlines().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, Functions functions) {
        functions.getOutlines().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, GeneralContext context) {
        context.getOutlines().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, LegalStatuses status) {
        status.getOutline().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, LocalDescriptions desc) {
        desc.getOutline().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, Mandates mandates) {
        mandates.getOutlines().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, Occupations occupations) {
        occupations.getOutlines().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, Places places) {
        places.getOutlines().add(parseOutline(node));
    }

    @Override
    public void parse(Node node, StructureOrGenealogy structure) {
        structure.getOutlines().add(parseOutline(node));
    }

}
