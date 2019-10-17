package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.NameEntryParallel;

public interface INameEntryParallelTagParserRegistry {

    void parseRecordTag(Node node, NameEntryParallel entry);

}