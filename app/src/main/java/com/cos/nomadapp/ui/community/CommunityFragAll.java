package com.cos.nomadapp.ui.community;

import android.content.Context;
import android.os.Bundle;
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
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.model.reply.Reply;
import com.cos.nomadapp.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class CommunityFragAll extends Fragment {

    private RecyclerView rvCommunityNew;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.community_frag_all,container,false);

        rvCommunityNew = view.findViewById(R.id.rv_community_all);

        mContext = container.getContext();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        List<Community> communities = new ArrayList<>();

        User user = User.builder()
                .name("유저1")
                .build();

        List<Reply> replies = new ArrayList<>();
        User replyUser = User.builder()
                .name("유저2")
                .build();
        Reply reply = new Reply(0L,"댓글내용1",replyUser,1,"1 hours ago");
        replies.add(reply);

        reply = new Reply(1L,"댓글내용2",replyUser,0,"1 hours ago");
        replies.add(reply);

        reply = new Reply(2L,"댓글내용3",replyUser,7,"1 hours ago");
        replies.add(reply);

        reply = new Reply(3L,"댓글내용2",replyUser,0,"1 hours ago");
        replies.add(reply);

        reply = new Reply(4L,"댓글내용3",replyUser,7,"1 hours ago");
        replies.add(reply);


        for (Long i = 0L ; i<5L;i++){

            Community community = new Community(i,"커뮤니티 제목"+i, user, replies , "내용",15,"#bla-bla","6 days ago");

            communities.add(community);
        }

        rvCommunityNew.setLayoutManager(manager);

        rvCommunityNew.setAdapter(new CommunityAdapter(communities,mContext));



        return view;
    }
}
