package com.cos.nomadapp.service;

import com.cos.nomadapp.model.CMRespDto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OAuthApi {

    @POST("android/oauth/jwt/google")
    Call<CMRespDto> postOauth(@Body String idToken);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.17.0.239:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
