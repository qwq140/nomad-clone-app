package com.cos.nomadapp.ui.community;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.cos.nomadapp.R;

import java.util.ArrayList;
import java.util.List;

import in.nashapp.androidsummernote.Summernote;

public class CommunityWriteActivity extends AppCompatActivity {

    private ImageView ivBack;
    private AutoCompleteTextView autoCompleteTextView;
    private Summernote summernote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_write);

        ivBack = findViewById(R.id.iv_back);

        ivBack.setOnClickListener(v -> {
            finish();
        });

        autoCompleteTextView = findViewById(R.id.autoCompleteText);

        List<String> category = new ArrayList<>();
        category.add("#to-do-list");
        category.add("#html_css");
        category.add("#javascript");
        category.add("#bla-bla");
        category.add("#python");
        category.add("#dev_resources");
        category.add("#jobs");
        category.add("#side_projects");
        category.add("#react");
        category.add("#uber_eats");
        category.add("#hello");
        category.add("#instaclone");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.community_category_item,category);

        // 디폴트 값
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(),false);

        autoCompleteTextView.setAdapter(arrayAdapter);

        summernote = (Summernote) findViewById(R.id.summernote);
        summernote.setRequestCodeforFilepicker(5);//Any Number which is not being used by other OnResultActivity



    }


}