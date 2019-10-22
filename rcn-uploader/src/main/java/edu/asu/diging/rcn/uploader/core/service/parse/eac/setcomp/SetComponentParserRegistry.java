package edu.asu.diging.rcn.uploader.core.service.parse.eac.setcomp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.SetComponent;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ISetComponentTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.SetComponentTagParser;

@Service
public class SetComponentParserRegistry implements ISetComponentTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, SetComponentTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(SetComponentTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, SetComponent comp) {
        SetComponentTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, comp);
        }
    }
    
}
