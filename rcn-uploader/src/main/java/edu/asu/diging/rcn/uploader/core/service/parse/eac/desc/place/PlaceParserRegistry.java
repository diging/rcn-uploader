package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.place;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IPlaceTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;

@Service
public class PlaceParserRegistry implements IPlaceTagParserRegistry {

    @Autowired
    private ApplicationContext ctx;

    private Map<String, PlaceTagParser> tagParsers;
    
    @PostConstruct
    public void init() {
        tagParsers = new HashMap<>();
        ctx.getBeansOfType(PlaceTagParser.class).values()
                .forEach(h -> tagParsers.put(h.handlesTag(), h));
    }

    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.ITagParserRegistry#parseRecordTag(org.w3c.dom.Node, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parseRecordTag(Node node, Place place) {
        PlaceTagParser handler = tagParsers.get(node.getNodeName());
        if (handler != null) {
            handler.parse(node, place);
        }
    }
    
}
