package com.cos.nomadapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.cos.nomadapp.adapter.FaqAdapter;
import com.cos.nomadapp.model.Item;
import com.google.android.material.navigation.NavigationView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.fontawesome.FontTextView;

public class FaqActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private RecyclerView rvFaqList;
    private RoundedImageView rivUser;
    //footer
    private TextView tvRoadMap,tvCourses,tvCommunity,tvFaq,tvChallenges,tvServiceTerm,tvPrivacyPolicy,tvCancleOrRefundPolicy;
    private FontTextView ftvYoutube, ftvGithub, ftvFacebook, ftvInsta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("FAQ");

        tvCourses=findViewById(R.id.tv_courses);
        tvChallenges=findViewById(R.id.tv_challenges);
        tvFaq=findViewById(R.id.tv_faq);
        tvRoadMap=findViewById(R.id.tv_roadmap);
        tvCommunity=findViewById(R.id.tv_community);
        tvServiceTerm=findViewById(R.id.tv_service_term);
        tvPrivacyPolicy=findViewById(R.id.tv_privacy_policy);
        tvCancleOrRefundPolicy=findViewById(R.id.tv_cancle_or_refund_policy);
        ftvInsta=findViewById(R.id.ftv_insta);
        ftvYoutube=findViewById(R.id.ftv_youtube);
        ftvFacebook=findViewById(R.id.ftv_facebook);
        ftvGithub=findViewById(R.id.ftv_github);

        tvCommunity.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), CommunityActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        tvCourses.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), CoursesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        tvChallenges.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ChallengesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        tvRoadMap.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        tvFaq.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), FaqActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        tvServiceTerm.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ServiceTermActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        tvPrivacyPolicy.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), PrivacyPolicyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        tvCancleOrRefundPolicy.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), CancleOrRefundPolicyActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        ftvInsta.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/nomad_coders/?hl=ko"));
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        ftvYoutube.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCUpJs89fSBXNolQGOYKn0YQ"));
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        ftvFacebook.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ko-kr.facebook.com/nomadcoders/"));
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });
        ftvGithub.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/nomadcoders"));
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });

        ivBack.setOnClickListener(v -> {
            finish();
        });

        // 리사이클러뷰
        rvFaqList = findViewById(R.id.rv_faq_list);
        rvFaqList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<FaqGubun> faqGubuns = new ArrayList<>();

        List<FaqItem> academyItems = new ArrayList<>();
        academyItems.add(new FaqItem("로그인"));
        academyItems.add(new FaqItem("결제"));
        academyItems.add(new FaqItem("취소 및 환불정책"));
        academyItems.add(new FaqItem("수업 관련 문의"));
        academyItems.add(new FaqItem("증빙서류 발급"));
        academyItems.add(new FaqItem("슬랙 이용방법"));
        academyItems.add(new FaqItem("문의하기"));

        FaqGubun academy = new FaqGubun("노마드 아카데미",academyItems);
        faqGubuns.add(academy);

        List<FaqItem> challengeItems = new ArrayList<>();
        challengeItems.add(new FaqItem("로그인"));
        challengeItems.add(new FaqItem("결제"));
        challengeItems.add(new FaqItem("취소 및 환불정책"));
        challengeItems.add(new FaqItem("수업 관련 문의"));
        challengeItems.add(new FaqItem("증빙서류 발급"));

        FaqGubun challenge = new FaqGubun("노마드 챌린지",challengeItems);
        faqGubuns.add(challenge);

        List<FaqItem> reviewItems = new ArrayList<>();
        reviewItems.add(new FaqItem("코코아 클론 2주 완성반"));
        reviewItems.add(new FaqItem("바닐라JS 2주 완성반"));
        reviewItems.add(new FaqItem("파이썬 2주 완성반"));
        reviewItems.add(new FaqItem("에어비앤비 4주 완성반"));
        reviewItems.add(new FaqItem("유튜브 클론 6주 완성반"));
        reviewItems.add(new FaqItem("CSS Layout 2주 완성반"));
        reviewItems.add(new FaqItem("리액트JS 2주 완성반"));
        reviewItems.add(new FaqItem("우버이츠 클론 6주 완성반"));

        FaqGubun review = new FaqGubun("졸업작품 및 후기",reviewItems);
        faqGubuns.add(review);

        List<FaqItem> communityItems = new ArrayList<>();
        communityItems.add(new FaqItem("로그인"));
        communityItems.add(new FaqItem("결제"));
        communityItems.add(new FaqItem("취소 및 환불정책"));
        communityItems.add(new FaqItem("수업 관련 문의"));
        communityItems.add(new FaqItem("증빙서류 발급"));
        communityItems.add(new FaqItem("슬랙 이용방법"));
        communityItems.add(new FaqItem("문의하기"));

        FaqGubun community = new FaqGubun("노마드 커뮤니티",communityItems);
        faqGubuns.add(community);




        rvFaqList.setAdapter(new FaqAdapter(faqGubuns));

        //roundedImageView 이벤트
        rivUser = (RoundedImageView) findViewById(R.id.riv_user);
        rivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(
                        getApplicationContext(),//화면제어권자
                        v);             //팝업을 띄울 기준이될 위젯
                getMenuInflater().inflate(R.menu.user_menu, p.getMenu());
                //이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("Dashboard")) {
                            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(v.getContext(), UserDashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            v.getContext().startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                p.show();
            }
        });
        //roundedImageView End

    }


}