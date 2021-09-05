package com.example.currentaffair.viewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

import com.example.currentaffair.R;
import com.example.currentaffair.adapters.AdminControlAdapter;
import com.google.android.material.tabs.TabLayout;

public class AdminControl extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager=(ViewPager)findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Add News"));
        tabLayout.addTab(tabLayout.newTab().setText("Comments"));
        tabLayout.addTab(tabLayout.newTab().setText("Configurations"));


        AdminControlAdapter adminControlAdapter = new AdminControlAdapter(AdminControl.this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adminControlAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
