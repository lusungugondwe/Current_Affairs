package com.example.currentaffair.viewModel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.currentaffair.MainActivity;
import com.example.currentaffair.NewsDatabase;
import com.example.currentaffair.R;
import com.example.currentaffair.adapters.NewsAdapter;
import com.example.currentaffair.models.NewsItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

   NewsDatabase database;
   NewsAdapter newsAdapter;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_display, container, false);
        database = new NewsDatabase(getActivity());

        ListView newsListView = (ListView) view.findViewById(R.id.lv_news_id);

        ArrayList<NewsItem> news = new ArrayList<NewsItem>();

        Cursor data = database.getNews();

        if(data.getCount() == 0){

        }else {
            while (data.moveToNext()){
                news.add(new NewsItem(data.getString(1).toString(), data.getString(2).toString(), data.getBlob(4), data.getInt(0), data.getString(3).toString(), ""));
            }
        }

        newsAdapter = new NewsAdapter(getActivity(), R.layout.news_layout_model, news);
        newsListView.setAdapter(newsAdapter);

        newsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setMessage("Delete the news")
                        .setCancelable(false)
                        .setIcon(R.drawable.ic_baseline_remove_circle_outline_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                Cursor cursor = database.getAllComments(news.get(position).getId());

                                boolean result = database.deleteNewsComments(news.get(position).getId());
                                boolean result2 = database.deleteNews(news.get(position).getId());
                                if (result){
                                    Toast.makeText(getActivity(), "comments for this news are eleted successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), "comment deletion failed", Toast.LENGTH_SHORT).show();
                                }

                                if (result2){
                                    Toast.makeText(getActivity(), "News deleted successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(), "news deletion failed", Toast.LENGTH_SHORT).show();
                                }

                                newsAdapter.notifyDataSetChanged();

                            }
                        }).setNegativeButton("No", null);
                builder.show();
                return true;
            }
        });

        return view;
    }
}
