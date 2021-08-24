package com.example.currentaffair;

public class NewsCommentsModel {
    Integer commentId;
    String comment;
    Integer user;

    public NewsCommentsModel(Integer user, Integer commentId, String comment) {
        this.commentId = commentId;
        this.comment = comment;
        this.user = user;
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
