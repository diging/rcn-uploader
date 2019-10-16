package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.LanguageDeclaration;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.LanguageDeclarationImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILangDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class LanguageDeclarationParser implements RecordTagParser {
    
    @Autowired
    private ILangDeclTagParserRegistry languageDeclarationTagParsers;

    @Override
    public String handlesTag() {
        return "languageDeclaration";
    }

    @Override
    public void parse(Node node, Record record) {
        if (record.getLanguageDeclarations() == null) {
            record.setLanguageDeclarations(new ArrayList<LanguageDeclaration>());
        }
        LanguageDeclaration langDecl = new LanguageDeclarationImpl();
        record.getLanguageDeclarations().add(langDecl);
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            languageDeclarationTagParsers.parseRecordTag(nodeList.item(i), langDecl);
        }
    }

}
