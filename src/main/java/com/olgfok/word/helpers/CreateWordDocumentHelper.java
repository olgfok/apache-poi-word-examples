package com.olgfok.word.helpers;

import com.olgfok.word.creators.TableCreator;
import com.olgfok.word.elements.Footnote;
import com.olgfok.word.elements.ListItem;
import com.olgfok.word.creators.FootnoteCreator;
import com.olgfok.word.creators.ListCreator;
import com.olgfok.word.elements.Table;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

public class CreateWordDocumentHelper {

    @Autowired
    private XWPFDocument doc;

    @Autowired
    private FootnoteCreator footnoteCreator;

    @Autowired
    private ListCreator listCreator;

    @Autowired
    private TableCreator tableCreator;


    public void write(String fileName) throws IOException {

        doc.write(new FileOutputStream(fileName));
    }

    public void addFootnotes(List<Footnote> footnotes) {
        for (int i = 0; i < footnotes.size(); i++) {
            Footnote footnote = footnotes.get(i);
            footnoteCreator.addFootnote(footnote.getParagraphText(), footnote.getFootnoteText(), BigInteger.valueOf(i));
        }

    }

    public void addNumberedList(List<ListItem> list) {
        final BigInteger numId = listCreator.createNumbering(3);

        list.forEach(item -> {
            listCreator.addParagraph(numId, item.getLevel(), item.getText());
        });

    }

    public void addTables(List<Table> tables) {
        tables.forEach(t ->
                tableCreator.addTable(t.getHeaders(), t.getRowCount(), t.getColCount()));
    }


}
