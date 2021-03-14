package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.adapter.ChallengesAdapter;
import com.cos.nomadapp.adapter.CommunityDetailAdapter;
import com.cos.nomadapp.model.CommonTitle;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.challenge.Challenge;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.model.reply.Reply;
import com.cos.nomadapp.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class CommunityDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private RecyclerView rvCommunityDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        Intent intent = getIntent();

        Community community = (Community) intent.getSerializableExtra("community");

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("");

        ivBack.setOnClickListener(v -> {
            finish();
        });

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCommunityDetail = findViewById(R.id.rv_community_detail);

        List<Item> items = new ArrayList<>();

        items.add(new Item(0,community));

        for (int i = 0; i<community.getReply().size();i++){
            items.add(new Item(1,community.getReply().get(i)));
        }

        rvCommunityDetail.setLayoutManager(manager);

        rvCommunityDetail.setAdapter(new CommunityDetailAdapter(items,this));
    }
}