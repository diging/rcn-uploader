package edu.asu.diging.rcn.uploader.core.service.parse.eac.ident;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.NameEntryParallel;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.INameEntryParallelTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.NameEntryParallelTagParser;

@Service
public class NameEntryParallelParserRegistry implements INameEntryParallelTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, NameEntryParallelTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(NameEntryParallelTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, NameEntryParallel nameEntry) {
        NameEntryParallelTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, nameEntry);
        }
    }
    
}
