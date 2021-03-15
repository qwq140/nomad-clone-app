package com.cos.nomadapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.ui.community.CommunityDetailActivity;
import com.cos.nomadapp.R;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.community.Community;
import com.cos.nomadapp.model.reply.Reply;

import java.util.List;

public class CommunityDetailAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> items;
    private CommunityDetailActivity communityDetailActivity;

    public CommunityDetailAdapter(List<Item> items, CommunityDetailActivity communityDetailActivity) {

        this.items = items;
        this.communityDetailActivity = communityDetailActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == 0){
            return new DetailContentViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.community_detail_content,
                            parent,
                            false
                    )
            );
        }else {
            return new ReplyViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.community_detail_reply,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            Community community = (Community) items.get(position).getObject();
            ((DetailContentViewHolder) holder).setItem(community);
        }else if(getItemViewType(position)==1){
            Reply reply = (Reply) items.get(position).getObject();
            ((ReplyViewHolder) holder).setItem(reply);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public class DetailContentViewHolder extends RecyclerView.ViewHolder{

        private AppCompatButton btnCommunityLike, btnReply;
        private TextView tvDetailTitle, tvDetailContent, tvDetailUsername, tvDetailTime, tvReplyCount, tvDetailCategory;


        public DetailContentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetailTitle = itemView.findViewById(R.id.tv_detail_title);
            tvDetailContent = itemView.findViewById(R.id.tv_detail_content);
            tvDetailUsername = itemView.findViewById(R.id.tv_detail_username);
            tvDetailCategory = itemView.findViewById(R.id.tv_detail_category);
            tvDetailTime = itemView.findViewById(R.id.tv_detail_time);
            tvReplyCount = itemView.findViewById(R.id.tv_reply_count);
            btnCommunityLike = itemView.findViewById(R.id.btn_community_like);
            btnReply = itemView.findViewById(R.id.btn_reply);
            btnReply.setOnClickListener(v -> {
                communityDetailActivity.showReplyInput();
            });


        }

        void setItem(Community community){
            tvDetailTitle.setText(community.getTitle());
            tvDetailContent.setText(community.getContent());
            tvDetailUsername.setText(community.getUser().getUsername());
            tvDetailCategory.setText(community.getCategory());
            tvDetailTime.setText(community.getRegTime());
            tvReplyCount.setText(community.getReply().size()+"");
            btnCommunityLike.setText(community.getLike()+"");


        }
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder{

        private AppCompatButton btnReplyLike;
        private TextView tvReplyContent, tvReplyUsername, tvReplyTime;

        public ReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnReplyLike = itemView.findViewById(R.id.btn_reply_like);
            tvReplyUsername = itemView.findViewById(R.id.tv_reply_username);
            tvReplyContent = itemView.findViewById(R.id.tv_reply_content);
            tvReplyTime = itemView.findViewById(R.id.tv_reply_time);

        }

        void setItem(Reply reply){
            btnReplyLike.setText(reply.getLike()+"");
            tvReplyUsername.setText(reply.getUser().getUsername());
            tvReplyContent.setText(reply.getContent());
            tvReplyTime.setText(reply.getRegTime());

        }
    }


}