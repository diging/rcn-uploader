package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.asu.diging.eaccpf.model.Description;
import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.Functions;
import edu.asu.diging.eaccpf.model.impl.FunctionImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.DescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionsTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.IFunctionTagParserRegistry;

@Component
public class FunctionParser implements DescriptionTagParser, FunctionsTagParser {
    
    @Autowired
    private IFunctionTagParserRegistry parserRegistry;

    @Override
    public String handlesTag() {
        return "function";
    }

    @Override
    public void parse(Node node, Description desc) {
        if(desc.getFunctions() == null) {
            desc.setFunctions(new ArrayList<>());
        }       
        desc.getFunctions().add(parseFunction(node));
    }

    @Override
    public void parse(Node node, Functions functions) {
        functions.getFunctions().add(parseFunction(node));
    }

    protected Function parseFunction(Node node) {
        Function function = new FunctionImpl();
        function.setCitations(new ArrayList<>());
        function.setDateRanges(new ArrayList<>());
        function.setDates(new ArrayList<>());
        function.setDateSets(new ArrayList<>());
        function.setDescriptiveNote(new ArrayList<>());
        function.setPlaceEntries(new ArrayList<>());
        
        NodeList children = node.getChildNodes();
        for(int i=0; i<children.getLength(); i++) {
            parserRegistry.parseRecordTag(children.item(i), function);
        }
        
        return function;
    }
}
