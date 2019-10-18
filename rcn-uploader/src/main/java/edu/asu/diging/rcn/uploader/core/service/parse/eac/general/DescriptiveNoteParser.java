package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.ConventionDeclaration;
import edu.asu.diging.eaccpf.model.ExistDates;
import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.Identity;
import edu.asu.diging.eaccpf.model.LocalTypeDeclaration;
import edu.asu.diging.eaccpf.model.MaintenanceAgency;
import edu.asu.diging.eaccpf.model.RightsDeclaration;
import edu.asu.diging.eaccpf.model.Source;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ConventionDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ExistDatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IdentityTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalTypeDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MaintAgencyTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RightsDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.SourceTagParser;

@Component
public class DescriptiveNoteParser implements ConventionDeclarationTagParser, LocalTypeDeclarationTagParser,
        MaintAgencyTagParser, RightsDeclarationTagParser, SourceTagParser, IdentityTagParser, ExistDatesTagParser,
        FunctionTagParser, FunctionsTagParser {

    @Override
    public String handlesTag() {
        return "descriptiveNote";
    }

    @Override
    public void parse(Node node, ConventionDeclaration declaration) {
        declaration.setDescriptiveNote(new ArrayList<String>());
        addDescriptiveNotes(node, declaration.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, LocalTypeDeclaration declaration) {
        declaration.setDescriptiveNote(new ArrayList<String>());
        addDescriptiveNotes(node, declaration.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, MaintenanceAgency agency) {
        agency.setDescriptiveNote(new ArrayList<String>());
        addDescriptiveNotes(node, agency.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, RightsDeclaration rights) {
        rights.setDescriptiveNote(new ArrayList<String>());
        addDescriptiveNotes(node, rights.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, Source source) {
        source.setDescriptiveNote(new ArrayList<String>());
        addDescriptiveNotes(node, source.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, Identity identity) {
        identity.setDescriptiveNote(new ArrayList<String>());
        addDescriptiveNotes(node, identity.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, ExistDates dates) {
        addDescriptiveNotes(node, dates.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, Function function) {
        addDescriptiveNotes(node, function.getDescriptiveNote());
    }
    
    @Override
    public void parse(Node node, Functions functions) {
        addDescriptiveNotes(node, functions.getDescriptiveNote());
    }

    private void addDescriptiveNotes(Node node, List<String> notes) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            notes.add(child.getTextContent());
        }
    }


}
