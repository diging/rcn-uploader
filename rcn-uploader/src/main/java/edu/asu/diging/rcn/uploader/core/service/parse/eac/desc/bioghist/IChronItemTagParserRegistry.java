package edu.asu.diging.rcn.uploader.core.service.parse.eac.desc.bioghist;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ChronItem;

public interface IChronItemTagParserRegistry {

    void parseRecordTag(Node node, ChronItem item);

}