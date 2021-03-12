package com.cos.nomadapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.model.user.User;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CourseLogoAdapter extends RecyclerView.Adapter<CourseLogoAdapter.MyViewHolder>{

    private final List<Integer> logos;

    public CourseLogoAdapter(List<Integer> logos) {
        this.logos = logos;
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
        holder.setItem(logos.get(position));
    }


    @Override
    public int getItemCount() {
        return logos.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView ivLogo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.iv_session1_logo);
        }

        public void setItem(Integer logo){
            ivLogo.setImageResource(logo);
        }
    }
}