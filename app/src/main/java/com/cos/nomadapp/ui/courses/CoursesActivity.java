package com.cos.nomadapp.ui.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.adapter.CoursesAdapter;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.CommonTitle;
import com.cos.nomadapp.model.Item;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class CoursesActivity extends AppCompatActivity {

    private TextView tvToolbarTitle;
    private ImageView ivBack;

    private RecyclerView rvCoursesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Courses");

        ivBack.setOnClickListener(v -> {
            finish();
        });



        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCoursesList = findViewById(R.id.rv_courses_list);

        List<Item> items = new ArrayList<>();

        CommonTitle commonTitle = new CommonTitle("All Courses","초급부터 고급까지! 니꼬쌤과 함께 풀스택으로 성장하세요!");
        items.add(new Item(0, commonTitle));

        items.add(new Item(2));

        for (int i = 0 ; i<5;i++){
            Course course = new Course("[풀스택] 유튜브 클론코딩","유튜브 백엔드 + 프런트엔드 + 배포",R.drawable.course_youtube);
            items.add(new Item(1,course));
        }

        rvCoursesList.setLayoutManager(manager);

        rvCoursesList.setAdapter(new CoursesAdapter(items));

    }
}