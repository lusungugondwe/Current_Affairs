package com.example.currentaffair;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currentaffair.adapters.CommentsAdapter;
import com.example.currentaffair.models.NewsCommentsModel;
import com.example.currentaffair.models.NewsItem;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewForCommenting extends AppCompatActivity {
    TextView newsTitle, newsDescription, dateCreates;
    ShapeableImageView imageNews;
    EditText news_comments_id;
    ImageView sendComment;
    NewsDatabase database;
    CardView top_l;
    ScrollView change_height;
    CommentsAdapter commentsAdapter;
    RatingBar userRatingBar;
    RecyclerView recyclerView;
    ArrayList<NewsCommentsModel> models;
    TextView newsDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_for_commenting);
        database = new NewsDatabase(ViewForCommenting.this);
        change_height = (ScrollView) findViewById(R.id.change_height);
        top_l = (CardView) findViewById(R.id.top_l);
        newsTitle = (TextView) findViewById(R.id.news_title);
        newsDate = (TextView) findViewById(R.id.news_date_id);
        newsDescription = (TextView) findViewById(R.id.full_description);
        news_comments_id = (EditText) findViewById(R.id.news_comments_id);
        imageNews = (ShapeableImageView) findViewById(R.id.image_news);
        sendComment = (ImageView) findViewById(R.id.send_comment);
        userRatingBar = (RatingBar) findViewById(R.id.userRatingBar);
        recyclerView = (RecyclerView) findViewById(R.id.show_comments);
        models = new ArrayList<>();

        userRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser){
                    Toast.makeText(getApplicationContext(), "Rated : "+ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent data = getIntent();
        NewsItem newsItem = data.getParcelableExtra("ViewNewsDetails");
        newsTitle.setText(newsItem.getTitle());
        newsDescription.setText(newsItem.getBody());
        newsDate.setText(newsItem.getNewsDate());
        imageNews.setImageBitmap(BitmapFactory.decodeByteArray(newsItem.getBlob(), 0,newsItem.getBlob().length));
        getAllComments(newsItem.getId());

        commentsAdapter = new CommentsAdapter(ViewForCommenting.this, models);
        recyclerView.setAdapter(commentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewForCommenting.this));

        //sending comment
        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = news_comments_id.getText().toString().trim();

                if (news_comments_id.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Comment cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    boolean sent = database.insertComment(comment, newsItem.getId(), 0, getDate());
                    if (sent){
                        getAllComments(newsItem.getId());
                        commentsAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Comment Added...", Toast.LENGTH_SHORT).show();
                        news_comments_id.setText("");
                    }else {
                        Toast.makeText(getApplicationContext(), "Failed to add comment...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void getAllComments(Integer newsId) {
        models.clear();
        Cursor allComments = database.getAllComments(newsId);
        if (allComments.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No comments available...", Toast.LENGTH_SHORT).show();
        }else {
            while (allComments.moveToNext()){
                models.add(new NewsCommentsModel(0, allComments.getInt(0), allComments.getString(1).toString(), allComments.getString(4).toString()));
            }
        }
    }

    public  String getDate(){
        Date objDate = new Date();
        String strDateFormat = "MMM-dd-yyyy hh:mm:ss a"; //Date format is Specified
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); //Date format string is passed as an argument to the Date format object
        String current_time = objSDF.format(objDate).toString();

        return current_time;
    }
}