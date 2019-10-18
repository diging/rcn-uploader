package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IBiogHistTagParserRegistry;

@Service
public class BiogHistParserRegistry implements IBiogHistTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, BiogHistTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(BiogHistTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, BiogHist bio) {
        BiogHistTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, bio);
        }
    }
    
}
