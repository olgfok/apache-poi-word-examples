package com.olgfok.word.services;

import com.olgfok.word.dto.DocumentContent;
import com.olgfok.word.dto.Footnote;
import com.olgfok.word.dto.ListItem;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateDocumentTest {

    @Test
    public void createWordDocumentWithNestedList() throws IOException {
        DocumentContent content = new DocumentContent();
        List<ListItem> list = new ArrayList<>();
        list.add(new ListItem(0, "Item 1"));
        list.add(new ListItem(1, "Item 1.1"));
        list.add(new ListItem(1, "Item 1.2"));
        list.add(new ListItem(2, "Item 1.2.1"));

        list.add(new ListItem(0, "Item 2"));
        list.add(new ListItem(1, "Item 2.1"));
        list.add(new ListItem(2, "Item 2.1.1"));
        list.add(new ListItem(1, "Item 2.2"));

        content.setList(list);
        new CreateWordDocumentImpl().createDocument("WordDocumentWithNestedList.docx", content);
    }

    @Test
    public void createWordDocumentWithFootnotes() throws IOException {

        List<Footnote> footnotes = new ArrayList<>();
        footnotes.add(new Footnote("Some text with footnote", "The first footnote"));
        footnotes.add(new Footnote("Another text with footnote", "The second footnote"));
        DocumentContent content = new DocumentContent();
        content.setFootnotes(footnotes);
        new CreateWordDocumentImpl().createDocument("WordDocumentWithFootnotes.docx", content);
    }
}
