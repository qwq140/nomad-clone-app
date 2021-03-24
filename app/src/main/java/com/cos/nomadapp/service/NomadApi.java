package com.cos.nomadapp.service;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.faq.Faq;
import com.cos.nomadapp.model.faq.FaqCategory;
import com.cos.nomadapp.model.user.User;
import com.cos.nomadapp.model.user.UserUpdateReqDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NomadApi {


    @GET("faq/category")
    Call<CMRespDto<List<FaqCategory>>> faqCategoryFindAll();

    @GET("faq/{id}")
    Call<CMRespDto<Faq>> faqFindById(@Path("id") long id);

    @GET("user/{id}")
    Call<CMRespDto> getProfile(@Header("Authorization") String token, @Path("id") long id);

    @PUT("user/{id}")
    Call<CMRespDto> profileUpdate(@Header("Authorization") String token, @Path("id") long id, @Body UserUpdateReqDto userUpdateReqDto);

    @DELETE("user/{id}")
    Call<CMRespDto> userDelete(@Header("Authorization") String token, @Path("id") long id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.30.1.50:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
