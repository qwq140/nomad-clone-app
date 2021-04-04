package com.cos.nomadapp.ui.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.cos.nomadapp.CoursesSort;
import com.cos.nomadapp.R;
import com.cos.nomadapp.UserDashboardActivity;
import com.cos.nomadapp.adapter.CoursesAdapter;
import com.cos.nomadapp.adapter.TechAdapter;
import com.cos.nomadapp.model.CMRespDto;

import com.cos.nomadapp.model.courses.CoursesPreview;
import com.cos.nomadapp.model.tech.Tech;
import com.cos.nomadapp.service.NomadApi;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CoursesActivity extends AppCompatActivity {

    private static final String TAG = "CoursesActivity";
    private Context context = CoursesActivity.this;

    private TextView tvToolbarTitle, tvCoursesTitle, tvCoursesSubTitle;
    private SharedPreferences pref;

    private ImageView ivBack;
    private RoundedImageView rivUser;
    private RecyclerView rvCoursesList, rvTech;

    private CoursesSort coursesSort = new CoursesSort();
    private Chip chipBeginner, chipIntermediate, chipAdvanced, chipFree, chipPaid;
    private ChipGroup chipGroup1, chipGroup2;

    private ImageButton btnTechCancel, btnLevelCancel, btnPriceCancel;

    private NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);

    private String token;

    public CoursesSort getCoursesSort(){
        return coursesSort;
    }

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        init();

        downloadCourses();

        downloadTech();

        coursesSort();

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

    // download courses list
    public void downloadCourses(){
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCoursesList.setLayoutManager(manager);
        Call<CMRespDto<List<CoursesPreview>>> call = nomadApi.getAllCourses(coursesSort.getLevel(),coursesSort.getIsFree(),coursesSort.getTechId());
        call.enqueue(new Callback<CMRespDto<List<CoursesPreview>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<CoursesPreview>>> call, Response<CMRespDto<List<CoursesPreview>>> response) {
                Log.d(TAG, "onResponse: "+response.body());
                List<CoursesPreview> coursesPreviews = response.body().getData();
                rvCoursesList.setAdapter(new CoursesAdapter(coursesPreviews,context, token));
            }

            @Override
            public void onFailure(Call<CMRespDto<List<CoursesPreview>>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }


    // download tech list
    private void downloadTech(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,4);
        rvTech.setLayoutManager(gridLayoutManager);

        NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
        Call<CMRespDto<List<Tech>>> call = nomadApi.getTechList();
        call.enqueue(new Callback<CMRespDto<List<Tech>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Tech>>> call, Response<CMRespDto<List<Tech>>> response) {
                Log.d(TAG, "onResponse: 성공 " + response.body());
                List<Tech> teches = response.body().getData();
                Log.d(TAG, "onResponse: techs : "+teches);
                rvTech.setAdapter(new TechAdapter(teches,context,btnTechCancel));
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Tech>>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패");
            }
        });
    }

    private void init(){
        pref = getSharedPreferences("pref",MODE_PRIVATE);
        token = pref.getString("token","");

        // appbar
        ivBack = findViewById(R.id.iv_back);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);

        // level chips
        chipBeginner = findViewById(R.id.chip_beginner);
        chipIntermediate = findViewById(R.id.chip_intermediate);
        chipAdvanced = findViewById(R.id.chip_advanced);

        // pay chips
        chipFree = findViewById(R.id.chip_free);
        chipPaid = findViewById(R.id.chip_paid);

        // chipGroup
        chipGroup1 = findViewById(R.id.chipGroup);
        chipGroup2 = findViewById(R.id.chipGroup2);

        // select cancel buttons
        btnTechCancel = findViewById(R.id.btn_tech_cancel);
        btnLevelCancel = findViewById(R.id.btn_level_cancel);
        btnPriceCancel = findViewById(R.id.btn_price_cancel);

        // subtitle, title
        tvCoursesTitle = findViewById(R.id.tv_title);
        tvCoursesSubTitle = findViewById(R.id.tv_subtitle);

        // title, subtitle set text
        tvCoursesTitle.setText("All Courses");
        tvCoursesSubTitle.setText("초급부터 고급까지! 니꼬쌤과 함께 풀스택으로 성장하세요!");

        // appbar's title set text
        tvToolbarTitle.setText("Courses");

        // recyclerview
        rvCoursesList = findViewById(R.id.rv_courses_list);
        rvTech = findViewById(R.id.rv_tech);

        // back
        ivBack.setOnClickListener(v -> {
            finish();
        });

        //초기세팅
        coursesSort.setIsFree("");
        coursesSort.setLevel("");
        coursesSort.setTechId(0);
    }

    // courses filter button, chip
    private void coursesSort(){
        chipBeginner.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.d(TAG, "onResponse: isChecked"+isChecked);
            if (isChecked == true){
                coursesSort.setLevel("초급");
                btnLevelCancel.setVisibility(View.VISIBLE);
                downloadCourses();
            }
        });

        chipIntermediate.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.d(TAG, "onResponse: isChecked"+isChecked);
            if (isChecked == true){
                coursesSort.setLevel("중급");
                btnLevelCancel.setVisibility(View.VISIBLE);
                downloadCourses();
            }
        });

        chipAdvanced.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked == true){
                coursesSort.setLevel("고급");
                btnLevelCancel.setVisibility(View.VISIBLE);
                downloadCourses();
            }
        });

        btnLevelCancel.setOnClickListener(v -> {
            chipGroup1.clearCheck();
            coursesSort.setLevel("");
            btnLevelCancel.setVisibility(View.INVISIBLE);
            downloadCourses();
        });

        chipFree.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked == true){
                coursesSort.setIsFree("true");
                btnPriceCancel.setVisibility(View.VISIBLE);
                downloadCourses();
            }
        });

        chipPaid.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked == true){
                coursesSort.setIsFree("false");
                btnPriceCancel.setVisibility(View.VISIBLE);
                downloadCourses();
            }
        });

        btnPriceCancel.setOnClickListener(v -> {
            chipGroup2.clearCheck();
            coursesSort.setIsFree("");
            btnPriceCancel.setVisibility(View.INVISIBLE);
            downloadCourses();
        });
    }
}