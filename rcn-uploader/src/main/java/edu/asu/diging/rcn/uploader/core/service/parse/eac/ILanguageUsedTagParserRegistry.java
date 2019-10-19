package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.LanguageUsed;

public interface ILanguageUsedTagParserRegistry {

    void parseRecordTag(Node node, LanguageUsed lang);

}