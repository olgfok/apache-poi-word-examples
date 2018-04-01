package com.olgfok.word.helpers;

import com.olgfok.word.dto.Footnote;
import com.olgfok.word.dto.ListItem;
import com.olgfok.word.creators.FootnoteCreator;
import com.olgfok.word.creators.ListCreator;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

public class CreateWordDocumentHelper {

    private final XWPFDocument doc;

    //todo dependency injection
    private final FootnoteCreator footnoteCreator;
    private final ListCreator listCreator;

    public CreateWordDocumentHelper() {
        this.doc = new XWPFDocument();
        footnoteCreator = new FootnoteCreator(doc);
        listCreator = new ListCreator();
    }

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
        final BigInteger numId = listCreator.createNumbering(doc, 3);

        list.forEach(item -> {
            listCreator.addParagraph(numId, doc, item.getLevel(), item.getText());
        });

    }


}
