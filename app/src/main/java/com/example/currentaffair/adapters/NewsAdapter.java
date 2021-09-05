package com.example.currentaffair.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.currentaffair.R;
import com.example.currentaffair.models.NewsItem;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    private Context newsContext;
    private int newsResource;

    public NewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<NewsItem> objects) {
        super(context, resource, objects);

        newsContext = context;
        newsResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String title = getItem(position).getTitle();
        String body = getItem(position).getBody();
        Integer id = getItem(position).getId();
        byte[] newsImage = getItem(position).getBlob();
        String date = getItem(position).getNewsDate();
        NewsItem newsItems = new NewsItem(title, body, newsImage, id, date, "");

        LayoutInflater inflater = LayoutInflater.from(newsContext);

        convertView = inflater.inflate(newsResource, parent, false);

        TextView heading = (TextView) convertView.findViewById(R.id.news_heading);

        TextView news_body = (TextView) convertView.findViewById(R.id.news_body);
        ImageView member_name_id = (ImageView) convertView.findViewById(R.id.memnber_name_id);

        heading.setText(title);
        news_body.setText(body);
//        newsDate.setText(date);
        member_name_id.setImageBitmap(BitmapFactory.decodeByteArray(newsItems.getBlob(), 0,newsItems.getBlob().length));

        return convertView;
    }

}

