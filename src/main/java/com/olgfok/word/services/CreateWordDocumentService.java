package com.olgfok.word.services;

import com.olgfok.word.dto.DocumentContent;

import java.io.IOException;

public interface CreateWordDocumentService {
    void createDocument(String fileName, DocumentContent listItem) throws IOException;
}
