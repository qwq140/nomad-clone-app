package com.cos.nomadapp.ui.community;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.adapter.CommunityAdapter;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.model.reply.Reply;
import com.cos.nomadapp.model.user.User;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CommunitySearchActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private TextInputEditText etSearch;
    private RecyclerView rvSearchList;
    private CommunityAdapter communityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_search);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("검색");

        ivBack.setOnClickListener(v -> {
            finish();
        });

        rvSearchList = findViewById(R.id.rv_search_list);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        List<Community> communities = new ArrayList<>();

        User user = User.builder()
                .name("유저1")
                .build();

        List<Reply> replies = new ArrayList<>();
        User replyUser = User.builder()
                .name("유저2")
                .build();
        Reply reply = new Reply(0L,"댓글내용1",replyUser,1,"1 hours ago");
        replies.add(reply);

        reply = new Reply(1L,"댓글내용2",replyUser,0,"1 hours ago");
        replies.add(reply);

        reply = new Reply(2L,"댓글내용3",replyUser,7,"1 hours ago");
        replies.add(reply);


        for (Long i = 0L ; i<5L;i++){
            Community community = new Community(i,"커뮤니티 제목"+i, user, replies , "내용",15,"bla-bla","15");
            communities.add(community);
        }


        rvSearchList.setLayoutManager(manager);

        communityAdapter = new CommunityAdapter(communities,this);

        rvSearchList.setAdapter(communityAdapter);

    }

}