package com.cos.nomadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cos.nomadapp.R;
import com.cos.nomadapp.model.tech.Tech;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class TechAdapter extends RecyclerView.Adapter<TechAdapter.MyViewHolder>{

    private final List<Tech> teches;
    private Context context;

    public TechAdapter(List<Tech> teches, Context context) {

        this.teches = teches;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.course_detail_logo_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(teches.get(position));


    }


    @Override
    public int getItemCount() {
        return teches.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView ivTech;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTech = itemView.findViewById(R.id.iv_session1_logo);
        }

        public void setItem(Tech tech){
            Glide
                    .with(context)
                    .load(tech.getFile().getFileUrl()) // 임시 테스트로 넘어오는 이미지는 localhost라서 적용이 안됨
                    .centerCrop()
                    .placeholder(R.drawable.ic_js)
                    .into(ivTech);
        }

    }
}