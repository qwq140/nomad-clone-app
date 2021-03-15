package com.cos.nomadapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.ui.courses.CourseDetailActivity;
import com.cos.nomadapp.R;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.CommonTitle;
import com.cos.nomadapp.model.Item;
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

        //0 : CourseTitle 1 : Course 2: CoursesFilter
        if(viewType == 0){
            return new TitleViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.title_item,
                            parent,
                            false
                    )
            );
        }else if(viewType == 1){
            return new CourseViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_item,
                            parent,
                            false
                    )
            );
        }else {
            return new FilterViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.courses_filter_item,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            CommonTitle commonTitle = (CommonTitle) items.get(position).getObject();
            ((TitleViewHolder) holder).setTitleItem(commonTitle);
        }else if(getItemViewType(position)==1){
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
            tvTitle = itemView.findViewById(R.id.tv_course_title);
            tvSubTitle = itemView.findViewById(R.id.tv_course_subtitle);

            itemView.setOnClickListener(v -> {
                Context context = v.getContext();
                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            });
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
            tvCoursesTitle = itemView.findViewById(R.id.tv_title);
            tvCoursesSubTitle = itemView.findViewById(R.id.tv_subtitle);
        }

        void setTitleItem(CommonTitle commonTitle){
            tvCoursesTitle.setText(commonTitle.getTitle());
            tvCoursesSubTitle.setText(commonTitle.getSubTitle());
        }
    }

    public static class FilterViewHolder extends RecyclerView.ViewHolder{

        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}