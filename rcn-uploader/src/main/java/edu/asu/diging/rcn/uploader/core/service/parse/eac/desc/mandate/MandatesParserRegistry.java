package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.mandate;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Mandates;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IMandatesTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandatesTagParser;

@Service
public class MandatesParserRegistry implements IMandatesTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, MandatesTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(MandatesTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, Mandates mandates) {
        MandatesTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, mandates);
        }
    }
    
}
