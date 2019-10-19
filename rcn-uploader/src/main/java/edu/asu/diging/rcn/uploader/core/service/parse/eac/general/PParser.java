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
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.GeneralContext;
import edu.asu.diging.eaccpf.model.LegalStatuses;
import edu.asu.diging.eaccpf.model.LocalDescriptions;
import edu.asu.diging.eaccpf.model.Mandates;
import edu.asu.diging.eaccpf.model.Occupations;
import edu.asu.diging.eaccpf.model.Places;
import edu.asu.diging.eaccpf.model.StructureOrGenealogy;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.GeneralContextTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.OccupationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlacesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.StructureOrGenealogyTagParser;

@Component
public class PParser implements BiogHistTagParser, FunctionsTagParser, GeneralContextTagParser, LegalStatusesTagParser,
        LocalDescriptionsTagParser, MandatesTagParser, OccupationsTagParser, PlacesTagParser, StructureOrGenealogyTagParser {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String handlesTag() {
        return "p";
    }
    
    private String getPText(Node node) throws TransformerFactoryConfigurationError {
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

        return buffer.toString();
    }

    @Override
    public void parse(Node node, BiogHist bio) {
        bio.getPs().add(getPText(node));
    }

    @Override
    public void parse(Node node, Functions functions) {
        functions.getPs().add(getPText(node));
    }

    @Override
    public void parse(Node node, GeneralContext context) {
        context.getPs().add(getPText(node));
    }

    @Override
    public void parse(Node node, LegalStatuses status) {
        status.getPs().add(getPText(node));
    }
    
    @Override
    public void parse(Node node, LocalDescriptions desc) {
        desc.getPs().add(getPText(node));
    }

    @Override
    public void parse(Node node, Mandates mandates) {
        mandates.getPs().add(getPText(node));
    }

    @Override
    public void parse(Node node, Occupations occupations) {
        occupations.getPs().add(getPText(node));
    }

    @Override
    public void parse(Node node, Places places) {
        places.getPs().add(getPText(node));
    }

    @Override
    public void parse(Node node, StructureOrGenealogy structure) {
        structure.getPs().add(getPText(node));
    }

}
