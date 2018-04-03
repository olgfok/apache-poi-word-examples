package com.olgfok.word.creators;

import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

/**
 * Creates numbered lists
 */
public class ListCreator {

    @Autowired
    private XWPFDocument doc;
    public BigInteger createNumbering(Integer levelCount) {
        XWPFNumbering numbering = doc.createNumbering();

        CTAbstractNum ctAbstractNum = CTAbstractNum.Factory.newInstance();
        ctAbstractNum.setAbstractNumId(BigInteger.ZERO);
        CTMultiLevelType ctMultiLevelType = ctAbstractNum.addNewMultiLevelType();
        ctMultiLevelType.setVal(STMultiLevelType.MULTILEVEL);

        addCTLevels(ctAbstractNum, levelCount);
        XWPFAbstractNum abstractNum = new XWPFAbstractNum(ctAbstractNum);
        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
        BigInteger numID = numbering.addNum(abstractNumID);
        return numID;

    }

    private void addCTLevels(CTAbstractNum ctAbstractNum, Integer levelCount) {
        for (int level = 0; level < levelCount; level++) {
            CTLvl ctLvl = ctAbstractNum.addNewLvl();
            ctLvl.setIlvl(BigInteger.valueOf(level));
            ctLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
            ctLvl.addNewLvlText().setVal(getLvlTxtVal(level));
            ctLvl.addNewStart().setVal(BigInteger.ONE);
        }

    }

    private String getLvlTxtVal(Integer level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= level; i++) {
            sb.append("%").append(i + 1).append(".");

        }
        return sb.toString();
    }

    public void addParagraph(BigInteger numId, Integer level,
                                     String rowName) {
        XWPFParagraph p = doc.createParagraph();
        p.setNumID(numId);
        //sets level to paragraph
        CTDecimalNumber ctDecimalNumber = p.getCTP().getPPr().getNumPr().addNewIlvl();
        ctDecimalNumber.setVal(BigInteger.valueOf(level));//sets level value
        p.setIndentationLeft(level * 360);
        p.createRun().setText(rowName);
    }

}
