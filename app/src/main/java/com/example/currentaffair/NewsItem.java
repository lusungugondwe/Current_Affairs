package com.example.currentaffair;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class NewsItem implements Parcelable {
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

    protected NewsItem(Parcel in) {
        blob = in.createByteArray();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        body = in.readString();
        comment = in.readString();
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByteArray(blob);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(title);
        dest.writeString(body);
        dest.writeString(comment);
    }
}
