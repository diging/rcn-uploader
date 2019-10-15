package edu.asu.diging.rcn.uploader.core.service.parse.eac.condec;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ConventionDeclaration;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ConventionDeclarationTagParser;

@Component
public class AbbreviationParser implements ConventionDeclarationTagParser {

    @Override
    public String handlesTag() {
        return "abbreviation";
    }

    @Override
    public void parse(Node node, ConventionDeclaration declaration) {
        declaration.setAbbreviation(node.getTextContent());
    }

}
