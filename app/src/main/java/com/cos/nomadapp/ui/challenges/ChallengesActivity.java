package com.cos.nomadapp.ui.challenges;

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
import com.cos.nomadapp.adapter.ChallengesAdapter;
import com.cos.nomadapp.model.CommonTitle;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.challenge.Challenge;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ChallengesActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private RecyclerView rvChallengeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Challenges");

        ivBack.setOnClickListener(v -> {
            finish();
        });

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