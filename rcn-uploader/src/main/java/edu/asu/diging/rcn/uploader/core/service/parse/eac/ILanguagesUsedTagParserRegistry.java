package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LanguagesUsed;

public interface ILanguagesUsedTagParserRegistry {

    void parseRecordTag(Node node, LanguagesUsed langs);

}