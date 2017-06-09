package com.qunar.character.enums;


public enum CharacterRegex {

    ENGLISH("[a-zA-Z]"),
    CHINESE("[\u4e00-\u9fa5]"),
    NUMBER("[0-9]"),
    EXTRA("\\pP|\\pS");

    private CharacterRegex(String regex) {
        this.regex = regex;
    }

    private String regex;

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}
