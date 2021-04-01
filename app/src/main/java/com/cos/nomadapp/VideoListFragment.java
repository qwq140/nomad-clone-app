package com.cos.nomadapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.adapter.CourseCurriculumAdapter;
import com.cos.nomadapp.adapter.VideoListAdapter;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.courses.Curriculum;
import com.cos.nomadapp.model.video.Video;
import com.cos.nomadapp.model.video.VideoContent;
import com.cos.nomadapp.service.NomadApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoListFragment extends Fragment {

    private static final String TAG = "VideoListFragment";
    
    private RecyclerView rvVideoList;
    private TextView tvVlistTitle;
    private long videoId;
    private NomadApi nomadApi;
    private SharedPreferences pref;
    private Context context;
    private String token;
    
    public VideoListFragment(long videoId) {
        this.videoId = videoId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.video_frag_list, container, false );
        context = getActivity();
        tvVlistTitle = view.findViewById(R.id.tv_vlist_title);
        rvVideoList = view.findViewById(R.id.rv_video_list);
        
        nomadApi = NomadApi.retrofit.create(NomadApi.class);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        pref = context.getSharedPreferences("pref",Context.MODE_PRIVATE);
        token = pref.getString("token","");
        Call<CMRespDto<Video>> call = nomadApi.getVideoList("Bearer " + token, videoId);
        call.enqueue(new Callback<CMRespDto<Video>>() {
            @Override
            public void onResponse(Call<CMRespDto<Video>> call, Response<CMRespDto<Video>> response) {
                if (response.body()==null){
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(intent);
                } else {
                    Log.d(TAG, "onResponse: "+response.body());
                    Video video = response.body().getData();
                    tvVlistTitle.setText(video.getName());
                    List<Curriculum> curriculumList = new ArrayList<>();

                    for(int i = 0 ; i < video.getContents().size() ; i++){
                        Curriculum curriculum = new Curriculum();
                        curriculum.setChapter(video.getContents().get(i).get("title").toString());
                        List<VideoContent> curriculumContent = new ArrayList<>();
                        Log.d(TAG, "section8: chapter : "+curriculum.getChapter());
                        Log.d(TAG, "section8: "+video.getContents().get(i).get("list"));
                        List<Map<String, Object>> content = (List<Map<String, Object>>)video.getContents().get(i).get("list");
                        Log.d(TAG, "section8: "+content);
                        for (int j=0; j<content.size();j++){
                            Log.d(TAG, "section8: curriculumContent"+content.get(j));
                            VideoContent videoContent = VideoContent.builder()
                                    .title(content.get(j).get("title").toString())
                                    .isFree(Boolean.parseBoolean(content.get(j).get("isFree").toString()))
                                    .vimeoId(content.get(j).get("vimeoId").toString())
                                    .build();
                            curriculumContent.add(videoContent);
                        }
                        curriculum.setContents(curriculumContent);
                        curriculumList.add(curriculum);
                    }

                    LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                    rvVideoList.setLayoutManager(manager);
                    rvVideoList.setAdapter(new VideoListAdapter(curriculumList,context, video.getId()));
                }

            }

            @Override
            public void onFailure(Call<CMRespDto<Video>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
