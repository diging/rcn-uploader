package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.BiogHist;
import edu.asu.diging.eaccpf.model.Item;
import edu.asu.diging.eaccpf.model.impl.ItemListImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.BiogHistTagParser;

@Component
public class ItemListParser implements BiogHistTagParser {
    
    @Autowired
    private ItemTagParser itemParser;
    
    @Override
    public String handlesTag() {
        return "list";
    }

    @Override
    public void parse(Node node, BiogHist bio) {
        bio.setItemList(new ItemListImpl());
        bio.getItemList().setLocalType(((Element)node).getAttribute("localType"));
        bio.getItemList().setItems(new ArrayList<Item>());
        
        NodeList items = node.getChildNodes();
        for(int i=0; i<items.getLength(); i++) {
            Node itemNode = items.item(i);
            if(itemNode.getNodeName().equals("item")) {
                Item item = itemParser.parseItem(itemNode);
                bio.getItemList().getItems().add(item);
            }
        }
    }

}
