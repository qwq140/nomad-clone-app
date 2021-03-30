package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.ui.courses.CourseDetailActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    private Course course;
    private SharedPreferences pref;
    private String token;

    //디자인
    private TextView tvToolbarTitle, tvCourseTitle, tvCourseSubTitle, tvCourseLevel, tvAmountPayment;
    private ImageView ivBack;
    private RoundedImageView ivCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        init();

        setItem();
    }

    private void init(){
        Intent intent = getIntent();
        course = (Course) intent.getSerializableExtra("course");

        // 토큰 가져오기
        pref=getSharedPreferences("pref", MODE_PRIVATE);
        token = pref.getString("token","");

        // 뒤로가기 버튼
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            finish();
        });

        // 앱바 디자인
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Payment");

        ivCourse = findViewById(R.id.iv_course);
        tvCourseTitle = findViewById(R.id.tv_course_title);
        tvCourseSubTitle = findViewById(R.id.tv_course_subtitle);
        tvCourseLevel = findViewById(R.id.tv_courses_level);
        tvAmountPayment = findViewById(R.id.tv_amount_payment);

    }

    private void setItem(){
        tvCourseLevel.setVisibility(View.INVISIBLE);
        tvCourseTitle.setText(course.getTitle());
        tvCourseSubTitle.setText(course.getSubTitle());

        Map<String,Object> previewImage = course.getPreviewImage();

        Glide
                .with(this)
                .load(previewImage.get("url")) // 임시 테스트로 넘어오는 이미지는 localhost라서 적용이 안됨
                .centerCrop()
                .placeholder(R.drawable.course_youtube)
                .into(ivCourse);

        tvAmountPayment.setText("￦ "+course.getPrice());
    }
}