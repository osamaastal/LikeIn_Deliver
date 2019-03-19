package com.example.engosama.likein_deliver.Classes;

public class ContactUsClass {
    String name,data;

    public ContactUsClass(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public ContactUsClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
