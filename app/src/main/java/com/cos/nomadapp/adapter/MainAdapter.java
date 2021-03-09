package com.cos.nomadapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.CoursesActivity;
import com.cos.nomadapp.R;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.challenge.Challenge;
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
        }else if (viewType == 2){
            return new LinkViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.main_link_item,
                            parent,
                            false
                    )
            );
        }else {
            return new ChallengeViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.challenge_item,
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
        }else{
            Challenge challenge = (Challenge) items.get(position).getObject();
            ((ChallengeViewHolder) holder).setChallengeItem(challenge);
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
            tvTitle = itemView.findViewById(R.id.tv_course_title);
            tvSubTitle = itemView.findViewById(R.id.tv_course_subtitle);
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

            tvLink.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), CoursesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                v.getContext().startActivity(intent);
            });
        }

        void setMainLinkItem(String link){
            tvLink.setText(link);
        }
    }

    public static class ChallengeViewHolder extends RecyclerView.ViewHolder{

        private TextView tvChallengeTitle, tvTerm, tvChallengers;

        public ChallengeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChallengeTitle = itemView.findViewById(R.id.tv_challenge_title);
            tvTerm = itemView.findViewById(R.id.tv_term);
            tvChallengers = itemView.findViewById(R.id.tv_challengers);
        }

        void setChallengeItem(Challenge challenge){
            tvChallengeTitle.setText(challenge.getTitle());
            tvChallengers.setText(challenge.getChallengers()+"");
            tvTerm.setText(challenge.getTerm()+"");
        }
    }

}