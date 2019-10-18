package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.exists;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ExistDates;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ExistDatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IExistDatesTagParserRegistry;

@Service
public class ExistDatesParserRegistry implements IExistDatesTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, ExistDatesTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(ExistDatesTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, ExistDates dates) {
        ExistDatesTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, dates);
        }
    }
    
}
