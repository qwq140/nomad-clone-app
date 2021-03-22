package com.cos.nomadapp.ui.faq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.adapter.FaqAdapter;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.faq.Faq;
import com.cos.nomadapp.model.faq.FaqCategory;
import com.cos.nomadapp.model.faq.FaqGubun;
import com.cos.nomadapp.model.faq.FaqItem;
import com.cos.nomadapp.service.NomadApi;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaqActivity extends AppCompatActivity {

    private static final String TAG = "FaqActivity";

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private RecyclerView rvFaqList;
    private List<FaqGubun> faqGubunList;
    private List<FaqCategory> faqCategoryList = new ArrayList<>();
    private List<Faq> faqList = new ArrayList<>();
    private Context mContext = this;

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

        faqGubunList = new ArrayList<>();

        //전체 카테고리 불러오기
        NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
        Call<CMRespDto<List<FaqCategory>>> call = nomadApi.faqCategoryFindAll();
        call.enqueue(new Callback<CMRespDto<List<FaqCategory>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<FaqCategory>>> call, Response<CMRespDto<List<FaqCategory>>> response) {
                Log.d(TAG, "onResponse: "+response.body());
                faqCategoryList = (List<FaqCategory>) response.body().getData();
                Log.d(TAG, "onResponse: "+faqCategoryList);
                for (int i=0; i<faqCategoryList.size();i++){
                    List<FaqItem> faqItems = new ArrayList<>();
                    for (int j = 0; j < faqCategoryList.get(i).getFaq().size(); j++){
                        faqItems.add(new FaqItem(faqCategoryList.get(i).getFaq().get(j).getId(),faqCategoryList.get(i).getFaq().get(j).getTitle()));
                    }
                    faqGubunList.add(new FaqGubun(faqCategoryList.get(i).getTitle(),faqItems));
                }
                Log.d(TAG, "onResponse: faqGubunList : "+faqGubunList );
                rvFaqList.setAdapter(new FaqAdapter(faqGubunList,FaqActivity.this));


            }

            @Override
            public void onFailure(Call<CMRespDto<List<FaqCategory>>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });



    }

}