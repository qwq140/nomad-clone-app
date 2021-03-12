package com.cos.nomadapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.R;

import java.util.List;

public class CourseLectureAfterAdapter extends RecyclerView.Adapter<CourseLectureAfterAdapter.MyViewHolder>{

    private final List<String> lectureAfter;

    public CourseLectureAfterAdapter(List<String> lectureAfter) {

        this.lectureAfter = lectureAfter;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.detail_lecture_after,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(lectureAfter.get(position));

    }


    @Override
    public int getItemCount() {
        return lectureAfter.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvLectureAfter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLectureAfter = itemView.findViewById(R.id.tv_lecture_after);
        }

        public void setItem(String lectureAfter){
            tvLectureAfter.setText(lectureAfter);
            tvLectureAfter.setTextColor(Color.parseColor("#FFFFFF"));

        }
    }
}