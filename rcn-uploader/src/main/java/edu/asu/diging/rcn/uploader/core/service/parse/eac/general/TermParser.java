package edu.asu.diging.rcn.uploader.core.service.parse.eac.general;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.Function;
import edu.asu.diging.eaccpf.model.LegalStatus;
import edu.asu.diging.eaccpf.model.LocalDescription;
import edu.asu.diging.eaccpf.model.Mandate;
import edu.asu.diging.eaccpf.model.Term;
import edu.asu.diging.eaccpf.model.impl.TermImpl;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.FunctionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LegalStatusTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.LocalDescriptionTagParser;
import edu.asu.diging.rcn.uploader.core.service.parse.eac.MandateTagParser;

@Component
public class TermParser implements FunctionTagParser, LegalStatusTagParser, LocalDescriptionTagParser, MandateTagParser {
    
    
    @Override
    public String handlesTag() {
        return "term";
    }
    
    protected Term parseTerm(Node node) {
        Term term = new TermImpl();
        term.setLastDateTimeVerified(((Element)node).getAttribute("lastDateTimeVerified"));
        term.setScriptCode(((Element)node).getAttribute("scriptCode"));
        term.setTransliteration(((Element)node).getAttribute("transliteration"));
        term.setVocabularySource(((Element)node).getAttribute("vocabularySource"));
        term.setText(node.getTextContent());
        
        return term;
    }
   
    @Override
    public void parse(Node node, Function function) {
        function.setTerm(parseTerm(node));
    }

    @Override
    public void parse(Node node, LegalStatus status) {
        status.setTerm(parseTerm(node));
    }

    @Override
    public void parse(Node node, LocalDescription desc) {
        desc.setTerm(parseTerm(node));
    }

    @Override
    public void parse(Node node, Mandate mandate) {
        mandate.setTerm(parseTerm(node));
    }
}
