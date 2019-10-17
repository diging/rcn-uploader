package edu.asu.diging.rcn.uploader.core.service.parse.eac.dates;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.UseDates;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IUseDatesTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.UseDatesTagParser;

@Service
public class UseDatesParserRegistry implements IUseDatesTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, UseDatesTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(UseDatesTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, UseDates useDates) {
        UseDatesTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, useDates);
        }
    }
    
}
