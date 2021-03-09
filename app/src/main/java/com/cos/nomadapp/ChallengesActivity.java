package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

import com.cos.nomadapp.adapter.ChallengesAdapter;
import com.cos.nomadapp.adapter.CoursesAdapter;
import com.cos.nomadapp.model.CommonTitle;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.challenge.Challenge;
import com.cos.nomadapp.model.courses.Course;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ChallengesActivity extends AppCompatActivity {

    private Toolbar toolbarNomad;
    private ImageView ivMenu, ivLogo;
    private DrawerLayout drawer;
    private NavigationView nv;
    private RecyclerView rvChallengeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        toolbarNomad = findViewById(R.id.toolbar_nomad);
        setSupportActionBar(toolbarNomad);

        ivMenu = findViewById(R.id.iv_menu);
        drawer = findViewById(R.id.drawer);

        ivLogo = findViewById(R.id.iv_logo);
        ivLogo.setOnClickListener(v -> {
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            this.startActivity(intent);
        });

        ivMenu.setOnClickListener(v -> {
            drawer.openDrawer(Gravity.LEFT);
        });

        nv = findViewById(R.id.nv);
        NavigationViewHelper.enable(ChallengesActivity.this,nv);



        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvChallengeList = findViewById(R.id.rv_challenges_list);

        List<Item> items = new ArrayList<>();

        CommonTitle commonTitle = new CommonTitle("Challenges","강의만으로는 부족해! 멱살잡고 캐리하는 챌린지에 무료로 참여하세요!");
        items.add(new Item(0, commonTitle));

        items.add(new Item(2));

        for (Long i = 0L ; i<9L;i++){
            Challenge challenge = new Challenge(i,"바닐라JS 2주 완성반",2,935);
            items.add(new Item(1,challenge));
        }

        rvChallengeList.setLayoutManager(manager);

        rvChallengeList.setAdapter(new ChallengesAdapter(items));
    }
}