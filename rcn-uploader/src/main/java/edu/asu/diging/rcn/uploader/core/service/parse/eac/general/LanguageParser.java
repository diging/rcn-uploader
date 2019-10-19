package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LanguageDeclaration;
import edu.asu.diging.eaccpf.model.LanguageUsed;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguageDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguageUsedTagParser;

@Component
public class LanguageParser implements LanguageUsedTagParser, LanguageDeclarationTagParser {
    
    @Override
    public String handlesTag() {
        return "language";
    }

    @Override
    public void parse(Node node, LanguageUsed lang) {
        lang.setLanguage(node.getTextContent());
        lang.setLanguageCode(((Element)node).getAttribute("languageCode"));
    }

    @Override
    public void parse(Node node, LanguageDeclaration declaration) {
        declaration.setLanguage(node.getTextContent());
        declaration.setLanguageCode(((Element)node).getAttribute("languageCode"));
    }

}
