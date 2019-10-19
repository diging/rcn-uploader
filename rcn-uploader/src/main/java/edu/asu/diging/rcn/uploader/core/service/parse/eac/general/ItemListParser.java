package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.GeneralContext;
import edu.asu.diging.eaccpf.model.Item;
import edu.asu.diging.eaccpf.model.ItemList;
import edu.asu.diging.eaccpf.model.LegalStatuses;
import edu.asu.diging.eaccpf.model.LocalDescriptions;
import edu.asu.diging.eaccpf.model.Mandates;
import edu.asu.diging.eaccpf.model.Occupations;
import edu.asu.diging.eaccpf.model.Places;
import edu.asu.diging.eaccpf.model.StructureOrGenealogy;
import edu.asu.diging.eaccpf.model.impl.ItemListImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.GeneralContextTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandatesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.OccupationsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlacesTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.StructureOrGenealogyTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist.ItemTagParser;

@Component
public class ItemListParser implements BiogHistTagParser, FunctionsTagParser, GeneralContextTagParser,
        LegalStatusesTagParser, LocalDescriptionsTagParser, MandatesTagParser, OccupationsTagParser, PlacesTagParser,
        StructureOrGenealogyTagParser {

    @Autowired
    private ItemTagParser itemParser;

    @Override
    public String handlesTag() {
        return "list";
    }

    protected ItemList parseItemList(Node node) {
        ItemList list = new ItemListImpl();
        list.setLocalType(((Element) node).getAttribute("localType"));
        list.setItems(new ArrayList<Item>());

        NodeList items = node.getChildNodes();
        for (int i = 0; i < items.getLength(); i++) {
            Node itemNode = items.item(i);
            if (itemNode.getNodeName().equals("item")) {
                Item item = itemParser.parseItem(itemNode);
                list.getItems().add(item);
            }
        }

        return list;
    }

    @Override
    public void parse(Node node, BiogHist bio) {
        bio.setItemList(parseItemList(node));
    }

    @Override
    public void parse(Node node, Functions functions) {
        functions.getItemLists().add(parseItemList(node));
    }

    @Override
    public void parse(Node node, GeneralContext context) {
        context.getItemLists().add(parseItemList(node));
    }

    @Override
    public void parse(Node node, LegalStatuses status) {
        status.getItemList().add(parseItemList(node));
    }

    @Override
    public void parse(Node node, LocalDescriptions desc) {
        desc.getItemList().add(parseItemList(node));
    }

    @Override
    public void parse(Node node, Mandates mandates) {
        mandates.getItemLists().add(parseItemList(node));
    }

    @Override
    public void parse(Node node, Occupations occupations) {
        occupations.getItemLists().add(parseItemList(node));
    }

    @Override
    public void parse(Node node, Places places) {
        places.getItemLists().add(parseItemList(node));
    }

    @Override
    public void parse(Node node, StructureOrGenealogy structure) {
        structure.getItemLists().add(parseItemList(node));
    }

}
