package com.example.currentaffair.viewModel;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currentaffair.NewsDatabase;
import com.example.currentaffair.R;
import com.example.currentaffair.ViewForCommenting;
import com.example.currentaffair.adapters.CommentsAdapter;
import com.example.currentaffair.models.NewsCommentsModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommentsFragments extends Fragment {

    RecyclerView recyclerView;
    CommentsAdapter commentsAdapter;

    NewsDatabase database;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.comments_display, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.display_comments_id);
        database = new NewsDatabase(getActivity());

        ArrayList<NewsCommentsModel> commentsModelArrayList = new ArrayList<>();

        Cursor allComments = database.getAllComments();
        if (allComments.getCount() == 0){
            Toast.makeText(getActivity(), "No comments available...", Toast.LENGTH_SHORT).show();
        }else {
            while (allComments.moveToNext()){
                commentsModelArrayList.add(new NewsCommentsModel(0, allComments.getInt(0), allComments.getString(1).toString(), allComments.getString(4).toString()));
            }
        }

        commentsAdapter = new CommentsAdapter(getActivity(), commentsModelArrayList);
        recyclerView.setAdapter(commentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
