package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.eaccpf.model.ConventionDeclaration;
import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.GeneralContext;
import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.eaccpf.model.LegalStatuses;
import edu.asu.diging.eaccpf.model.LocalDescription;
import edu.asu.diging.eaccpf.model.LocalDescriptions;
import edu.asu.diging.eaccpf.model.LocalTypeDeclaration;
import edu.asu.diging.eaccpf.model.Mandate;
import edu.asu.diging.eaccpf.model.Mandates;
import edu.asu.diging.eaccpf.model.Occupations;
import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.Places;
import edu.asu.diging.eaccpf.model.RightsDeclaration;
import edu.asu.diging.eaccpf.model.StructureOrGenealogy;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ConventionDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.GeneralContextTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalTypeDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandateTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.OccupationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlacesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RightsDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.StructureOrGenealogyTagParser;

@Component
public class CitationParser implements ConventionDeclarationTagParser, LocalTypeDeclarationTagParser,
        RightsDeclarationTagParser, BiogHistTagParser, FunctionTagParser, FunctionsTagParser, GeneralContextTagParser,
        LegalStatusTagParser, LegalStatusesTagParser, LocalDescriptionTagParser, LocalDescriptionsTagParser,
        MandateTagParser, MandatesTagParser, OccupationsTagParser, PlaceTagParser, PlacesTagParser, StructureOrGenealogyTagParser {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String getCitationText(Node node) throws TransformerFactoryConfigurationError {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            logger.error("Could not create transformer to read abstract.", e);
            return "";
        }
        StringWriter buffer = new StringWriter();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

        NodeList children = node.getChildNodes();
        if (children.getLength() > 0) {
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                try {
                    transformer.transform(new DOMSource(child), new StreamResult(buffer));
                } catch (TransformerException e) {
                    logger.error("Could not extract abstract tag content.", e);
                    continue;
                }
            }
        }

        String citationText = buffer.toString();
        return citationText;
    }

    @Override
    public String handlesTag() {
        return "citation";
    }

    @Override
    public void parse(Node node, ConventionDeclaration declaration) {
        declaration.setCitation(getCitationText(node));
    }

    @Override
    public void parse(Node node, LocalTypeDeclaration declaration) {
        declaration.setCitation(getCitationText(node));
    }

    @Override
    public void parse(Node node, RightsDeclaration rights) {
        rights.setCitation(getCitationText(node));
    }

    @Override
    public void parse(Node node, BiogHist bio) {
        bio.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, Function function) {
        function.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, Functions functions) {
        functions.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, GeneralContext context) {
        context.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, LegalStatus status) {
        status.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, LegalStatuses status) {
        status.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, LocalDescription desc) {
        desc.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, LocalDescriptions desc) {
        desc.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, Mandate mandate) {
        mandate.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, Mandates mandates) {
        mandates.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, Occupations occupations) {
        occupations.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, Place place) {
        place.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, Places places) {
        places.getCitations().add(getCitationText(node));
    }

    @Override
    public void parse(Node node, StructureOrGenealogy structure) {
        structure.getCitations().add(getCitationText(node));
    }

}
