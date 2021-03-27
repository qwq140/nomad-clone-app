package com.cos.nomadapp.service;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.community.CReply;
import com.cos.nomadapp.model.community.Category;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.model.faq.Faq;
import com.cos.nomadapp.model.faq.FaqCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NomadApi {


    @GET("faq/category")
    Call<CMRespDto<List<FaqCategory>>> faqCategoryFindAll();

    @GET("faq/{id}")
    Call<CMRespDto<Faq>> faqFindById(@Path("id") long id);

    //--- community Start ---

    @GET("category")
    Call<CMRespDto<List<Category>>> comCategoryFindAll();

    // 카테고리
    @GET("community")
    Call<CMRespDto<List<Community>>> comFindAll();

    // 카테고리 한개에 community List
    @GET("community/{id}")
    Call<CMRespDto<Community>> comFindById(@Path("id") long id);

    // 댓글 찾기
    @GET("cReply")
    Call<CMRespDto<List<CReply>>> cReplyFindAll();

    // 커뮤니티 저장
    @POST("community")
    Call<CMRespDto<Community>> comSave();

    //--- community End ---

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.9:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
