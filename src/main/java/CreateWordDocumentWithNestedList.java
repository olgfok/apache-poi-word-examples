import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class CreateWordDocumentWithNestedList {

    public static void main(String[] args) throws IOException {
        XWPFDocument document = new XWPFDocument();

        BigInteger numId = createNumbering(document, 3);

        addParagraph(numId, document, 0, "First level row 1");
        addParagraph(numId, document, 1, "Second level row 1.1");
        addParagraph(numId, document, 2, "Third level row 1.1.1");
        addParagraph(numId, document, 2, "Third level row 1.1.2");
        addParagraph(numId, document, 1, "Second level row 1.2");

        addParagraph(numId, document, 0, "First level row 2");
        addParagraph(numId, document, 1, "Second level row 2.1");

        FileOutputStream out = new FileOutputStream("CreateWordDocumentWithNestedList.docx");
        document.write(out);

        System.out.println("CreateWordTableWithBulletList written successully");

    }

    private static BigInteger createNumbering(XWPFDocument document,
                                              Integer levelCount) {
        XWPFNumbering numbering = document.createNumbering();


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

    private static void addCTLevels(CTAbstractNum ctAbstractNum, Integer levelCount) {
        for (int level = 0; level < levelCount; level++) {
            CTLvl ctLvl = ctAbstractNum.addNewLvl();
            ctLvl.setIlvl(BigInteger.valueOf(level));
            ctLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
            ctLvl.addNewLvlText().setVal(getLvlTxtVal(level));
            ctLvl.addNewStart().setVal(BigInteger.ONE);
        }

    }

    private static String getLvlTxtVal(Integer level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= level; i++) {
            sb.append("%").append(i + 1).append(".");

        }
        return sb.toString();
    }

    private static void addParagraph(BigInteger numId, XWPFDocument document, Integer level,
                                     String rowName) {
        XWPFParagraph p = document.createParagraph();
        p.setNumID(numId);
        //sets level to paragraph
        CTDecimalNumber ctDecimalNumber = p.getCTP().getPPr().getNumPr().addNewIlvl();
        ctDecimalNumber.setVal(BigInteger.valueOf(level));//sets level value
        p.setIndentationLeft(level * 360);
        p.createRun().setText(rowName);
    }


}
