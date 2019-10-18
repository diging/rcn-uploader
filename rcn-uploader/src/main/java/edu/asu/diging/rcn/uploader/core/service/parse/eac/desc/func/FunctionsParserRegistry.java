package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.func;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IFunctionsTagParserRegistry;

@Service
public class FunctionsParserRegistry implements IFunctionsTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, FunctionsTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(FunctionsTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, Functions functions) {
        FunctionsTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, functions);
        }
    }
    
}
