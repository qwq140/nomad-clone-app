package com.cos.nomadapp.ui.community;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.cos.nomadapp.model.community.CReplySaveReqDto;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.service.NomadApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityDetailActivity extends AppCompatActivity {
    private static final String TAG = "CommunityDetailActivity";
    private ImageView ivBack,ivSendReply;
    private TextView tvToolbarTitle;
    private RecyclerView rvCommunityDetail;
    private EditText etReply;
    private List<CReply> replies;
    private RelativeLayout replyBar;
    private EditText et_reply;
    private CommunityDetailAdapter communityDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        Community community = (Community) intent.getSerializableExtra("community");
        //댓글 쓰기
        replyBar = (RelativeLayout) findViewById(R.id.reply_bar);
        replyBar.setVisibility(View.INVISIBLE);
        ivSendReply=findViewById(R.id.iv_reply_send);
        et_reply = findViewById(R.id.et_reply);
        //댓글 쓰기

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
        Call<CMRespDto<Community>> call = nomadApi.comFindById(community.getId());
        call.enqueue(new Callback<CMRespDto<Community>>() {
            @Override
            public void onResponse(Call<CMRespDto<Community>> call, Response<CMRespDto<Community>> response) {
                replies = response.body().getData().getReplys();
                for(int i=0;i<replies.size();i++){
                    items.add(new Item(1,replies.get(i)));
                    Log.d(TAG, "onResponse: test321: "+replies.get(i));
                }
            }

            @Override
            public void onFailure(Call<CMRespDto<Community>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패");
            }

        });
        //댓글쓰기
        ivSendReply.setOnClickListener(v->{

            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            String token = pref.getString("token","");
            //long principalId = pref.getLong("principalId",0);
            Log.d(TAG, "token :"+token);

            CReplySaveReqDto cReplySaveReqDto=new CReplySaveReqDto();
            cReplySaveReqDto.setContent(et_reply.getText().toString());
            cReplySaveReqDto.setComId(community.getId());
            //cReplySaveReqDto.setUserId(principalId);

            Log.d(TAG, "onCreate: "+cReplySaveReqDto);
            NomadApi nomadApi2 = NomadApi.retrofit.create(NomadApi.class);
            Call<CMRespDto<CReply>> call2 = nomadApi2.cReplySave("Bearer "+token,cReplySaveReqDto);
            call2.enqueue(new Callback<CMRespDto<CReply>>() {
                @Override
                public void onResponse(Call<CMRespDto<CReply>> call, Response<CMRespDto<CReply>> response) {
                    items.add(new Item(1,response.body().getData()));
                    Log.d(TAG, "onResponse: test123 : "+response.body().getData());
                    shutdownReplyInput();
                }

                @Override
                public void onFailure(Call<CMRespDto<CReply>> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });
        });
        rvCommunityDetail.setLayoutManager(manager);
        communityDetailAdapter=new CommunityDetailAdapter(items,this);
        rvCommunityDetail.setAdapter(communityDetailAdapter);
        communityDetailAdapter.notifyDataSetChanged();
    }
    public void showReplyInput(){           //댓글 키보드 쓰기
        RelativeLayout replyBar = (RelativeLayout) findViewById(R.id.reply_bar);
        replyBar.setVisibility(View.VISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        etReply = (EditText) findViewById(R.id.et_reply);
        etReply.requestFocus();
        imm.showSoftInput(etReply, InputMethodManager.SHOW_IMPLICIT);
    }
    public void shutdownReplyInput(){           //댓글 키보드 닫기
        RelativeLayout replyBar = (RelativeLayout) findViewById(R.id.reply_bar);
        replyBar.setVisibility(View.INVISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        etReply = (EditText) findViewById(R.id.et_reply);
        etReply.requestFocus();
        View view = this.getCurrentFocus();
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}