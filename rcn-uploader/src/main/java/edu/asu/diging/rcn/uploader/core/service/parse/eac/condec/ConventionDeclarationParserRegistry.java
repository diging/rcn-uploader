package edu.asu.diging.rcn.uploader.core.service.parse.eac.condec;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ConventionDeclaration;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ConventionDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IConvDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry;

@Service
public class ConventionDeclarationParserRegistry implements IConvDeclTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, ConventionDeclarationTagParser> tagParser;
    
    @PostConstruct
    public void init() {
        tagParser = new HashMap<>();
        ctx.getBeansOfType(ConventionDeclarationTagParser.class).values()
                .forEach(h -> tagParser.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, ConventionDeclaration convDecl) {
        ConventionDeclarationTagParser handler = tagParser.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, convDecl);
        }
    }
    
}
