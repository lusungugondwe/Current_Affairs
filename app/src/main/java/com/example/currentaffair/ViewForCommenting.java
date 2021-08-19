package com.example.currentaffair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

public class ViewForCommenting extends AppCompatActivity {
    TextView newsTitle, newsDescription, dateCreates;
    ShapeableImageView imageNews;
    EditText news_comments_id;
    ImageView sendComment;
    NewsDatabase database;
    CardView top_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_for_commenting);

        top_l = (CardView) findViewById(R.id.top_l);
        newsTitle = (TextView) findViewById(R.id.news_title);
        newsDescription = (TextView) findViewById(R.id.full_description);
        news_comments_id = (EditText) findViewById(R.id.news_comments_id);
        imageNews = (ShapeableImageView) findViewById(R.id.image_news);
        Intent data = getIntent();
        NewsItem newsItem = data.getParcelableExtra("Comments");
        newsTitle.setText(newsItem.getTitle());
        newsDescription.setText(newsItem.getBody());
        imageNews.setImageBitmap(BitmapFactory.decodeByteArray(newsItem.getBlob(), 0,newsItem.getBlob().length));


        if (newsDescription.getText().length() > 60){
            top_l.setMinimumHeight(400);
        }
    }

}