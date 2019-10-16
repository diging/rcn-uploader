package edu.asu.diging.rcn.uploader.core.service.parse.eac.langdec;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LanguageDeclaration;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguageDeclarationTagParser;

@Component
public class ScriptParser implements LanguageDeclarationTagParser {

    @Override
    public String handlesTag() {
        return "language";
    }

    @Override
    public void parse(Node node, LanguageDeclaration langDecl) {
        langDecl.setLanguage(node.getTextContent());
        langDecl.setLanguageCode(((Element)node).getAttribute("languageCode"));
    }

}
