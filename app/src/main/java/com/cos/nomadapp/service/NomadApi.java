package com.cos.nomadapp.service;

import com.cos.nomadapp.model.CMRespDto;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface NomadApi {


    @GET("faq")
    Call<CMRespDto> faqFindAll();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://http://172.30.1.50:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
