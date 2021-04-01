package com.cos.nomadapp.ui.community;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.adapter.CommunityAdapter;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.service.NomadApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityFragAll extends Fragment {

    private RecyclerView rvCommunityNew;
    private Context mContext;
    private static final String TAG = "CommunityFragAll";
    private List<Community> communities;
    private CommunityAdapter communityAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_frag_all,container,false);

        rvCommunityNew = view.findViewById(R.id.rv_community_all);
        mContext = container.getContext();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        communities= new ArrayList<>();

        //전체 커뮤니티 불러오기
        //RecyclerView

        NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
        Call<CMRespDto<List<Community>>> call = nomadApi.comFindAll();
        call.enqueue(new Callback<CMRespDto<List<Community>>>() {

            @Override
            public void onResponse(Call<CMRespDto<List<Community>>> call, Response<CMRespDto<List<Community>>> response) {
                Log.d(TAG, "onResponse: 데이터받아온다 :"+ response.body().getData());
                communities = (List<Community>) response.body().getData();
                rvCommunityNew.setLayoutManager(manager);
                communityAdapter =new CommunityAdapter(communities,mContext);
                rvCommunityNew.setAdapter(communityAdapter);
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Community>>> call, Throwable t) {

            }
        });


        return view;
    }
}