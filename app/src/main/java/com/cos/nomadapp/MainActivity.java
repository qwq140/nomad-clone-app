package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

import com.cos.nomadapp.adapter.MainAdapter;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.challenge.Challenge;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.common.MainTitle;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarNomad;
    private ImageView ivMenu;
    private DrawerLayout drawer;
    private NavigationView nv;
    private RecyclerView rvMainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarNomad = findViewById(R.id.toolbar_nomad);
        setSupportActionBar(toolbarNomad);

        ivMenu = findViewById(R.id.iv_back);
        drawer = findViewById(R.id.drawer);

        ivMenu.setOnClickListener(v -> {
            drawer.openDrawer(Gravity.LEFT);
        });

        nv = findViewById(R.id.nv);
        NavigationViewHelper.enable(MainActivity.this,nv);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvMainList = findViewById(R.id.rv_main_list);

        List<Item> items = new ArrayList<>();

        // type 0 : MainTitleItem 1 : CourseItem 2: main_link_item
        List<MainTitle> mainTitles = new ArrayList<>();
        mainTitles.add(new MainTitle("Clone Startups.\nLearn to code.","코딩은 진짜를 만들어보는거야!\n실제 구현되어 있는 서비스를 한땀 한땀\n따라 만들면서 코딩을 배우세요."));

        items.add(new Item(0,mainTitles.get(0)));

        for (int i = 0 ; i<5;i++){
            Course course = new Course("[풀스택] 유튜브 클론코딩","유튜브 백엔드 + 프런트엔드 + 배포",R.drawable.course_youtube);
            items.add(new Item(1,course));
        }

        items.add(new Item(2,"See all courses →"));



        rvMainList.setLayoutManager(manager);

        rvMainList.setAdapter(new MainAdapter(items));

    }
}