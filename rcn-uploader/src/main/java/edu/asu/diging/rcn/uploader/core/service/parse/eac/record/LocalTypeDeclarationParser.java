package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.LanguageDeclaration;
import edu.asu.diging.eaccpf.model.LocalTypeDeclaration;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.LanguageDeclarationImpl;
import edu.asu.diging.eaccpf.model.impl.LocalTypeDeclarationImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILangDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILocalTypeDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class LocalTypeDeclarationParser implements RecordTagParser {
    
    @Autowired
    private ILocalTypeDeclTagParserRegistry localTypeDeclarationTagParsers;

    @Override
    public String handlesTag() {
        return "localTypeDeclaration";
    }

    @Override
    public void parse(Node node, Record record) {
        if (record.getLocalTypeDeclarations() == null) {
            record.setLocalTypeDeclarations(new ArrayList<LocalTypeDeclaration>());
        }
        LocalTypeDeclaration localTypeDecl = new LocalTypeDeclarationImpl();
        record.getLocalTypeDeclarations().add(localTypeDecl);
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            localTypeDeclarationTagParsers.parseRecordTag(nodeList.item(i), localTypeDecl);
        }
    }

}
