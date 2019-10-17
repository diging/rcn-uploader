package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.NameEntry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.INameEntryTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryTagParser;

@Service
public class NameEntryParserRegistry implements INameEntryTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, NameEntryTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(NameEntryTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, NameEntry nameEntry) {
        NameEntryTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, nameEntry);
        }
    }
    
}
