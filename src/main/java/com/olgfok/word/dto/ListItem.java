package com.olgfok.word.dto;

public class ListItem {

    public ListItem(Integer level, String text) {
        this.level = level;
        this.text = text;
    }

    private Integer level;
    private String text;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
