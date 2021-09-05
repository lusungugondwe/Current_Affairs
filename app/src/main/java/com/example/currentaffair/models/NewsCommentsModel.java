package com.example.currentaffair.models;

public class NewsCommentsModel {
    Integer commentId;
    String comment;
    Integer user;
    String date;

    public NewsCommentsModel(Integer user, Integer commentId, String comment, String date) {
        this.commentId = commentId;
        this.comment = comment;
        this.user = user;
        this.date = date;
    }

    public NewsCommentsModel(){

    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
