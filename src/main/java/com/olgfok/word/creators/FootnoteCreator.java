package com.olgfok.word.creators;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.math.BigInteger;

public class FootnoteCreator {
    private static final String FOOTNOTE_STYLE_ID = "FootnoteStyle";

    @Autowired
    private XWPFDocument doc;


    @PostConstruct
    public void initFootnote() {
        addFootnoteReferenceStyle();

        if (doc.getFootnotes().isEmpty()) {
            doc.createFootnotes();

        }
    }
    private void addFootnoteReferenceStyle() {
        CTStyle style = CTStyle.Factory.newInstance();
        style.setStyleId(FOOTNOTE_STYLE_ID);
        style.setType(STStyleType.CHARACTER);
        style.addNewName().setVal("footnote reference");
        style.addNewBasedOn().setVal("DefaultParagraphFont");
        style.addNewUiPriority().setVal(new BigInteger("99"));
        style.addNewSemiHidden();
        style.addNewUnhideWhenUsed();
        style.addNewRPr().addNewVertAlign().setVal(STVerticalAlignRun.SUPERSCRIPT);

        doc.createStyles().addStyle(new XWPFStyle(style));
    }

    public void addFootnote(String pText, String ftnText, BigInteger id) {
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(pText);
        addFootnote(id, paragraph, ftnText);
    }

    private void addFootnote(BigInteger id, XWPFParagraph paragraph, String ftnText) {

        XWPFRun footnoteRun = paragraph.createRun();

        //footnoteRun.setText("1");
        CTFtnEdnRef ref = footnoteRun.getCTR().addNewFootnoteReference();

        footnoteRun.getCTR().addNewRPr().addNewRStyle().setVal(FOOTNOTE_STYLE_ID);
        ref.setId(id);
        CTP ctp = paragraph.getCTP();

        addFootnoteObject(ctp, id, ftnText);

    }

    private BigInteger addFootnoteObject(CTP text, BigInteger refId, String ftnText) {
        CTFtnEdn main = CTFtnEdn.Factory.newInstance();
        main.setId(refId);
        CTP ctp = main.addNewP();
        //ctp.addNewPPr().addNewPStyle().setVal("FootnoteText");

        CTR ctr = ctp.addNewR();
        ctr.addNewRPr().addNewRStyle().setVal(FOOTNOTE_STYLE_ID);
        ctr.addNewFootnoteRef();

        CTR ctr2 = ctp.addNewR();

        CTText ctText = ctr2.addNewT();

        ctText.setStringValue(ftnText);

        for (CTR textCTR : text.getRArray()) {
            for (CTText t : textCTR.getTArray()) {
                t.setSpace(SpaceAttribute.Space.PRESERVE);
            }
        }


        XWPFFootnote footnote = doc.addFootnote(main);
        return refId;
    }

}
