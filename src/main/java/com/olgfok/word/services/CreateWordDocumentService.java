package com.olgfok.word.services;

import com.olgfok.word.elements.DocumentContent;

import java.io.IOException;

public interface CreateWordDocumentService {
    void createDocument(String fileName, DocumentContent listItem) throws IOException;
}
