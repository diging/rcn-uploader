package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LanguageDeclaration;
import edu.asu.diging.eaccpf.model.LanguageUsed;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguageDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguageUsedTagParser;

@Component
public class ScriptParser implements LanguageUsedTagParser, LanguageDeclarationTagParser {
    
    @Override
    public String handlesTag() {
        return "script";
    }

    @Override
    public void parse(Node node, LanguageUsed lang) {
        lang.setScript(node.getTextContent());
        lang.setScriptCode(((Element)node).getAttribute("scriptCode"));
    }
    
    @Override
    public void parse(Node node, LanguageDeclaration langDecl) {
        langDecl.setScript(node.getTextContent());
        langDecl.setScriptCode(((Element)node).getAttribute("scriptCode"));
    }

}
