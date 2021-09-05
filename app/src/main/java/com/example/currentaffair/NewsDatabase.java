package com.example.currentaffair;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NewsDatabase extends SQLiteOpenHelper {
    private static final String db_name = "current_affairs_db";
    private static final String tb_name = "current_affairs_tb";
    private static final String tb_user = "user_tb";
    private static final String tb_comments = "comments_tb";

    public NewsDatabase(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tb_name+"(id integer primary key autoincrement, title text, description text, news_date text, image BLOB)");
        db.execSQL("CREATE TABLE "+tb_user+"(id integer primary key autoincrement, email text, password text, state text)");
        db.execSQL("CREATE TABLE "+tb_comments+"(id integer primary key autoincrement, comment text, news_id integer, user integer, comment_date text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tb_name);
        db.execSQL("DROP TABLE IF EXISTS "+tb_user);
        db.execSQL("DROP TABLE IF EXISTS "+tb_comments);
        onCreate(db);
    }

    public void insertNews(String date, String title, String description, byte[] image){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("image", image);
        values.put("news_date", date);
        database.insert(tb_name, null, values);
    }

    public boolean insertUser(String email, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("state", "logged out");
        database.insert(tb_user, null, values);
        return true;
    }

    public Cursor getUser(){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor users = database.rawQuery("SELECT email, password, state FROM "+tb_user, null);
        return users;
    }

    public Cursor getUsers(String email, String password){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor users = database.rawQuery("SELECT email, password, state FROM "+tb_user+" WHERE email=? AND password=?", new String[]{email, password});
        return users;
    }

    public boolean updateUserState(String state, String user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("state", state);

        int result = database.update(tb_user, values, "email=?",new String[]{user});
        if (result >0){
            return true;
        }else {
            return false;
        }
    }
    public Cursor loginUser(String email, String password){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor user = database.rawQuery("SELECT email, password FROM "+tb_user+" WHERE email=? AND password=?",new String[]{email, password});
        return user;
    }

    public Cursor userState(){
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor state = database.rawQuery("SELECT state FROM "+tb_user, null);
        return state;
    }

    public Cursor getNews(){
        SQLiteDatabase data = this.getReadableDatabase();
        Cursor cursor = data.rawQuery("SELECT * FROM "+tb_name+" ORDER BY id DESC", null);
        return cursor;
    }

    public boolean insertComment(String comment, Integer newsId, Integer userId, String date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("comment", comment);
        values.put("user", userId);
        values.put("news_id", newsId);
        values.put("comment_date", date);
        database.insert(tb_comments, null, values);
        return true;
    }

    public Cursor getAllComments(Integer id){
        SQLiteDatabase data = this.getReadableDatabase();
        Cursor cursor = data.rawQuery("SELECT * FROM "+tb_comments+" WHERE news_id=?", new String[]{  String.valueOf(id)});
        return cursor;
    }

    public Cursor getAllComments(){
        SQLiteDatabase data = this.getReadableDatabase();
        Cursor cursor = data.rawQuery("SELECT * FROM "+tb_comments, null);
        return cursor;
    }

    public boolean deleteNewsComments(Integer id){
        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.delete(tb_comments, "news_id=?", new String[]{String.valueOf(id)});

        if (result > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteNews(Integer id){
        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.delete(tb_name, "id=?", new String[]{String.valueOf(id)});

        if (result > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updateNews(String comment, Integer newsId, Integer userId, byte[] image, String date){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("comment", comment);
        values.put("user", userId);
        values.put("news_id", newsId);
        values.put("image", image);
        values.put("comment_date", date);

        long result = database.update(tb_name,values,"id=?", new String[]{String.valueOf(newsId)});
        if (result > 0){
            return true;
        }else {
            return false;
        }
    }
}
