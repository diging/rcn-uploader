package edu.asu.diging.rcn.uploader.core.service.parse.eac.langdec;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LanguageDeclaration;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILangDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguageDeclarationTagParser;

@Service
public class LanguageDeclarationParserRegistry implements ILangDeclTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, LanguageDeclarationTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(LanguageDeclarationTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, LanguageDeclaration langDecl) {
        LanguageDeclarationTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, langDecl);
        }
    }
    
}
