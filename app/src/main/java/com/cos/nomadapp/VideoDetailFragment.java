package com.cos.nomadapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.community.CReply;
import com.cos.nomadapp.model.community.CReplySaveReqDto;
import com.cos.nomadapp.model.video.VideoContent;
import com.cos.nomadapp.model.video.VideoReply;
import com.cos.nomadapp.model.video.dto.VideoReplySaveReqDto;
import com.cos.nomadapp.service.NomadApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VideoDetailFragment extends Fragment {

    private static final String TAG = "VideoDetailFragment";

    private WebView videoView;
    private RecyclerView rvVideoReply;
    private Context context;
    private AppCompatButton btnVreply;

    private ImageView ivSendReply;
    private RelativeLayout replyBar;
    private boolean isReady=false;  //edittext열고 닫기 댓글쓰면 사라짐
    private EditText etReply;
    private TextView tvVideoTitle;

    private SharedPreferences pref;
    private String token;

    private VideoContent videoContent;

    public VideoDetailFragment(VideoContent videoContent) {
        this.videoContent = videoContent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.video_frag_detail, container, false );

        context = container.getContext();
        tvVideoTitle = view.findViewById(R.id.tv_video_title);
        tvVideoTitle.setText(videoContent.getTitle());
        videoView = view.findViewById(R.id.video_view);
//        videoView.getSettings().setAppCacheMaxSize( 10 * 1024 * 1024 ); // 10MB
//        videoView.getSettings().setAllowFileAccess( true );
//        videoView.getSettings().setAppCacheEnabled( true );
        videoView.getSettings().setJavaScriptEnabled( true );
//        videoView.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );
//        videoView.getSettings().setDomStorageEnabled(true);
//        videoView.setWebChromeClient(new WebChromeClient());
//        videoView.setWebViewClient(new WebViewClient());
        videoView.loadUrl("https://player.vimeo.com/video/180491843");

        rvVideoReply = view.findViewById(R.id.rv_video_reply);
        LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rvVideoReply.setLayoutManager(manager);

        replyBar = view.findViewById(R.id.reply_bar);
        replyBar.setVisibility(View.INVISIBLE);
        ivSendReply=view.findViewById(R.id.iv_reply_send);
        etReply = view.findViewById(R.id.et_reply);

        btnVreply = view.findViewById(R.id.btn_vrepley);
        btnVreply.setOnClickListener(v -> {
            showReplyInput();
        });

        ivSendReply.setOnClickListener(v -> {
            pref = context.getSharedPreferences("pref",Context.MODE_PRIVATE);
            token = pref.getString("token","");

            VideoReplySaveReqDto videoReplySaveReqDto =new VideoReplySaveReqDto();
            videoReplySaveReqDto.setContent(etReply.getText().toString());


            NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
            Call<CMRespDto<VideoReply>> call = nomadApi.videoReplySave("Bearer "+token,videoReplySaveReqDto);
            call.enqueue(new Callback<CMRespDto<VideoReply>>() {
                @Override
                public void onResponse(Call<CMRespDto<VideoReply>> call, Response<CMRespDto<VideoReply>> response) {
                    Log.d(TAG, "onResponse: test123 : "+response.body());
                }

                @Override
                public void onFailure(Call<CMRespDto<VideoReply>> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });
            shutdownReplyInput();
        });


        return view;
    }

    public void showReplyInput(){           //댓글 쓰기
        if(isReady){
            isReady=false;
            replyBar.setVisibility(View.INVISIBLE);
        }
        else{
            isReady=true;
            replyBar.setVisibility(View.VISIBLE);
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        etReply.requestFocus();
        imm.showSoftInput(etReply, InputMethodManager.SHOW_IMPLICIT);
    }
    public void shutdownReplyInput(){           //댓글 닫기
        replyBar.setVisibility(View.INVISIBLE);
        isReady=false;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        etReply.requestFocus();
        imm.hideSoftInputFromWindow(etReply.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
