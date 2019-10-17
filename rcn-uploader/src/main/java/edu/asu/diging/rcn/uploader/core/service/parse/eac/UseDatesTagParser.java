package edu.asu.diging.rcn.uploader.core.service.parse.eac;

import org.w3c.dom.Node;

import edu.asu.diging.eaccpf.model.UseDates;

public interface UseDatesTagParser {

    String handlesTag();

    void parse(Node node, UseDates useDates);

}