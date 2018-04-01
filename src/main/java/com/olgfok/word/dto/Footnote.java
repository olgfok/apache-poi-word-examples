package com.olgfok.word.dto;

public class Footnote {
    private String paragraphText;

    private String footnoteText;

    public String getParagraphText() {
        return paragraphText;
    }

    public void setParagraphText(String paragraphText) {
        this.paragraphText = paragraphText;
    }

    public String getFootnoteText() {
        return footnoteText;
    }

    public void setFootnoteText(String footnoteText) {
        this.footnoteText = footnoteText;
    }

    public Footnote(String paragraphText, String footnoteText) {
        this.paragraphText = paragraphText;
        this.footnoteText = footnoteText;
    }


}
