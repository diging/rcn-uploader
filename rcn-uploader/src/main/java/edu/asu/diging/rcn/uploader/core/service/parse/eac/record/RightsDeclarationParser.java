package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.RightsDeclaration;
import edu.asu.diging.eaccpf.model.impl.RightsDeclarationImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IRightsDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class RightsDeclarationParser implements RecordTagParser {
    
    @Autowired
    private IRightsDeclTagParserRegistry rightsDeclarationTagParsers;

    @Override
    public String handlesTag() {
        return "rightsDeclaration";
    }

    @Override
    public void parse(Node node, Record record) {
        if (record.getRightsDeclarations() == null) {
            record.setRightsDeclarations(new ArrayList<RightsDeclaration>());
        }
        RightsDeclaration rights = new RightsDeclarationImpl();
        rights.setLocalType(((Element)node).getAttribute("localType"));
        record.getRightsDeclarations().add(rights);
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            rightsDeclarationTagParsers.parseRecordTag(nodeList.item(i), rights);
        }
    }

}
