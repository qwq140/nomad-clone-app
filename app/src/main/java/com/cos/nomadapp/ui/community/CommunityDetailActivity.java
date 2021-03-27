package com.cos.nomadapp.ui.community;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.adapter.CommunityDetailAdapter;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.common.Item;
import com.cos.nomadapp.model.community.CReply;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.service.NomadApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityDetailActivity extends AppCompatActivity {
    private static final String TAG = "CommunityDetailActivity";
    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private RecyclerView rvCommunityDetail;
    private EditText etReply;
    private List<CReply> replies;

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

        //게시글마다 댓글 불러오기
        replies = new ArrayList<>();


        NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
        Call<CMRespDto<List<CReply>>> call = nomadApi.cReplyFindAll();
        
        call.enqueue(new Callback<CMRespDto<List<CReply>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<CReply>>> call, Response<CMRespDto<List<CReply>>> response) {
                replies = (List<CReply>) response.body().getData();     //모든 댓글 데이터
                Log.d(TAG, "onResponse: "+replies.toString());
                for(int i=0;i<replies.size();i++){
                    if(community.getId().equals(replies.get(i).getCommunity().getId())){
                        items.add(new Item(1, replies.get(i)));
                    }else{
                        Log.d(TAG, "onResponse: "+replies.get(i));
                    }
                }
            }

            @Override
            public void onFailure(Call<CMRespDto<List<CReply>>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패");
            }

        });
        rvCommunityDetail.setLayoutManager(manager);
        rvCommunityDetail.setAdapter(new CommunityDetailAdapter(items,this));
        
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showReplyInput(){           //댓글 쓰기
        RelativeLayout replyBar = (RelativeLayout) findViewById(R.id.reply_bar);
        replyBar.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        etReply = (EditText) findViewById(R.id.et_reply);
        etReply.requestFocus();
        imm.showSoftInput(etReply, InputMethodManager.SHOW_IMPLICIT);
    }


}