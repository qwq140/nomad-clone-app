package com.cos.nomadapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.user.User;
import com.cos.nomadapp.service.OAuthApi;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private SignInButton btnGoogleLogin;
    private final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final String TAG = "LoginActivity";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Login");

        ivBack.setOnClickListener(v -> {
            finish();
        });

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener((view)->{
            onClick(view);
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d(TAG, "handleSignInResult: "+account);
            OAuthApi oauthApi = OAuthApi.retrofit.create(OAuthApi.class);
            Call<CMRespDto> call = oauthApi.postOauth(account.getIdToken());
            call.enqueue(new Callback<CMRespDto>() {
                @Override
                public void onResponse(Call<CMRespDto> call, Response<CMRespDto> response) {
                    Log.d(TAG, "onResponse: 응답 : "+response.body());
                    if (response.body().getStatusCode()==200){
                        Log.d(TAG, "onResponse: 200 성공");
                        Map<String, Object> data = (Map<String, Object>) response.body().getData();
                        Log.d(TAG, "onResponse: data : " + data);
                        // 서버로 부터 받은 token payload에 userid 들어가있음, 요청을 할 때 jwt토큰도 함께 보내기?
                        User user = User.builder()
                                .email(data.get("email").toString())
                                .name(data.get("name").toString())
                                .provider(data.get("provider").toString())
                                .roles(data.get("roles").toString())
                                .build();

                        pref = getSharedPreferences("pref", MODE_PRIVATE);
                        editor = pref.edit();
                        editor.putString("token",data.get("token").toString());
                        editor.commit();

                        Log.d(TAG, "onResponse: User (구글로그인) : "+user);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("principal",user);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    } else {
                        Log.d(TAG, "onResponse: 로그인 실패");
                    }

                }

                @Override
                public void onFailure(Call<CMRespDto> call, Throwable t) {
                    Log.d(TAG, "onFailure: onFailure");
                }
            });
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());}
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }

    }

}