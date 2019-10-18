package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ChronItem;

@Component
public class EventParser implements ChronItemTagParser {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String handlesTag() {
        return "event";
    }

   
    @Override
    public void parse(Node node, ChronItem item) {
        item.setEvent(node.getTextContent());
    }

}
