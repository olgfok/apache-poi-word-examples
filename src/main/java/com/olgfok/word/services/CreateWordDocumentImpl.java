package com.olgfok.word.services;

import com.olgfok.word.helpers.CreateWordDocumentHelper;
import com.olgfok.word.dto.DocumentContent;
import org.apache.log4j.Logger;

import java.io.IOException;

public class CreateWordDocumentImpl implements CreateWordDocumentService {
    private static Logger logger = Logger.getLogger(CreateWordDocumentImpl.class);
    CreateWordDocumentHelper createWordDocumentHelper = new CreateWordDocumentHelper();

    public void createDocument(String fileName, DocumentContent content) throws IOException {
        if (content.getList()!=null) {
            createWordDocumentHelper.addNumberedList(content.getList());

        }

        if (content.getFootnotes()!=null) {
            createWordDocumentHelper.addFootnotes(content.getFootnotes());

        }
        createWordDocumentHelper.write(fileName);

        logger.info(fileName + " written successully");
    }

}
