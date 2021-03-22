package com.cos.nomadapp.ui.faq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.adapter.FaqAdapter;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.faq.FaqGubun;
import com.cos.nomadapp.model.faq.FaqItem;
import com.cos.nomadapp.service.NomadApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class FaqActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private RecyclerView rvFaqList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("FAQ");

        ivBack.setOnClickListener(v -> {
            finish();
        });

        // 리사이클러뷰
        rvFaqList = findViewById(R.id.rv_faq_list);
        rvFaqList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // faq findAll get요청
        NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
        Call<CMRespDto> call = nomadApi.faqFindAll();


        List<FaqGubun> faqGubuns = new ArrayList<>();

        List<FaqItem> academyItems = new ArrayList<>();
        academyItems.add(new FaqItem(0L,"로그인"));
        academyItems.add(new FaqItem(1L,"결제"));
        academyItems.add(new FaqItem(2L,"취소 및 환불정책"));
        academyItems.add(new FaqItem(3L,"수업 관련 문의"));
        academyItems.add(new FaqItem(4L,"증빙서류 발급"));
        academyItems.add(new FaqItem(5L,"슬랙 이용방법"));
        academyItems.add(new FaqItem(6L,"문의하기"));

        FaqGubun academy = new FaqGubun("노마드 아카데미",academyItems);
        faqGubuns.add(academy);

        List<FaqItem> challengeItems = new ArrayList<>();
        challengeItems.add(new FaqItem(7L,"로그인"));
        challengeItems.add(new FaqItem(8L,"결제"));
        challengeItems.add(new FaqItem(9L,"취소 및 환불정책"));
        challengeItems.add(new FaqItem(10L,"수업 관련 문의"));
        challengeItems.add(new FaqItem(11L,"증빙서류 발급"));

        FaqGubun challenge = new FaqGubun("노마드 챌린지",challengeItems);
        faqGubuns.add(challenge);

        List<FaqItem> reviewItems = new ArrayList<>();
        reviewItems.add(new FaqItem(12L,"코코아 클론 2주 완성반"));
        reviewItems.add(new FaqItem(13L,"바닐라JS 2주 완성반"));
        reviewItems.add(new FaqItem(14L,"파이썬 2주 완성반"));
        reviewItems.add(new FaqItem(15L,"에어비앤비 4주 완성반"));
        reviewItems.add(new FaqItem(16L,"유튜브 클론 6주 완성반"));
        reviewItems.add(new FaqItem(17L,"CSS Layout 2주 완성반"));
        reviewItems.add(new FaqItem(18L,"리액트JS 2주 완성반"));
        reviewItems.add(new FaqItem(19L,"우버이츠 클론 6주 완성반"));

        FaqGubun review = new FaqGubun("졸업작품 및 후기",reviewItems);
        faqGubuns.add(review);

        List<FaqItem> communityItems = new ArrayList<>();
        communityItems.add(new FaqItem(20L,"로그인"));
        communityItems.add(new FaqItem(21L,"결제"));
        communityItems.add(new FaqItem(22L,"취소 및 환불정책"));
        communityItems.add(new FaqItem(23L,"수업 관련 문의"));
        communityItems.add(new FaqItem(24L,"증빙서류 발급"));
        communityItems.add(new FaqItem(25L,"슬랙 이용방법"));
        communityItems.add(new FaqItem(26L,"문의하기"));

        FaqGubun community = new FaqGubun("노마드 커뮤니티",communityItems);
        faqGubuns.add(community);

        rvFaqList.setAdapter(new FaqAdapter(faqGubuns,this));


    }
}