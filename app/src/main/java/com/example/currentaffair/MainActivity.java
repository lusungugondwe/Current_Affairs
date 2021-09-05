package com.example.currentaffair;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.currentaffair.adapters.NewsAdapter;
import com.example.currentaffair.models.NewsCommentsModel;
import com.example.currentaffair.models.NewsItem;
import com.example.currentaffair.viewModel.LoggedOut;
import com.example.currentaffair.viewModel.Upload;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    NewsDatabase database;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new NewsDatabase(MainActivity.this);

        ListView newsListView = (ListView) findViewById(R.id.news_list_view);

        ArrayList<NewsItem> news = new ArrayList<NewsItem>();

        Cursor data = database.getNews();

        if(data.getCount() == 0){

        }else {
            while (data.moveToNext()){
                news.add(new NewsItem(data.getString(1).toString(), data.getString(2).toString(), data.getBlob(4), data.getInt(0), data.getString(3).toString(), ""));
            }
        }

        newsAdapter = new NewsAdapter(MainActivity.this, R.layout.news_layout_model, news);
        newsListView.setAdapter(newsAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent comments = new Intent(MainActivity.this, ViewForCommenting.class);

                NewsItem selectedItem = new NewsItem(news.get(position).getTitle().toString(), news.get(position).getBody().toString(), news.get(position).getBlob(), news.get(position).getId(), news.get(position).getNewsDate(), "");
                comments.putExtra("ViewNewsDetails", selectedItem);
                startActivity(comments);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_news_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                Intent logout = new Intent(MainActivity.this, LoggedOut.class);
                startActivity(logout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}