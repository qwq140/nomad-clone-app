package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class CommunityActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private ViewPager vpContainer;
    private TabLayout tabs;
    private CommunityPagerAdapter communityPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);


        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Community");

        ivBack.setOnClickListener(v -> {
            finish();
        });


        //ViewPager
        vpContainer = findViewById(R.id.vp_container);
        tabs = findViewById(R.id.tabs);

        communityPagerAdapter = new CommunityPagerAdapter(getSupportFragmentManager(),1);

        communityPagerAdapter.addFragment(new CommunityFragAll());
        communityPagerAdapter.addFragment(new CommunityFragTodolist());
        communityPagerAdapter.addFragment(new CommunityFragJavascript());
        communityPagerAdapter.addFragment(new CommunityFragBla());
        communityPagerAdapter.addFragment(new CommunityFragHtml());
        communityPagerAdapter.addFragment(new CommunityFragPython());

        vpContainer.setAdapter(communityPagerAdapter);

        tabs.setupWithViewPager(vpContainer);

        tabs.getTabAt(0).setText("all");
        tabs.getTabAt(1).setText("to-do-list");
        tabs.getTabAt(2).setText("javascript");
        tabs.getTabAt(3).setText("bla-bla");
        tabs.getTabAt(4).setText("html_css");
        tabs.getTabAt(5).setText("python");



    }
}