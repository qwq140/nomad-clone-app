package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.model.faq.FaqGubun;
import com.cos.nomadapp.model.faq.FaqItem;

import java.util.ArrayList;
import java.util.List;

public class FaqDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle, tvFaqDetailContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_detail);

        Intent intent = getIntent();

        FaqItem faqItem = (FaqItem) intent.getParcelableExtra("faqItem");

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText(faqItem.getName());

        ivBack.setOnClickListener(v -> {
            finish();
        });

        tvFaqDetailContent = findViewById(R.id.tv_faq_detail_content);
        tvFaqDetailContent.setMovementMethod(new ScrollingMovementMethod());

        // intend로 받은 id 같은걸로 데이터 요청을 해서 받아서 뿌리기
        String content = "<h3>리뉴얼 이후 계정 이동</h3><hr><div>이전 웹사이트 회원분은 (2020년 6월 15일 리뉴얼 이전) 동일한 이메일 계정으로 로그인 바랍니다.</div>";
        tvFaqDetailContent.setText(Html.fromHtml(content));



    }
}