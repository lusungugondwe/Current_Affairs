package com.example.currentaffair.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.currentaffair.viewModel.AddNewsFragment;
import com.example.currentaffair.viewModel.AdminFragment;
import com.example.currentaffair.viewModel.NewsFragment;
import com.example.currentaffair.viewModel.CommentsFragments;

public class AdminControlAdapter extends FragmentStatePagerAdapter {
    private Context myContext;
    int totalTabs;

    public AdminControlAdapter(Context myContext, FragmentManager fragmentManager, int totalTabs) {
        super(fragmentManager);
        this.myContext = myContext;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;
            case 1:
                AddNewsFragment addNewsFragment = new AddNewsFragment();
                return addNewsFragment;
            case 2:
                CommentsFragments commentsFragments = new CommentsFragments();
                return commentsFragments;
            case 3:
                AdminFragment adminFragment = new AdminFragment();
                return adminFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
