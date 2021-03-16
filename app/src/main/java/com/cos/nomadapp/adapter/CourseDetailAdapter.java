package com.cos.nomadapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.courses.Charge;
import com.cos.nomadapp.model.courses.Concept;
import com.cos.nomadapp.model.courses.CourseFaqTitle;
import com.cos.nomadapp.model.courses.CourseSection1;
import com.cos.nomadapp.model.courses.CourseSection2;
import com.cos.nomadapp.model.courses.CourseSection3;
import com.cos.nomadapp.model.courses.Curriculum;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items;
    private static final String TAG = "CourseDetailAdapter";
    private Context context;

    public CourseDetailAdapter(List<Item> items, Context context) {

        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new CourseDetailAdapter.CourseSectionFirstViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section1,
                            parent,
                            false
                    )
            );
        } else if (viewType == 1) {
            return new CourseDetailAdapter.CourseSectionSecondViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section2,
                            parent,
                            false
                    )
            );
        } else if(viewType == 2){
            return new CourseDetailAdapter.CourseSectionThirdViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section3,
                            parent,
                            false
                    )
            );
        } else if(viewType == 3){
            return new CourseDetailAdapter.CourseSectionFourthViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section4,
                            parent,
                            false
                    )
            );
        } else if(viewType == 4){
            return new CourseDetailAdapter.CourseSectionFifthViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section5,
                            parent,
                            false
                    )
            );
        } else if(viewType == 5){
            return new CourseDetailAdapter.CourseSectionSixthViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section6,
                            parent,
                            false
                    )
            );
        } else if(viewType == 6){
            return new CourseDetailAdapter.CourseSectionSeventhViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section7,
                            parent,
                            false
                    )
            );
        } else if(viewType == 7){
            return new CourseDetailAdapter.CourseSectionEighthViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section8,
                            parent,
                            false
                    )
            );
        } else {
            return new CourseDetailAdapter.CourseSectionNinthViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.course_detail_section9,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            CourseSection1 CourseSection1 = (CourseSection1) items.get(position).getObject();
            ((CourseSectionFirstViewHolder) holder).setCourseDetailItem(CourseSection1);

            List<Integer> logos = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                logos.add(R.drawable.test);
            }

            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
            ((CourseSectionFirstViewHolder) holder).rvLangLogo.setLayoutManager(manager);
            ((CourseSectionFirstViewHolder) holder).rvLangLogo.setAdapter(new CourseLogoAdapter(logos));
        } else if (getItemViewType(position) == 1) {
            CourseSection2 courseSection2 = (CourseSection2) items.get(position).getObject();
            ((CourseSectionSecondViewHolder) holder).setCourseDetailItem(courseSection2);
        } else if (getItemViewType(position) == 2) {
            CourseSection3 courseSection3 = (CourseSection3) items.get(position).getObject();
            ((CourseSectionThirdViewHolder) holder).setCourseDetailItem(courseSection3);
        } else if (getItemViewType(position) == 3) {
            List<Concept> concepts = (List<Concept>) items.get(position).getObject();
            ((CourseSectionFourthViewHolder) holder).setCourseDetailItem();

            GridLayoutManager manager = new GridLayoutManager(context, 2);
            ((CourseSectionFourthViewHolder) holder).rvConcept.setLayoutManager(manager);
            ((CourseSectionFourthViewHolder) holder).rvConcept.setAdapter(new CourseConceptAdapter(concepts, context));

        } else if (getItemViewType(position) == 4) {
            List<String> levelContent = (List<String>) items.get(position).getObject();

            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            ((CourseSectionFifthViewHolder) holder).rvLevelContent.setLayoutManager(manager);
            ((CourseSectionFifthViewHolder) holder).rvLevelContent.setAdapter(new CourseLevelContentAdapter(levelContent));

        } else if (getItemViewType(position) == 5) {
            List<String> lectureAfter = (List<String>) items.get(position).getObject();
            ((CourseSectionSixthViewHolder) holder).setCourseDetailItem();

            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            ((CourseSectionSixthViewHolder) holder).rvLectureAfter.setLayoutManager(manager);
            ((CourseSectionSixthViewHolder) holder).rvLectureAfter.setAdapter(new CourseLectureAfterAdapter(lectureAfter));

        } else if (getItemViewType(position) == 6) {
            List<Curriculum> curriculumList = (List<Curriculum>) items.get(position).getObject();

            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            ((CourseSectionSeventhViewHolder) holder).rvCourseCurriculum.setLayoutManager(manager);
            ((CourseSectionSeventhViewHolder) holder).rvCourseCurriculum.setAdapter(new CourseCurriculumAdapter(curriculumList,context));

        } else if (getItemViewType(position) == 7) {
            Charge charge = (Charge) items.get(position).getObject();
            ((CourseSectionEighthViewHolder) holder).setItem(charge);
        } else {
            List<CourseFaqTitle> titles = (List<CourseFaqTitle>) items.get(position).getObject();
            LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            ((CourseSectionNinthViewHolder) holder).rvCourseFaq.setLayoutManager(manager);
            ((CourseSectionNinthViewHolder) holder).rvCourseFaq.setAdapter(new CourseFaqAdapter(titles));
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

    public static class CourseSectionFirstViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivImage;
        private TextView tvTitle, tvSubTitle, tvLevel;
        private RecyclerView rvLangLogo;

        public CourseSectionFirstViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.pageImage);
            tvTitle = itemView.findViewById(R.id.tv_page_title);
            tvSubTitle = itemView.findViewById(R.id.tv_page_subtitle);
            tvLevel = itemView.findViewById(R.id.tv_section1_level);
            rvLangLogo = itemView.findViewById(R.id.rv_lang_logo);

            itemView.setBackgroundColor(Color.RED);
        }

        void setCourseDetailItem(CourseSection1 courseSection1){
            ivImage.setImageResource(courseSection1.getImage());
            tvTitle.setText(courseSection1.getTitle());
            tvTitle.setTextColor(Color.parseColor(courseSection1.getTextColor()));
            tvSubTitle.setText(courseSection1.getSubTitle());
            tvSubTitle.setTextColor(Color.parseColor(courseSection1.getTextColor()));
            tvLevel.setText(courseSection1.getLevel());
            tvLevel.setTextColor(Color.parseColor(courseSection1.getBackgroundColor()));

        }
    }

    public static class CourseSectionSecondViewHolder extends RecyclerView.ViewHolder{

        private TextView tvVideoCount, tvVideoMinute, tvVideoLevel;


        public CourseSectionSecondViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVideoCount = itemView.findViewById(R.id.tv_video_count);
            tvVideoMinute = itemView.findViewById(R.id.tv_video_minute);
            tvVideoLevel = itemView.findViewById(R.id.tv_video_level);



        }

        void setCourseDetailItem(CourseSection2 courseSection2){
            tvVideoCount.setText(courseSection2.getVideoCount()+"");
            tvVideoMinute.setText(courseSection2.getVideoMinute()+"분");
            tvVideoLevel.setText(courseSection2.getLevel());

        }
    }

    public static class CourseSectionThirdViewHolder extends RecyclerView.ViewHolder{

        private RoundedImageView ivThird;
        private TextView tvThirdTitle, tvThirdContent1, tvThirdContent2;


        public CourseSectionThirdViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThird = itemView.findViewById(R.id.iv_third);
            tvThirdTitle = itemView.findViewById(R.id.tv_third_title);
            tvThirdContent1 = itemView.findViewById(R.id.tv_third_content1);
            tvThirdContent2 = itemView.findViewById(R.id.tv_third_content2);
        }

        void setCourseDetailItem(CourseSection3 courseSection3){
            Log.d(TAG, "setCourseDetailItem:"+ courseSection3.getSimpleImage());
            ivThird.setImageResource(courseSection3.getSimpleImage());
            tvThirdTitle.setText(courseSection3.getSimpleTitle());
            tvThirdContent1.setText(courseSection3.getSimpleContent1());
            tvThirdContent2.setText(courseSection3.getSimpleContent2());

        }
    }

    public static class CourseSectionFourthViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvConcept;
        private TextView tvSectionForthTitle;

        public CourseSectionFourthViewHolder(@NonNull View itemView) {
            super(itemView);
            rvConcept = itemView.findViewById(R.id.rv_concept);
            itemView.setBackgroundColor(Color.RED);
            tvSectionForthTitle = itemView.findViewById(R.id.tv_section4_title);

        }

        void setCourseDetailItem(){
            // 색깔 데이터로 가져오기
            tvSectionForthTitle.setTextColor(Color.WHITE);
        }
    }

    public static class CourseSectionFifthViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvLevelContent;

        public CourseSectionFifthViewHolder(@NonNull View itemView) {
            super(itemView);
            rvLevelContent = itemView.findViewById(R.id.rv_level_content);
        }
    }

    public static class CourseSectionSixthViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvLectureAfter;
        private TextView tvTitle;

        public CourseSectionSixthViewHolder(@NonNull View itemView) {
            super(itemView);
            rvLectureAfter = itemView.findViewById(R.id.rv_lecture_after);
            itemView.setBackgroundColor(Color.parseColor("#f60000"));
            tvTitle = itemView.findViewById(R.id.tv_lecture_after_title);

        }

        void setCourseDetailItem(){
            // 색깔 데이터로 가져오기
            tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }

    public static class CourseSectionSeventhViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvCourseCurriculum;

        public CourseSectionSeventhViewHolder(@NonNull View itemView) {
            super(itemView);
            rvCourseCurriculum = itemView.findViewById(R.id.rv_course_curriculum);
        }
    }

    public static class CourseSectionEighthViewHolder extends RecyclerView.ViewHolder{

        private TextView tvChargeTitle, tvChargeContent, tvPrice, tvSectionTitle;
        private ConstraintLayout layout;

        public CourseSectionEighthViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChargeTitle = itemView.findViewById(R.id.tv_charge_title);
            tvChargeContent = itemView.findViewById(R.id.tv_charge_content);
            tvPrice = itemView.findViewById(R.id.tv_price);
            layout = itemView.findViewById(R.id.layout_section8);
            tvSectionTitle = itemView.findViewById(R.id.tv_section8_title);
        }

        public void setItem(Charge charge){
            tvChargeTitle.setText(charge.getTitle());
            tvChargeContent.setText(charge.getContent());
            tvPrice.setText(charge.getPrice());
            layout.setBackgroundColor(Color.parseColor(charge.getBackgroundColor()));
            tvSectionTitle.setTextColor(Color.parseColor(charge.getTextColor()));
        }
    }

    public static class CourseSectionNinthViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rvCourseFaq;

        public CourseSectionNinthViewHolder(@NonNull View itemView) {
            super(itemView);
            rvCourseFaq = itemView.findViewById(R.id.rv_course_faq);
        }

    }

}
