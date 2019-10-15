package edu.asu.diging.rcn.uploader.core.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.springframework.stereotype.Service;

import edu.asu.diging.rcn.uploader.core.exception.IteratorCreationException;
import edu.asu.diging.rcn.uploader.core.service.FileHandler;
import edu.asu.diging.rcn.uploader.core.service.IHandlerRegistry;
import edu.asu.diging.rcn.uploader.core.service.RecordIterator;
import edu.asu.diging.rcn.uploader.core.service.parse.MultipleEntryIterator;

@Service
public class ZipFileHandler implements FileHandler {
    
    private String CONTENT_FOLDER_NAME = "contents";

    @Override
    public boolean canHandle(String path) {
        if (path.toLowerCase().endsWith(".zip")) {
            return true;
        }
        return false;
    }

    @Override
    public RecordIterator getIterator(String path, IHandlerRegistry callback, String datasetId)
            throws IteratorCreationException {
        try (ZipFile zipFile = new ZipFile(new File(path))) {
            MultipleEntryIterator iterator = new MultipleEntryIterator();
            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            String storageFolder = new File(path).getParent() + File.separator + CONTENT_FOLDER_NAME;
            new File(storageFolder).mkdir();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.isDirectory()) {
                    String destPath = storageFolder + File.separator + entry.getName();
                    File file = new File(destPath);
                    file.mkdirs();
                } else {
                    String destPath = storageFolder + File.separator + entry.getName();

                    try (InputStream inputStream = zipFile.getInputStream(entry);
                            FileOutputStream outputStream = new FileOutputStream(destPath);) {
                        int data = inputStream.read();
                        while (data != -1) {
                            outputStream.write(data);
                            data = inputStream.read();
                        }
                    }

                    iterator.addIterator(callback.getRecordIterator(datasetId, destPath));
                }
            }

            return iterator;
        } catch (ZipException e) {
            throw new IteratorCreationException("Could not read zip file.", e);
        } catch (IOException e) {
            throw new IteratorCreationException("Could not read zip file.", e);
        }
    }

}