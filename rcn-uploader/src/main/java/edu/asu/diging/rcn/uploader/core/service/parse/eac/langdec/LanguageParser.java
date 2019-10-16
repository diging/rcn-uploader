package edu.asu.diging.rcn.uploader.core.service.parse.eac.langdec;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LanguageDeclaration;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguageDeclarationTagParser;

@Component
public class LanguageParser implements LanguageDeclarationTagParser {

    @Override
    public String handlesTag() {
        return "script";
    }

    @Override
    public void parse(Node node, LanguageDeclaration langDecl) {
        langDecl.setScript(node.getTextContent());
        langDecl.setScriptCode(((Element)node).getAttribute("scriptCode"));
    }

}
