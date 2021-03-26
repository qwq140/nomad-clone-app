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
import com.cos.nomadapp.model.community.Category;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.service.NomadApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityFragSub extends Fragment {
    private RecyclerView rvCommunityNew;
    private Context mContext;
    private static final String TAG = "CommunityFragAll";
    private List<Community> communities;
    private Long id;                //카테고리의 아이디

    public CommunityFragSub(Long id) {
        this.id=id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_frag_bla,container,false);

        rvCommunityNew = view.findViewById(R.id.rv_community_sub);
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
                for(int i=0;i<response.body().getData().size();i++){
                    if(response.body().getData().get(i).getCategory().getId()==id){
                        communities.add(response.body().getData().get(i));
                    }
                }
                rvCommunityNew.setLayoutManager(manager);
                rvCommunityNew.setAdapter(new CommunityAdapter(communities,mContext));
            }

            @Override
            public void onFailure(Call<CMRespDto<List<Community>>> call, Throwable t) {
                Log.d(TAG, "onFailure: 뿌리기 실패");
            }

        });

        return view;
    }
}
