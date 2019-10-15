package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class RecordIdParser implements RecordTagParser {
    
    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.TagParser#handlesTag()
     */
    @Override
    public String handlesTag() {
        return "recordId";
    }
    
    /* (non-Javadoc)
     * @see edu.asu.diging.rcn.uploader.core.service.parse.eac.TagParser#parse(org.w3c.dom.Element, edu.asu.diging.eaccpf.model.Record)
     */
    @Override
    public void parse(Node node, Record record) {
        record.setRecordId(node.getTextContent());
    }
}
