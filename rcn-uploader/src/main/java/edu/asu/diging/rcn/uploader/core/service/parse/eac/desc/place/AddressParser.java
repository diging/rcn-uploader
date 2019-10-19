package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.place;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Address;
import edu.asu.diging.eaccpf.model.AddressLine;
import edu.asu.diging.eaccpf.model.Place;
import edu.asu.diging.eaccpf.model.impl.AddressImpl;
import edu.asu.diging.eaccpf.model.impl.AddressLineImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.PlaceTagParser;

@Component
public class AddressParser implements PlaceTagParser {
    
    @Override
    public String handlesTag() {
        return "address";
    }

    @Override
    public void parse(Node node, Place place) {
        Address address = new AddressImpl();
        address.setLocalType(((Element)node).getAttribute("localType"));
        address.setAddressLines(new ArrayList<>());
        
        NodeList lines = node.getChildNodes();
        for(int i = 0; i<lines.getLength(); i++) {
            Node line = lines.item(i);
            if (line.getNodeName().equals("addressLine")) {
                AddressLine aLine = new AddressLineImpl();
                aLine.setLine(line.getTextContent());
                aLine.setLocalType(((Element)node).getAttribute("localType"));
                address.getAddressLines().add(aLine);
            }
        }
        
        place.getAddresses().add(address);
    }

}
