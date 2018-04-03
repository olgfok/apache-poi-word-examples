package com.olgfok.word.services;

import com.olgfok.word.helpers.CreateWordDocumentHelper;
import com.olgfok.word.elements.DocumentContent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class CreateWordDocumentServiceImpl implements CreateWordDocumentService {
    private static Logger logger = Logger.getLogger(CreateWordDocumentServiceImpl.class);

    @Autowired
    private CreateWordDocumentHelper createWordDocumentHelper;

    public void createDocument(String fileName, DocumentContent content) throws IOException {
        if (content.getList()!=null) {
            createWordDocumentHelper.addNumberedList(content.getList());

        }

        if (content.getFootnotes()!=null) {
            createWordDocumentHelper.addFootnotes(content.getFootnotes());

        }

        if (content.getTables()!=null) {
            createWordDocumentHelper.addTables(content.getTables());
        }
        createWordDocumentHelper.write(fileName);

        logger.info(fileName + " written successully");
    }

}
