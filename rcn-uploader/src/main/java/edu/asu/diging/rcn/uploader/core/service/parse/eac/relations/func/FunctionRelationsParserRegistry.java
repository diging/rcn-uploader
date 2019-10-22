package edu.asu.diging.rcn.uploader.core.service.parse.eac.relations.func;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.FunctionRelation;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionRelationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IFunctionRelationTagParserRegistry;

@Service
public class FunctionRelationsParserRegistry implements IFunctionRelationTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, FunctionRelationsTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(FunctionRelationsTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, FunctionRelation relations) {
        FunctionRelationsTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, relations);
        }
    }
    
}
