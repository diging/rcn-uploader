package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.ExistDates;

public interface IExistDatesTagParserRegistry {

    void parseRecordTag(Node node, ExistDates dates);

}