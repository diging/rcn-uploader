package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.ConventionDeclaration;
import edu.asu.diging.eaccpf.model.CpfRelation;
import edu.asu.diging.eaccpf.model.ExistDates;
import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.FunctionRelation;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.Identity;
import edu.asu.diging.eaccpf.model.LanguageUsed;
import edu.asu.diging.eaccpf.model.LanguagesUsed;
import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.eaccpf.model.LegalStatuses;
import edu.asu.diging.eaccpf.model.LocalDescription;
import edu.asu.diging.eaccpf.model.LocalDescriptions;
import edu.asu.diging.eaccpf.model.LocalTypeDeclaration;
import edu.asu.diging.eaccpf.model.MaintenanceAgency;
import edu.asu.diging.eaccpf.model.Mandate;
import edu.asu.diging.eaccpf.model.Mandates;
import edu.asu.diging.eaccpf.model.Occupations;
import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.Places;
import edu.asu.diging.eaccpf.model.ResourceRelation;
import edu.asu.diging.eaccpf.model.RightsDeclaration;
import edu.asu.diging.eaccpf.model.SetComponent;
import edu.asu.diging.eaccpf.model.Source;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ConventionDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.CpfRelationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ExistDatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionRelationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IdentityTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguageUsedTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LanguagesUsedTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalTypeDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MaintAgencyTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandateTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.OccupationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlacesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.ResourceRelationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.RightsDeclarationTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.SetComponentTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.SourceTagParser;

@Component
public class DescriptiveNoteParser implements ConventionDeclarationTagParser, LocalTypeDeclarationTagParser,
        MaintAgencyTagParser, RightsDeclarationTagParser, SourceTagParser, IdentityTagParser, ExistDatesTagParser,
        FunctionTagParser, FunctionsTagParser, LanguageUsedTagParser, LanguagesUsedTagParser, LegalStatusTagParser,
        LegalStatusesTagParser, LocalDescriptionTagParser, LocalDescriptionsTagParser, MandateTagParser,
        MandatesTagParser, OccupationsTagParser, PlaceTagParser, PlacesTagParser, CpfRelationTagParser,
        FunctionRelationsTagParser, ResourceRelationTagParser, SetComponentTagParser {

    @Override
    public String handlesTag() {
        return "descriptiveNote";
    }

    private void addDescriptiveNotes(Node node, List<String> notes) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            notes.add(child.getTextContent());
        }
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

    @Override
    public void parse(Node node, LanguageUsed lang) {
        addDescriptiveNotes(node, lang.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, LanguagesUsed langs) {
        addDescriptiveNotes(node, langs.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, LegalStatus status) {
        addDescriptiveNotes(node, status.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, LegalStatuses status) {
        addDescriptiveNotes(node, status.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, LocalDescription desc) {
        addDescriptiveNotes(node, desc.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, LocalDescriptions desc) {
        addDescriptiveNotes(node, desc.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, Mandate mandate) {
        addDescriptiveNotes(node, mandate.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, Mandates mandates) {
        addDescriptiveNotes(node, mandates.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, Occupations occupations) {
        addDescriptiveNotes(node, occupations.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, Place place) {
        addDescriptiveNotes(node, place.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, Places places) {
        addDescriptiveNotes(node, places.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, CpfRelation relations) {
        addDescriptiveNotes(node, relations.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, FunctionRelation relations) {
        addDescriptiveNotes(node, relations.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, ResourceRelation relations) {
        addDescriptiveNotes(node, relations.getDescriptiveNote());
    }

    @Override
    public void parse(Node node, SetComponent component) {
        addDescriptiveNotes(node, component.getDescriptiveNote());
    }

}
