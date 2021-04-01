package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cos.nomadapp.adapter.DashboardAdapter;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.common.Item;
import com.cos.nomadapp.model.user.User;
import com.cos.nomadapp.model.user.UserDashboardSecondSection;
import com.cos.nomadapp.service.NomadApi;
import com.cos.nomadapp.utils.JwtUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDashboardActivity extends AppCompatActivity {
    private static final String TAG = "UserDashboardActivity";
    private ImageView ivBack;
    private TextView tvToolbarTitle, tvDashName, tvDashUsername;
    private RoundedImageView rivUser, rivDashboardUser;
    private RecyclerView rvDashboard;
    private SharedPreferences pref;
    private Button btnEditProfile, btnSeeProfile;
    private NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
    private User user;

    private long id;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("DashBoard");

        ivBack.setOnClickListener(v -> {
            finish();
        });

        rivDashboardUser = findViewById(R.id.riv_dashboard_user);
        tvDashName = findViewById(R.id.tv_dash_name);
        tvDashUsername = findViewById(R.id.tv_dash_username);



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

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDashboard = findViewById(R.id.rv_dashboard);
        rvDashboard.setLayoutManager(manager);

        List<Item> items = new ArrayList<>();

        UserDashboardSecondSection userDashboardSecondSection = new UserDashboardSecondSection("Join more courses and complete challenges to unlock the next level!");
        items.add(new Item(1, userDashboardSecondSection));
        Log.d(TAG, "onCreate: " + userDashboardSecondSection);

        rvDashboard.setAdapter(new DashboardAdapter(items, UserDashboardActivity.this));

    }

    @SneakyThrows
    @Override
    protected void onResume() {
        super.onResume();
        // 토큰 불러오기, principal 불러오기
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        token = pref.getString("token", "");
        Log.d(TAG, "onCreate: token : " + token);

        if (token!=""){
            // 토큰 디코딩
            String payload = JwtUtils.payloadDecoded(token);
            JSONObject payloadObj = new JSONObject(payload);
            Log.d(TAG, "onCreate: Payload : " + payloadObj);

            id = payloadObj.getLong("id");

            // 개인 정보 얻기
            Call<CMRespDto> call = nomadApi.getProfile("Bearer " + token, id);
            call.enqueue(new Callback<CMRespDto>() {
                @Override
                public void onResponse(Call<CMRespDto> call, Response<CMRespDto> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    if (response.body()!=null){
                        Log.d(TAG, "onResponse: " + response.body().getData());
                        Map<String, Object> data = (Map<String, Object>) response.body().getData();
                        Log.d(TAG, "onResponse: data : " + data);

                        user = User.builder()
                                .username(data.get("username").toString())
                                .email(data.get("email").toString())
                                .name(data.get("name").toString())
                                .provider(data.get("provider").toString())
                                .roles(data.get("roles").toString())
                                .imageUrl(data.get("imageUrl").toString())
                                .build();

                        tvDashName.setText(user.getName());
                        tvDashUsername.setText(user.getUsername());

                        if (data.get("file")==null){
                            Glide
                                    .with(UserDashboardActivity.this)
                                    .load(user.getImageUrl())
                                    .centerCrop()
                                    .placeholder(R.drawable.ic_user)
                                    .into(rivDashboardUser);
                        } else {
                            Map<String, Object> file = (Map<String, Object>) data.get("file");
                            Glide
                                    .with(UserDashboardActivity.this)
                                    .load(file.get("fileUrl"))
                                    .centerCrop()
                                    .placeholder(R.drawable.ic_user)
                                    .into(rivDashboardUser);
                        }

                    } else {
                        Intent intent = new Intent(UserDashboardActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        UserDashboardActivity.this.startActivity(intent);
                    }

                }

                @Override
                public void onFailure(Call<CMRespDto> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });
        } else {
            Intent intent = new Intent(UserDashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            UserDashboardActivity.this.startActivity(intent);
        }


        btnEditProfile = findViewById(R.id.btn_edit_profile);
        btnSeeProfile = findViewById(R.id.btn_see_profile);

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), EditProfileActivity.class);
            intent.putExtra("principal",user);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });

        btnSeeProfile.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), SeeProfileActivity.class);
            intent.putExtra("principal",user);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            v.getContext().startActivity(intent);
        });

    }
}