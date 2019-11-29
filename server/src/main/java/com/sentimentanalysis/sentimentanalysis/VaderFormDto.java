package com.sentimentanalysis.sentimentanalysis;


public class VaderFormDto {
    String text;
    User user;

    public VaderFormDto(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VaderFormDto(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
