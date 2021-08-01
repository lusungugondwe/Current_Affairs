package com.example.currentaffairs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NewsDatabase extends SQLiteOpenHelper {
    private static final String db_name = "current_affairs_db";
    private static final String tb_name = "current_affairs_tb";

    public NewsDatabase(@Nullable Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tb_name+"(id integer primary key autoincrement, title text, description text, image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tb_name);
        onCreate(db);
    }

    public void insertNews(String title, String description, byte[] image){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("image", image);
        database.insert(tb_name, null, values);
    }

    public Cursor getNews(){
        SQLiteDatabase data = this.getReadableDatabase();
        Cursor cursor = data.rawQuery("SELECT * FROM "+tb_name+" ORDER BY id DESC", null);
        return cursor;
    }
}
