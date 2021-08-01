package com.example.currentaffairs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NewsDatabase database;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_news:
                Intent menuIntent = new Intent(MainActivity.this, Upload.class);
                startActivity(menuIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.create_news_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
                news.add(new NewsItem(data.getString(1).toString(), data.getString(2).toString(), data.getBlob(3), data.getInt(0)));
            }
        }

        NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, R.layout.news_model_layout, news);
        newsListView.setAdapter(newsAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = news.get(position).body;
                Toast.makeText(getApplicationContext(),name.toString(),Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("New Member");
                builder.setMessage(name.toString());
                builder.show();
            }
        });
    }
}