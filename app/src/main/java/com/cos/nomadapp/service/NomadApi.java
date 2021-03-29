package com.cos.nomadapp.service;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.community.CReply;
import com.cos.nomadapp.model.community.CReplySaveReqDto;
import com.cos.nomadapp.model.community.Category;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.model.community.CommunitySaveReqDto;
import com.cos.nomadapp.model.faq.Faq;
import com.cos.nomadapp.model.faq.FaqCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NomadApi {


    @GET("faq/category")
    Call<CMRespDto<List<FaqCategory>>> faqCategoryFindAll();

    @GET("faq/{id}")
    Call<CMRespDto<Faq>> faqFindById(@Path("id") long id);

    //--- community Start ---
    // 카테고리
    @GET("category")
    Call<CMRespDto<List<Category>>> comCategoryFindAll();

    // 커뮤니티 게시글
    @GET("community")
    Call<CMRespDto<List<Community>>> comFindAll();

    // 카테고리 한개에 community List
    @GET("community/{id}")
    Call<CMRespDto<Community>> comFindById(@Path("id") long id);

    // 모든 댓글 찾기
    @GET("cReply")
    Call<CMRespDto<List<CReply>>> cReplyFindAll();

    // 댓글 저장
    @POST("cReply")
    Call<CMRespDto<CReply>> cReplySave(@Header("Authorization") String token, @Body CReplySaveReqDto cReplySaveReqDto);

    // 게시글 저장
    @POST("community")
    Call<CMRespDto<Community>> comSave(@Header("Authorization") String token, @Body CommunitySaveReqDto communitySaveReqDto);


    //--- community End ---

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.17.100.102:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
