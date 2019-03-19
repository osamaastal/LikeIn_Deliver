package com.example.engosama.likein_deliver.Classes.TermsConditions;

public class TermsAndCondClass {
    String title,content;

    public TermsAndCondClass(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public TermsAndCondClass() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
