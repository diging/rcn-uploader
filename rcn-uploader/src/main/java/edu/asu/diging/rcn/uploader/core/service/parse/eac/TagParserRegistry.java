package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Record;

@Service
public class TagParserRegistry implements ITagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, RecordTagParser> recordTagParser;
    
    @PostConstruct
    public void init() {
        recordTagParser = new HashMap<>();
        ctx.getBeansOfType(RecordTagParser.class).values()
                .forEach(h -> recordTagParser.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, Record record) {
        RecordTagParser handler = recordTagParser.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, record);
        }
    }
    
}
