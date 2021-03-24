package com.cos.nomadapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.user.User;
import com.cos.nomadapp.model.user.UserUpdateReqDto;
import com.cos.nomadapp.service.NomadApi;
import com.cos.nomadapp.service.OAuthApi;
import com.cos.nomadapp.utils.JwtUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = "EditProfileActivity";

    private RoundedImageView ivProfileImg;
    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private SharedPreferences pref;
    private TextInputEditText tfEditName;
    private Button btnEditProfileSave, btnAccountDelete;
    private AppCompatButton btnChoosePhoto;
    private CheckBox checkDelete;

    private NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);

    private User principal;
    private String token;
    private JSONObject payloadObj;

    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        loadPref();

        init();


        long id = payloadObj.getLong("id");

        btnEditProfileSave.setOnClickListener(v -> {

            UserUpdateReqDto userUpdateReqDto = new UserUpdateReqDto();
            userUpdateReqDto.setName(tfEditName.getText().toString());

            Call<CMRespDto> call = nomadApi.profileUpdate("Bearer " + token, id, userUpdateReqDto);
            call.enqueue(new Callback<CMRespDto>() {
                @Override
                public void onResponse(Call<CMRespDto> call, Response<CMRespDto> response) {
                    Log.d(TAG, "onResponse: 수정 성공 : " + response.body());
                }

                @Override
                public void onFailure(Call<CMRespDto> call, Throwable t) {
                    Log.d(TAG, "onFailure: 수정 실패");
                }
            });

        });

        checkDelete = findViewById(R.id.check_delete);
        btnAccountDelete = findViewById(R.id.btn_account_delete);
        checkDelete.setOnClickListener(v -> {
            if (checkDelete.isChecked()){
                btnAccountDelete.setEnabled(true);
                btnAccountDelete.setBackgroundColor(Color.parseColor("#f04f4f"));
            } else {
                btnAccountDelete.setEnabled(false);
                btnAccountDelete.setBackgroundColor(Color.parseColor("#f7b8b3"));
            }
        });


        btnAccountDelete.setOnClickListener(v -> {
            Call<CMRespDto> call = nomadApi.userDelete("Bearer " + token, id);
            call.enqueue(new Callback<CMRespDto>() {
                @Override
                public void onResponse(Call<CMRespDto> call, Response<CMRespDto> response) {
                    Log.d(TAG, "onResponse: 삭제 성공 : " + response.body());
                }

                @Override
                public void onFailure(Call<CMRespDto> call, Throwable t) {
                    Log.d(TAG, "onFailure: 삭제 실패");
                }
            });
        });


        btnChoosePhoto.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);
        });

    }

    // sharedPreference에 저장된 토큰, principal 불러오기
    private void loadPref() throws Exception {
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        token = pref.getString("token", "");
        String payload = JwtUtils.payloadDecoded(token);
        payloadObj = new JSONObject(payload);
    }

    private void init(){
        ivBack = findViewById(R.id.iv_back);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tfEditName = findViewById(R.id.tf_edit_name);
        btnEditProfileSave = findViewById(R.id.btn_edit_profile_save);
        ivProfileImg = findViewById(R.id.iv_profile_img);
        btnChoosePhoto = findViewById(R.id.btn_choose_photo);

        // 툴바 title text 설정
        tvToolbarTitle.setText("DashBoard");

        // 뒤로가기 버튼
        ivBack.setOnClickListener(v -> {
            finish();
        });

        Intent intent = getIntent();
        principal = (User)intent.getSerializableExtra("principal");

        tfEditName.setText(principal.getName());

        Glide
                .with(EditProfileActivity.this)
                .load(principal.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.test)
                .into(ivProfileImg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    //선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    Log.d(TAG, "onActivityResult: Bitmap 확인 : "+ img);
                    in.close();
                    // 이미지 표시
                    ivProfileImg.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}