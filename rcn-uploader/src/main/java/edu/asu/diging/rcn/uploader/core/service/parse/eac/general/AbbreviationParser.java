package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ConventionDeclaration;
import edu.asu.diging.eaccpf.model.LocalTypeDeclaration;
import edu.asu.diging.eaccpf.model.RightsDeclaration;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ConventionDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalTypeDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RightsDeclarationTagParser;

@Component
public class AbbreviationParser implements ConventionDeclarationTagParser, LocalTypeDeclarationTagParser, RightsDeclarationTagParser {

    @Override
    public String handlesTag() {
        return "abbreviation";
    }

    @Override
    public void parse(Node node, ConventionDeclaration declaration) {
        declaration.setAbbreviation(node.getTextContent());
    }

    @Override
    public void parse(Node node, LocalTypeDeclaration declaration) {
        declaration.setAbbreviation(node.getTextContent());
    }

    @Override
    public void parse(Node node, RightsDeclaration rights) {
        rights.setAbbreviation(node.getTextContent()); 
    }

}
