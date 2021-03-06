package com.cos.nomadapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.model.CommonTitle;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.main.MainTitle;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class MainAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> items;

    public MainAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // type 0 : MainTitleItem 1 : CourseItem 2: main_link_item
        if(viewType == 0){
            return new TitleViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.main_title_item,
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
        }else{
            return new LinkViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.main_link_item,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            MainTitle mainTitle = (MainTitle) items.get(position).getObject();
            ((TitleViewHolder) holder).setMainTitleItem(mainTitle);
        }else if(getItemViewType(position)==1){
            Course course = (Course) items.get(position).getObject();
            ((CourseViewHolder) holder).setCourseItem(course);
        }else if(getItemViewType(position)==2){
            String link = (String)items.get(position).getObject();
            ((LinkViewHolder) holder).setMainLinkItem(link);
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

    public static class TitleViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle, tvSubTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_main_title);
            tvSubTitle = itemView.findViewById(R.id.tv_main_subtitle);
        }

        void setMainTitleItem(MainTitle mainTitle){
            tvTitle.setText(mainTitle.getTitle());
            tvSubTitle.setText(mainTitle.getSubTitle());
        }
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

    public static class LinkViewHolder extends RecyclerView.ViewHolder{

        private TextView tvLink;

        public LinkViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLink = itemView.findViewById(R.id.tv_link);
        }

        void setMainLinkItem(String link){
            tvLink.setText(link);
        }
    }

}