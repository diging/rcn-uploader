package edu.asu.diging.rcn.uploader.core.service.parse.eac.control;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LocalControl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILocalControlTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalControlTagParser;

@Service
public class LocalControlParserRegistry implements ILocalControlTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, LocalControlTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(LocalControlTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, LocalControl control) {
        LocalControlTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, control);
        }
    }
    
}
