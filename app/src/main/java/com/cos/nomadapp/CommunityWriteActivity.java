package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.chinalwb.are.AREditor;

import java.util.ArrayList;
import java.util.List;

public class CommunityWriteActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private Spinner spinner;
    private SpinnerAdapter spinnerAdapter;

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

        AREditor arEditor = this.findViewById(R.id.areditor);
        arEditor.setExpandMode(AREditor.ExpandMode.FULL);
        arEditor.setHideToolbar(false);
        arEditor.setToolbarAlignment(AREditor.ToolbarAlignment.BOTTOM);



    }
}