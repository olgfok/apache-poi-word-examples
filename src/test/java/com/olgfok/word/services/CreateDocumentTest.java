package com.olgfok.word.services;

import com.olgfok.spring.config.AppConfig;
import com.olgfok.word.elements.DocumentContent;
import com.olgfok.word.elements.Footnote;
import com.olgfok.word.elements.ListItem;
import com.olgfok.word.elements.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class CreateDocumentTest {

    @Autowired
    private CreateWordDocumentService wordDocumentService;
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
        wordDocumentService.createDocument("WordDocumentWithNestedList.docx", content);
    }

    @Test
    public void createWordDocumentWithFootnotes() throws IOException {

        List<Footnote> footnotes = new ArrayList<>();
        footnotes.add(new Footnote("Some text with footnote", "The first footnote"));
        footnotes.add(new Footnote("Another text with footnote", "The second footnote"));
        DocumentContent content = new DocumentContent();
        content.setFootnotes(footnotes);
        wordDocumentService.createDocument("WordDocumentWithFootnotes.docx", content);
    }

    @Test
    public void createWordDocumentWithTable() throws IOException {
        DocumentContent content = new DocumentContent();

        List<String> headers = Arrays.asList("First column","Second column",
                "Third column","Fourth column");

        List<Table> tables =new ArrayList<>();
        Table table = new Table();
        table.setColCount(3);
        table.setRowCount(4);
        table.setHeaders(headers);

        tables.add(table);
        content.setTables(tables);


        wordDocumentService.createDocument("WordDocumentWithTable.docx", content);
    }
}
