package com.cos.nomadapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.adapter.CommunityAdapter;
import com.cos.nomadapp.model.community.Community;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragAll extends Fragment {

    private RecyclerView rvCommunityNew;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_frag_all,container,false);

        rvCommunityNew = view.findViewById(R.id.rv_community_all);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        List<Community> communities = new ArrayList<>();

        for (Long i = 0L ; i<5L;i++){
            Community community = new Community(i,"커뮤니티 제목"+i, "username"+i, "내용",15,"bla-bla","15");
            communities.add(community);
        }

        rvCommunityNew.setLayoutManager(manager);

        rvCommunityNew.setAdapter(new CommunityAdapter(communities));


        return view;
    }
}
