package com.cos.nomadapp.service;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.pay.FreeSaveReqDto;
import com.cos.nomadapp.model.pay.Pay;
import com.cos.nomadapp.model.pay.PayCheckReqDto;
import com.cos.nomadapp.model.pay.PaySaveReqDto;
import com.cos.nomadapp.model.tech.Tech;
import com.cos.nomadapp.model.community.CReplySaveReqDto;
import com.cos.nomadapp.model.community.CommunitySaveReqDto;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.courses.CoursesPreview;
import com.cos.nomadapp.model.faq.Faq;
import com.cos.nomadapp.model.faq.FaqCategory;
import com.cos.nomadapp.model.user.LoginDto;
import com.cos.nomadapp.model.user.UserUpdateReqDto;
import com.cos.nomadapp.model.community.CReply;
import com.cos.nomadapp.model.community.Category;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.model.video.Video;
import com.cos.nomadapp.model.video.VideoReply;
import com.cos.nomadapp.model.video.dto.VideoReplySaveReqDto;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("user/load")
    Call<CMRespDto<LoginDto>> loadUser(@Header("Authorization") String token);

    @GET("courses/filter")
    Call<CMRespDto<List<CoursesPreview>>> getAllCourses(@Query("level") String level, @Query("isFree") String isFree, @Query("techId") long techId);

    @GET("courses/{id}")
    Call<CMRespDto<Course>> getDetailCourses(@Path("id") long id);

    @GET("homeCourses")
    Call<CMRespDto<List<CoursesPreview>>> getHomeCourses();

    @GET("video/{id}")
    Call<CMRespDto<Video>> getVideoList(@Header("Authorization") String token , @Path("id") long id);

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

    // 게시글 저장
    @POST("community")
    Call<CMRespDto<Community>> comSave(@Header("Authorization") String token, @Body CommunitySaveReqDto communitySaveReqDto);

    // 댓글 저장
    @POST("cReply")
    Call<CMRespDto<CReply>> cReplySave(@Header("Authorization") String token, @Body CReplySaveReqDto cReplySaveReqDto);
    //--- community End ---

    // 비디오 댓글
    @POST("vReply")
    Call<CMRespDto<VideoReply>> videoReplySave(@Header("Authorization") String token, @Body VideoReplySaveReqDto videoReplySaveReqDto);

    // 이미지 업로드
    @Multipart
    @POST("profile")
    Call<CMRespDto> postImage(@Header("Authorization") String token ,@Part MultipartBody.Part uploadFile);

    // 테크 목록
    @GET("tech")
    Call<CMRespDto<List<Tech>>> getTechList();

    //pay
    @POST("pay")
    Call<CMRespDto> paySave(@Header("Authorization") String token, @Body PaySaveReqDto paySaveReqDto);

    @GET("pay/{userId}")
    Call<CMRespDto<List<Pay>>> myPayList(@Header("Authorization") String token, @Path("userId") long id);

    @POST("pay/check")
    Call<CMRespDto<Pay>> payCheck(@Header("Authorization") String token, @Body PayCheckReqDto payCheckReqDto);

    @POST("pay/free")
    Call<CMRespDto> freePay(@Header("Authorization") String token, @Body FreeSaveReqDto freeSaveReqDto);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.30.1.53:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
