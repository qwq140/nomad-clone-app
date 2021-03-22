package com.cos.nomadapp.service;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.faq.Faq;
import com.cos.nomadapp.model.faq.FaqCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NomadApi {


    @GET("faq/category")
    Call<CMRespDto<List<FaqCategory>>> faqCategoryFindAll();

    @GET("faq/{id}")
    Call<CMRespDto<Faq>> faqFindById(@Path("id") long id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.30.1.50:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
