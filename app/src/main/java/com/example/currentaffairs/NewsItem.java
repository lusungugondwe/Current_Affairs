package com.example.currentaffairs;

import java.sql.Blob;

public class NewsItem {
    byte[] blob;
    Integer id;
    String title;
    String body;
    String comment;

    public NewsItem(String title, String body, byte[] blob, String comment, Integer id) {
        this.blob = blob;
        this.title = title;
        this.id = id;
        this.body = body;
        this.comment = comment;
    }
    public NewsItem(String title, String body, Integer id) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public NewsItem(String title, String body, byte[] blob, Integer id) {
        this.title = title;
        this.body = body;
        this.blob = blob;
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getBlob() {
        return blob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer source) {
        this.id = source;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
