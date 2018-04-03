package com.olgfok.word.creators;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TableCreator {

    @Autowired
    private XWPFDocument doc;


    public void addTable(List<String> headers, int rowCount, int colCount) {
        XWPFTable table = doc.createTable(rowCount, colCount);
        XWPFTableRow headerRow = table.getRow(0);
        for (int i=0;i<headers.size();i++) {
            if (i<colCount) {
                headerRow.getCell(i).setText(headers.get(i));
            } else {
                //insert extra row in third column
              //  CTTbl ctTbl = headerRow.getCell(i-1).getCTTc().addNewTbl();
               // CTRow ctRow = ctTbl.insertNewTr(0);
               // CTTc ctTc = ctRow.insertNewTc(0);
               // ctTc.addNewTcPr();



                //  XWPFTableCell newCell = headerRow.getCell(i).i;
                //newCell.setText(headers.get(i));

            }

        }

        // table.
    }
}
