package com.cos.nomadapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CoursesAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> items;

    public CoursesAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //0 : CourseTitle 1 : Course
        if(viewType == 0){
            return new TitleViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_title,
                            parent,
                            false
                    )
            );
        }else{
            return new CourseViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_item,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            CoursesTitle coursesTitle = (CoursesTitle) items.get(position).getObject();
            ((TitleViewHolder) holder).setTitleItem(coursesTitle);
        }else{
            Course course = (Course) items.get(position).getObject();
            ((CourseViewHolder) holder).setCourseItem(course);
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

    public static class CourseViewHolder extends RecyclerView.ViewHolder{

        private RoundedImageView ivCourse;
        private TextView tvTitle, tvSubTitle;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCourse = itemView.findViewById(R.id.iv_course);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSubTitle = itemView.findViewById(R.id.tv_subtitle);
        }

        void setCourseItem(Course course){
            ivCourse.setImageResource(course.getCourseImage());
            tvTitle.setText(course.getTitle());
            tvSubTitle.setText(course.getSubTitle());
        }
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCoursesTitle, tvCoursesSubTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCoursesTitle = itemView.findViewById(R.id.tv_courses_title);
            tvCoursesSubTitle = itemView.findViewById(R.id.tv_courses_subtitle);
        }

        void setTitleItem(CoursesTitle coursesTitle){
            tvCoursesTitle.setText(coursesTitle.getTitle());
            tvCoursesSubTitle.setText(coursesTitle.getSubTitle());
        }
    }
}