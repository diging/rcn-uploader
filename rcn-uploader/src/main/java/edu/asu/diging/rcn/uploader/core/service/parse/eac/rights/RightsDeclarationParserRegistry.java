package edu.asu.diging.rcn.uploader.core.service.parse.eac.rights;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.RightsDeclaration;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IRightsDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RightsDeclarationTagParser;

@Service
public class RightsDeclarationParserRegistry implements IRightsDeclTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, RightsDeclarationTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(RightsDeclarationTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, RightsDeclaration langDecl) {
        RightsDeclarationTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, langDecl);
        }
    }
    
}
