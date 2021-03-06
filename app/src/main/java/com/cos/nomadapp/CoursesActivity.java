package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.CommonTitle;
import com.cos.nomadapp.model.Item;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class CoursesActivity extends AppCompatActivity {

    private Toolbar toolbarNomad;
    private ImageView ivMenu;
    private DrawerLayout drawer;
    private NavigationView nv;
    private RecyclerView rvCoursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        toolbarNomad = findViewById(R.id.toolbar_nomad);
        setSupportActionBar(toolbarNomad);

        ivMenu = findViewById(R.id.iv_menu);
        drawer = findViewById(R.id.drawer);

        ivMenu.setOnClickListener(v -> {
            drawer.openDrawer(Gravity.LEFT);
        });

        nv = findViewById(R.id.nv);
        NavigationViewHelper.enable(CoursesActivity.this,nv);



        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCoursesList = findViewById(R.id.rv_courses_list);

        List<Item> items = new ArrayList<>();

        CommonTitle commonTitle = new CommonTitle("All Courses","초급부터 고급까지! 니꼬쌤과 함께 풀스택으로 성장하세요!");
        items.add(new Item(0, commonTitle));

        items.add(new Item(2));

        for (int i = 0 ; i<5;i++){
            Course course = new Course("[풀스택] 유튜브 클론코딩","유튜브 백엔드 + 프런트엔드 + 배포",R.drawable.youtube);
            items.add(new Item(1,course));
        }

        rvCoursesList.setLayoutManager(manager);

        rvCoursesList.setAdapter(new CoursesAdapter(items));

    }
}