package com.cos.nomadapp.ui.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.cos.nomadapp.R;
import com.cos.nomadapp.UserDashboardActivity;
import com.cos.nomadapp.adapter.CoursesAdapter;
import com.cos.nomadapp.model.CMRespDto;

import com.cos.nomadapp.service.NomadApi;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CoursesActivity extends AppCompatActivity {

    private static final String TAG = "CoursesActivity";
    private TextView tvToolbarTitle;
    private ImageView ivBack;
    private RoundedImageView rivUser;
    private RecyclerView rvCoursesList;
    private CoursesAdapter coursesAdapter;

    private NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Courses");

        ivBack.setOnClickListener(v -> {
            finish();
        });



        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCoursesList = findViewById(R.id.rv_courses_list);

//        List<Item> items = new ArrayList<>();
//
//        CommonTitle commonTitle = new CommonTitle("All Courses","초급부터 고급까지! 니꼬쌤과 함께 풀스택으로 성장하세요!");
//        items.add(new Item(0, commonTitle));
//
//        items.add(new Item(1));
//
//        for (int i = 0 ; i<5;i++){
//            Course course = new Course("[풀스택] 유튜브 클론코딩","유튜브 백엔드 + 프런트엔드 + 배포",R.drawable.course_youtube);
//            items.add(new Item(2,course));
//        }
//        items.add(new Item(3));

        rvCoursesList.setLayoutManager(manager);



        Call<CMRespDto> call = nomadApi.getAllCourses();
        call.enqueue(new Callback<CMRespDto>() {
            @Override
            public void onResponse(Call<CMRespDto> call, Response<CMRespDto> response) {
                Log.d(TAG, "onResponse: "+response.body());
                List<Map<String,Object>> coursesPreviews =(List<Map<String,Object>>) response.body().getData();
                Log.d(TAG, "onResponse: "+coursesPreviews);
                Log.d(TAG, "onResponse: "+coursesPreviews.get(0).get("id"));
                rvCoursesList.setAdapter(new CoursesAdapter(getApplicationContext(),coursesPreviews));
            }

            @Override
            public void onFailure(Call<CMRespDto> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

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