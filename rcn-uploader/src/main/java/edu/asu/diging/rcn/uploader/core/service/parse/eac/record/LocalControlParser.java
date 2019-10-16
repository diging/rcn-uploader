package edu.asu.diging.rcn.uploader.core.service.parse.eac.record;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.LanguageDeclaration;
import edu.asu.diging.eaccpf.model.LocalControl;
import edu.asu.diging.eaccpf.model.Record;
import edu.asu.diging.eaccpf.model.impl.LanguageDeclarationImpl;
import edu.asu.diging.eaccpf.model.impl.LocalControlImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILangDeclTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ILocalControlTagParserRegistry;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RecordTagParser;

@Component
public class LocalControlParser implements RecordTagParser {
    
    @Autowired
    private ILocalControlTagParserRegistry localControlTagParsers;

    @Override
    public String handlesTag() {
        return "localControl";
    }

    @Override
    public void parse(Node node, Record record) {
        if (record.getLocalControls() == null) {
            record.setLocalControls(new ArrayList<LocalControl>());
        }
        LocalControl control = new LocalControlImpl();
        record.getLocalControls().add(control);
        NodeList nodeList = node.getChildNodes();
        for(int i = 0; i<nodeList.getLength(); i++) {
            localControlTagParsers.parseRecordTag(nodeList.item(i), control);
        }
    }

}
