package com.cos.nomadapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.ui.community.CommunityDetailActivity;
import com.cos.nomadapp.R;
import com.cos.nomadapp.model.community.Community;

import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder>{

    private List<Community> communities;
    private Context mContext;
    private static final String TAG = "CommunityAdapter";
    public CommunityAdapter(List<Community> communities, Context mContext) {
        this.communities = communities;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item, parent, false);
        return new CommunityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder holder, int position) {
        holder.setCommunityItem(communities.get(position));

    }

    @Override
    public int getItemCount() {
        return communities.size();
    }

    public class CommunityViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCommunityTitle,tvCommunityCategory,tvRegTime, tvUsername,tvReplyCount;
        private AppCompatButton btnLike;

        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCommunityTitle = itemView.findViewById(R.id.tv_community_title);
            tvCommunityCategory = itemView.findViewById(R.id.tv_community_category);
            tvRegTime = itemView.findViewById(R.id.tv_reg_time);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvReplyCount = itemView.findViewById(R.id.tv_reply_count);
            btnLike = itemView.findViewById(R.id.btn_like);

            itemView.setOnClickListener(v->{
                int pos = getAdapterPosition();

                Intent intent = new Intent(mContext, CommunityDetailActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("community",communities.get(pos));
                mContext.startActivity(intent);
            });
        }

        public void setCommunityItem(Community community){
            tvCommunityTitle.setText(community.getTitle());
            tvCommunityCategory.setText(community.getCategory().getTitle());
            tvRegTime.setText(community.getCreateDate().toString());
            tvUsername.setText(community.getUser().getName());
            tvReplyCount.setText(community.getReplys().size()+"");
        }
    }

    public void filterList(List<Community> filterItems) {
        this.communities = filterItems;
        notifyDataSetChanged();
    }
}