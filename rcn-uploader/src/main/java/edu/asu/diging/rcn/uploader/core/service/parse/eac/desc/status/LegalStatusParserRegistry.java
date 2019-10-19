package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.status;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILegalStatusTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusTagParser;

@Service
public class LegalStatusParserRegistry implements ILegalStatusTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, LegalStatusTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(LegalStatusTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, LegalStatus status) {
        LegalStatusTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, status);
        }
    }
    
}
