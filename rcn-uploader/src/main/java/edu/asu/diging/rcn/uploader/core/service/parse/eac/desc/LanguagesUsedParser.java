package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.LanguagesUsed;
import edu.asu.diging.eaccpf.model.impl.LanguagesUsedImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILanguagesUsedTagParserRegistry;

@Component
public class LanguagesUsedParser implements DescriptionTagParser {
    
    @Autowired
    private ILanguagesUsedTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "languagesUsed";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getLanguagesUsedGroups() == null) {
            desc.setLanguagesUsedGroups(new ArrayList<>());
        }       
        desc.getLanguagesUsedGroups().add(parseLanguagesUsed(node));
    }

    protected LanguagesUsed parseLanguagesUsed(Node node) {
        LanguagesUsed lang = new LanguagesUsedImpl();
        lang.setDescriptiveNote(new ArrayList<>());
        lang.setLangugesUsed(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), lang);
        }
        
        return lang;
    }
}
