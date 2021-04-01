package com.cos.nomadapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.tech.Tech;
import com.cos.nomadapp.model.courses.CoursesPreview;
import com.cos.nomadapp.model.courses.PreviewImage;
import com.cos.nomadapp.service.NomadApi;
import com.cos.nomadapp.ui.courses.CourseDetailActivity;
import com.cos.nomadapp.FooterViewHolder;
import com.cos.nomadapp.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = "CoursesAdapter";

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FILTER = 1;
    private static final int TYPE_ITEM = 2;
    private static final int TYPE_FOOTER = 3;

    private List<CoursesPreview> coursesPreviews;
    private Context context;

    public CoursesAdapter(Context context, List<CoursesPreview> coursesPreviews) {
        this.context = context;
        this.coursesPreviews = coursesPreviews;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == TYPE_HEADER){
            return new TitleViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.title_item,
                            parent,
                            false
                    )
            );
        }else if(viewType == TYPE_FILTER){
            return new FilterViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.courses_filter_item,
                            parent,
                            false
                    )
            );

        }else if(viewType == TYPE_ITEM){
            return new CourseViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_item,
                            parent,
                            false
                    )
            );
        }else if (viewType == TYPE_FOOTER){
            return new FooterViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.footer,
                            parent,
                            false
                    )
            );
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleViewHolder){
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
            titleViewHolder.setTitleItem();
        } else if (holder instanceof FilterViewHolder){
            FilterViewHolder filterViewHolder = (FilterViewHolder) holder;
            filterViewHolder.setTechItem();
        } else if (holder instanceof  FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
        } else {
            CourseViewHolder courseViewHolder = (CourseViewHolder) holder;
            courseViewHolder.setCourseItem(coursesPreviews.get(position-2));
        }
    }

    @Override
    public int getItemCount() {
        return coursesPreviews.size()+3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_HEADER;
        } else if (position == 1){
            return TYPE_FILTER;
        }else if (position == coursesPreviews.size()+2){
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder{

        private RoundedImageView ivCourse;
        private TextView tvTitle, tvSubTitle, tvLevel;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCourse = itemView.findViewById(R.id.iv_course);
            tvTitle = itemView.findViewById(R.id.tv_course_title);
            tvSubTitle = itemView.findViewById(R.id.tv_course_subtitle);
            tvLevel = itemView.findViewById(R.id.tv_courses_level);
        }

        void setCourseItem(CoursesPreview coursesPreview){
            //ivCourse.setImageResource(coursesPreview.get());
            tvLevel.setText(coursesPreview.getLevel());
            tvTitle.setText(coursesPreview.getTitle());
            tvSubTitle.setText(coursesPreview.getSubTitle());

            PreviewImage previewImage =  coursesPreview.getPreviewImage();
            Log.d(TAG, "setCourseItem: "+previewImage.getUrl());
            // localhost로 들어오는건 안됨 ..... 나중에 배포할때는 가능할듯
            Glide
                    .with(context)
                    .load(previewImage.getUrl())
                    .centerCrop()
                    .placeholder(R.drawable.course_youtube)
                    .into(ivCourse);

            long id = coursesPreview.getId();

            Log.d(TAG, "setCourseItem: id : " +id);
            itemView.setOnClickListener(v -> {
                Context context = v.getContext();
                Intent intent = new Intent(context, CourseDetailActivity.class);
                intent.putExtra("id", id);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            });
        }
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCoursesTitle, tvCoursesSubTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCoursesTitle = itemView.findViewById(R.id.tv_title);
            tvCoursesSubTitle = itemView.findViewById(R.id.tv_subtitle);
        }

        void setTitleItem(){
            tvCoursesTitle.setText("All Courses");
            tvCoursesSubTitle.setText("초급부터 고급까지! 니꼬쌤과 함께 풀스택으로 성장하세요!");
        }
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvTech;

        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
            rvTech = itemView.findViewById(R.id.rv_tech);
        }

        public void setTechItem(){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context,4);
            rvTech.setLayoutManager(gridLayoutManager);

            NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
            Call<CMRespDto<List<Tech>>> call = nomadApi.getTechList();
            call.enqueue(new Callback<CMRespDto<List<Tech>>>() {
                @Override
                public void onResponse(Call<CMRespDto<List<Tech>>> call, Response<CMRespDto<List<Tech>>> response) {
                    Log.d(TAG, "onResponse: 성공 " + response.body());
                    List<Tech> teches = response.body().getData();
                    rvTech.setAdapter(new TechAdapter(teches,context));
                }

                @Override
                public void onFailure(Call<CMRespDto<List<Tech>>> call, Throwable t) {
                    Log.d(TAG, "onFailure: 실패");
                }
            });
        }
    }

}