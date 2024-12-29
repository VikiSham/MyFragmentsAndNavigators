package com.example.myfragmentandnavigators;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class PageViewerActivity extends AppCompatActivity {
    ViewPagerAdapter pageAdapter;
    ViewPager2 pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_viewer);

        pager = findViewById(R.id.viewPager2);
        // Create an adapter that knows which fragment should
        // be shown on each page
        pageAdapter = new ViewPagerAdapter(this);
        // Set the adapter onto the view pager
        pager.setAdapter(pageAdapter);
    }

}