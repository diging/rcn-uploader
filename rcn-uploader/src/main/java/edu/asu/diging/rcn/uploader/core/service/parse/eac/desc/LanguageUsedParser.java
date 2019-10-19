package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.LanguageUsed;
import edu.asu.diging.eaccpf.model.LanguagesUsed;
import edu.asu.diging.eaccpf.model.impl.LanguageUsedImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILanguageUsedTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguagesUsedTagParser;

@Component
public class LanguageUsedParser implements DescriptionTagParser, LanguagesUsedTagParser {
    
    @Autowired
    private ILanguageUsedTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "languageUsed";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getLanguagesUsed() == null) {
            desc.setLanguagesUsed(new ArrayList<>());
        }       
        desc.getLanguagesUsed().add(parseLanguagesUsed(node));
    }
    
    @Override
    public void parse(Node node, LanguagesUsed lang) {
        lang.getLangugesUsed().add(parseLanguagesUsed(node));
    }

    protected LanguageUsed parseLanguagesUsed(Node node) {
        LanguageUsed lang = new LanguageUsedImpl();
        lang.setDescriptiveNote(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), lang);
        }
        
        return lang;
    }

}
