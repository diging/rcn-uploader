package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.exists;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.Term;
import edu.asu.diging.eaccpf.model.impl.TermImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;

@Component
public class TermParser implements FunctionTagParser {
    
    
    @Override
    public String handlesTag() {
        return "term";
    }

   
    @Override
    public void parse(Node node, Function function) {
        Term term = new TermImpl();
        term.setLastDateTimeVerified(((Element)node).getAttribute("lastDateTimeVerified"));
        term.setScriptCode(((Element)node).getAttribute("scriptCode"));
        term.setTransliteration(((Element)node).getAttribute("transliteration"));
        term.setVocabularySource(((Element)node).getAttribute("vocabularySource"));
        term.setText(node.getTextContent());
        
        function.setTerm(term);
    }


}
