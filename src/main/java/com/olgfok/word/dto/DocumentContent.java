package com.olgfok.word.dto;

import java.util.List;

public class DocumentContent {


    private List<ListItem> list;
    private List<Footnote> footnotes;

    public List<ListItem> getList() {
        return list;
    }

    public void setList(List<ListItem> list) {
        this.list = list;
    }

    public List<Footnote> getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(List<Footnote> footnotes) {
        this.footnotes = footnotes;
    }
}
