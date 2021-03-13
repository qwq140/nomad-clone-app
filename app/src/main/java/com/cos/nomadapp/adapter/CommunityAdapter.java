package com.cos.nomadapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.model.community.Community;

import java.util.ArrayList;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.CommunityViewHolder>{

    private List<Community> communities;

    public CommunityAdapter(List<Community> communities) {
        this.communities = communities;
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

        private TextView tvCommunityTitle,tvCommunityCategory;

        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCommunityTitle = itemView.findViewById(R.id.tv_community_title);
            tvCommunityCategory = itemView.findViewById(R.id.tv_community_category);
        }

        public void setCommunityItem(Community community){
            tvCommunityTitle.setText(community.getTitle());
            tvCommunityCategory.setText(community.getCategory());
        }
    }

    public void filterList(List<Community> filterItems) {

        this.communities = filterItems;
        notifyDataSetChanged();
    }
}
