package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarNomad;
    private ImageView ivMenu;
    private DrawerLayout drawer;
    private NavigationView nv;
    private RecyclerView rvMainList;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarNomad = findViewById(R.id.toolbar_nomad);
        setSupportActionBar(toolbarNomad);

        ivMenu = findViewById(R.id.iv_menu);
        drawer = findViewById(R.id.drawer);

        ivMenu.setOnClickListener(v -> {
            drawer.openDrawer(Gravity.LEFT);
        });

        nv = findViewById(R.id.nv);
        NavigationViewHelper.enable(MainActivity.this,nv);

        List<Course> courses = new ArrayList<>();
        for (int i=1; i<7; i++){
            courses.add(new Course());
        }

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvMainList = findViewById(R.id.rv_main_list);
        rvMainList.setLayoutManager(manager);

        mainAdapter = new MainAdapter(courses);

        rvMainList.setAdapter(mainAdapter);

    }
}