package com.cos.nomadapp.service;

import android.provider.CallLog;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.community.CReplySaveReqDto;
import com.cos.nomadapp.model.community.CommunityFindReqDto;
import com.cos.nomadapp.model.community.CommunityItemRespDto;
import com.cos.nomadapp.model.community.CommunityListRespDto;
import com.cos.nomadapp.model.community.CommunitySaveReqDto;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.courses.CoursesPreview;
import com.cos.nomadapp.model.faq.Faq;
import com.cos.nomadapp.model.faq.FaqCategory;
import com.cos.nomadapp.model.likes.LikeClickRespDto;
import com.cos.nomadapp.model.likes.Likes;
import com.cos.nomadapp.model.user.UserUpdateReqDto;
import com.cos.nomadapp.model.community.CReply;
import com.cos.nomadapp.model.community.Category;
import com.cos.nomadapp.model.community.Community;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("/courses")
    Call<CMRespDto<List<CoursesPreview>>> getAllCourses();

    @GET("/courses/{id}")
    Call<CMRespDto<Course>> getDetailCourses(@Path("id") long id);

    @GET("/homeCourses")
    Call<CMRespDto<List<CoursesPreview>>> getHomeCourses();

    @GET("/admin/video/{id}")
    Call<CMRespDto> getVideoList(@Header("Authorization") String token ,@Path("id") long id);

    //--- community Start ---
    //카테고리 전체 찾기
    @GET("category")
    Call<CMRespDto<List<Category>>> comCategoryFindAll();

    //인기순 카테고리별 정렬
    @GET("community/popular/{id}")
    Call<CMRespDto<List<Community>>> findAllByCount(@Path("id") long id);

    //최신순 카테고리별 정렬
    @GET("community/new/{id}")
    Call<CMRespDto<List<Community>>> findAllByCreateDate(@Path("id") long id);

    // 전체 커뮤니티

    @GET("community")
    Call<CMRespDto<List<CommunityListRespDto>>> comFindAll(@Header("Authorization") String token, @Query("sort") String sort, @Query("categoryId")long categoryId,@Query("page") int page);

    // 커뮤니티 상세보기
    @GET("community/{id}")
    Call<CMRespDto<CommunityItemRespDto>> comFindById(@Header("Authorization") String token, @Path("id") Long communityId);

    // 커뮤니티 삭제하기
    @DELETE("community/{id}")
    Call<CMRespDto> comDelete(@Path("id") long id);

    // 모든 댓글 찾기
    @GET("cReply")
    Call<CMRespDto<List<CReply>>> cReplyFindAll();

    // 댓글 한 건 찾기
    @GET("/cReply/{id}")
    Call<CMRespDto<CReply>> cReplyFindById(@Path("id") long id);

    // 게시글 저장
    @POST("community")
    Call<CMRespDto<Community>> comSave(@Header("Authorization") String token, @Body CommunitySaveReqDto communitySaveReqDto);

    // 댓글 저장
    @POST("cReply")
    Call<CMRespDto<CReply>> cReplySave(@Header("Authorization") String token, @Body CReplySaveReqDto cReplySaveReqDto);
    //--- community End ---

    @DELETE("cReply/{id}")
    Call<CMRespDto> cReplyDelete(@Header("Authorization") String token,@Path("id") long id);


    //--- like ---
    @POST("like")
    Call<CMRespDto<LikeClickRespDto>> likeUp(@Header("Authorization") String token, @Body Long communityId);


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.4:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
