package com.cos.nomadapp.ui.community;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import in.nashapp.androidsummernote.Summernote;

public class CommunityWriteActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private Spinner spinner;
    private SpinnerAdapter spinnerAdapter;
    private Summernote summernote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_write);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("글쓰기");

        ivBack.setOnClickListener(v -> {
            finish();
        });

        // 카테고리 목록
        List<String> category = new ArrayList<>();
        category.add("#카테고리1");
        category.add("#카테고리2");
        category.add("#카테고리3");
        category.add("#카테고리4");

        spinner = findViewById(R.id.spinner);

        spinnerAdapter = new SpinnerAdapter(this,category);

        spinner.setAdapter(spinnerAdapter);
        summernote = (Summernote) findViewById(R.id.summernote);
        summernote.setRequestCodeforFilepicker(5);//Any Number which is not being used by other OnResultActivity



    }


}