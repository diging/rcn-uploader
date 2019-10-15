package edu.asu.diging.rcn.uploader.core.service.parse.eac.condec;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.ConventionDeclaration;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ConventionDeclarationTagParser;

@Component
public class DescriptiveNoteParser implements ConventionDeclarationTagParser {

    @Override
    public String handlesTag() {
        return "descriptiveNote";
    }

    @Override
    public void parse(Node node, ConventionDeclaration declaration) {
        declaration.setDescriptiveNote(new ArrayList<String>());
        NodeList nodeList = node.getChildNodes();
        for (int i=0; i<nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            declaration.getDescriptiveNote().add(child.getTextContent());
        }
    }

}
