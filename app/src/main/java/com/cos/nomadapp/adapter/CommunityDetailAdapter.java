package com.cos.nomadapp.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.model.community.CReply;
import com.cos.nomadapp.ui.community.CommunityDetailActivity;
import com.cos.nomadapp.R;
import com.cos.nomadapp.model.common.Item;
import com.cos.nomadapp.model.community.Community;

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
            CReply creply = (CReply) items.get(position).getObject();
            ((ReplyViewHolder) holder).setItem(creply);
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

        private AppCompatButton btnCommunityLike, btnReply,btnsendReply;
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
            btnsendReply=itemView.findViewById(R.id.iv_reply_send);

        }

        void setItem(Community community){
            tvDetailTitle.setText(community.getTitle());
            tvDetailContent.setText(Html.fromHtml(community.getContent()));
            tvDetailUsername.setText(community.getUser().getName());
            tvDetailCategory.setText(community.getCategory().getTitle());
            tvDetailTime.setText(community.getCreateDate().toString());
            btnCommunityLike.setText(community.getCount().toString());
            tvReplyCount.setText(community.getReplys().size()+"");
        }
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder{

        private AppCompatButton btnReplyLike;
        private TextView tvReplyContent, tvReplyUsername, tvReplyTime,tvReplyCount;

        public ReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnReplyLike = itemView.findViewById(R.id.btn_reply_like);
            tvReplyUsername = itemView.findViewById(R.id.tv_reply_username);
            tvReplyContent = itemView.findViewById(R.id.tv_reply_content);
            tvReplyTime = itemView.findViewById(R.id.tv_reply_time);
            tvReplyCount = itemView.findViewById(R.id.tv_reply_count);
        }

        void setItem(CReply cReply){
            btnReplyLike.setText("0");
            tvReplyUsername.setText(cReply.getUser().getName());
            tvReplyContent.setText(cReply.getContent());
            tvReplyTime.setText(cReply.getCreateDate().toString());
        }

    }


}