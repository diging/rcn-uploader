package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.place;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Places;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IPlacesTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlacesTagParser;

@Service
public class PlacesParserRegistry implements IPlacesTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, PlacesTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(PlacesTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, Places places) {
        PlacesTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, places);
        }
    }
    
}
