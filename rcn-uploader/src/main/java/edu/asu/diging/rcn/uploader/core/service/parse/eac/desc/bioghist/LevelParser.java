package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Item;
import edu.asu.diging.eaccpf.model.Level;
import edu.asu.diging.eaccpf.model.Outline;
import edu.asu.diging.eaccpf.model.impl.LevelImpl;

@Component
public class LevelParser implements OutlineTagParser {
    
    @Autowired
    private ItemTagParser itemParser;
    
    @Override
    public String handlesTag() {
        return "level";
    }

   
    @Override
    public void parse(Node node, Outline outline) {
        Level level = createLevel(node);
        outline.getLevels().add(level);
        processChildNodes(node, level);
    }
    
    public void parse(Node node, Level parentLevel) {
        Level level = createLevel(node);
        parentLevel.getSubLevels().add(level);
        processChildNodes(node, level);
    }
    
    private Level createLevel(Node node) {
        Level level = new LevelImpl();
        level.setLocalType(((Element)node).getAttribute("localType"));
        level.setItems(new ArrayList<Item>());
        level.setSubLevels(new ArrayList<>());
        return level;
    }

    private void processChildNodes(Node node, Level level) {
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            Node child = children.item(i);
            if(child.getNodeName().equals("level")) {
                parse(child, level);
            } else if (child.getNodeName().equals("item")) {
                Item item = itemParser.parseItem(child);
                level.getItems().add(item);
            }
        }
    }

}
