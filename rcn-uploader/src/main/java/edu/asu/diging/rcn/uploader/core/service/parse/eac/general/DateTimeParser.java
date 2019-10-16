package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.DateTime;

public abstract class DateTimeParser {

    
    protected void fillDateTime(Node node, DateTime datetime) {
        datetime.setDatetime(node.getTextContent());
        datetime.setStandard(((Element)node).getAttribute("standardDateTime"));
    }

}
