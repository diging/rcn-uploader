package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

public class OtherRecordIdParser implements RecordTagParser {

    @Override
    public String handlesTag() {
        return "otherRecordId";
    }

    @Override
    public void parse(Node node, Record record) {
        record.setOtherRecordId(node.getTextContent());
    }

}
