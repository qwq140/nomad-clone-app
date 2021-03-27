package com.cos.nomadapp.service;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.user.User;
import com.cos.nomadapp.model.user.UserLoginRespDto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OAuthApi {

    @POST("android/oauth/jwt/google")
    Call<CMRespDto<UserLoginRespDto>> postOauth(@Body String idToken);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.9:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
