package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.ConventionDeclaration;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.ConventionDeclarationImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IConvDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class ConventionDeclarationParser implements RecordTagParser {
    
    @Autowired
    private IConvDeclTagParserRegistry conventionDeclarationTagParsers;

    @Override
    public String handlesTag() {
        return "conventionDeclaration";
    }

    @Override
    public void parse(Node node, Record record) {
        if (record.getConventionDeclarations() == null) {
            record.setConventionDeclarations(new ArrayList<ConventionDeclaration>());
        }
        ConventionDeclaration convDecl = new ConventionDeclarationImpl();
        record.getConventionDeclarations().add(convDecl);
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            conventionDeclarationTagParsers.parseRecordTag(nodeList.item(i), convDecl);
        }
    }

}
