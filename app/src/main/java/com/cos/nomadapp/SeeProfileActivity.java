package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cos.nomadapp.model.user.User;
import com.makeramen.roundedimageview.RoundedImageView;

public class SeeProfileActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle, tvSeeProfileName;
    private RoundedImageView rivSeeProfileUser;
    private ImageView ivShield;

    private User principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_profile);

        init();
    }

    private void init(){

        Intent intent = getIntent();
        principal = (User)intent.getSerializableExtra("principal");

        ivBack = findViewById(R.id.iv_back);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        ivShield = findViewById(R.id.iv_shield);
        tvSeeProfileName = findViewById(R.id.tv_see_profile_name);
        rivSeeProfileUser = findViewById(R.id.riv_see_profile_user);

        // 툴바 title text 설정
        tvToolbarTitle.setText("See Profile");

        // 뒤로가기 버튼
        ivBack.setOnClickListener(v -> {
            finish();
        });

        tvSeeProfileName.setText(principal.getName());


        Glide
                .with(SeeProfileActivity.this)
                .load(principal.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.test)
                .into(rivSeeProfileUser);
    }
}