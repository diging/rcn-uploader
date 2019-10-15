package edu.asu.diging.rcn.uploader.core.service;

import edu.asu.diging.rcn.uploader.core.exception.AuthmatcherCommunicationException;

public interface IRcnMatcherConnector {

    String getUploadeFile(String apiToken) throws AuthmatcherCommunicationException;

}