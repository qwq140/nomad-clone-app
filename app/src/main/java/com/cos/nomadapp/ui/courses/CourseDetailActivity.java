package com.cos.nomadapp.ui.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cos.nomadapp.R;
import com.cos.nomadapp.UserDashboardActivity;
import com.cos.nomadapp.adapter.CourseConceptAdapter;
import com.cos.nomadapp.adapter.CourseCurriculumAdapter;
import com.cos.nomadapp.adapter.CourseFaqAdapter;
import com.cos.nomadapp.adapter.CourseLectureAfterAdapter;
import com.cos.nomadapp.adapter.CourseLevelContentAdapter;
import com.cos.nomadapp.adapter.CourseLogoAdapter;
import com.cos.nomadapp.adapter.CourseSimpleAdapter;
import com.cos.nomadapp.adapter.CourseSkillAdapter;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.courses.Concept;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.courses.CourseFaqContent;
import com.cos.nomadapp.model.courses.CourseFaqTitle;
import com.cos.nomadapp.model.courses.CourseSimple;
import com.cos.nomadapp.model.courses.Curriculum;
import com.cos.nomadapp.service.NomadApi;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseDetailActivity extends AppCompatActivity {

    private static final String TAG = "CourseDetailActivity";
    
    private TextView tvToolbarTitle;
    private ImageView ivBack;
    private RoundedImageView rivUser;

    private Course course;

    private ImageView mainImage;
    private TextView tvMainTitle, tvMainSubTitle, tvMainLevel;
    private RecyclerView rvLangLogo;

    private ConstraintLayout layoutSection1;

    private TextView tvVideoCount, tvVideoMinute, tvVideoLevel;

    private RecyclerView rvCourseSimpleInfo;

    private RecyclerView rvConcept;
    private TextView tvConceptTitle;
    private LinearLayout layoutSection4;

    private RecyclerView rvSkillPackages, rvLevelContent, rvLectureAfter, rvCourseCurriculum, rvCourseFaq;
    private TextView tvSkillName, tvSkillSection, tvLectureAfterTitle, tvPayTitle, tvPrice;
    private LinearLayout layoutSection5, layoutSection7, layoutTopSection9;

    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        init();


        NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
        Call<CMRespDto<Course>> call = nomadApi.getDetailCourses(id);
        call.enqueue(new Callback<CMRespDto<Course>>() {
            @Override
            public void onResponse(Call<CMRespDto<Course>> call, Response<CMRespDto<Course>> response) {
                Log.d(TAG, "onResponse: 코스 디테일 : "+response.body());
                course = response.body().getData();
                itemSetting();

            }

            @Override
            public void onFailure(Call<CMRespDto<Course>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });



        //roundedImageView 이벤트
        rivUser = (RoundedImageView) findViewById(R.id.riv_user);
        rivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p = new PopupMenu(
                        getApplicationContext(),//화면제어권자
                        v);             //팝업을 띄울 기준이될 위젯
                getMenuInflater().inflate(R.menu.user_menu, p.getMenu());
                //이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().equals("Dashboard")) {
                            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(v.getContext(), UserDashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            v.getContext().startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                p.show();
            }
        });
        //roundedImageView End
    }

    private void init(){
        // 페이지 넘어올때 id 가져오기
        Intent intent = getIntent();
        id = intent.getLongExtra("id",0);
        Log.d(TAG, "onCreate: id"+id);

        // 뒤로가기 버튼
        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            finish();
        });

        // 앱바 디자인
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("");

        // section1
        mainImage = findViewById(R.id.pageImage);
        tvMainTitle = findViewById(R.id.tv_page_title);
        tvMainSubTitle = findViewById(R.id.tv_page_subtitle);
        tvMainLevel = findViewById(R.id.tv_section1_level);
        rvLangLogo = findViewById(R.id.rv_lang_logo);
        layoutSection1 = findViewById(R.id.layout_section1);

        // section2
        tvVideoCount = findViewById(R.id.tv_video_count);
        tvVideoMinute = findViewById(R.id.tv_video_minute);
        tvVideoLevel = findViewById(R.id.tv_video_level);

        // section3
        rvCourseSimpleInfo = findViewById(R.id.rv_course_simple_info);

        // section4
        rvConcept = findViewById(R.id.rv_concept);
        tvConceptTitle = findViewById(R.id.tv_section4_title);
        layoutSection4 = findViewById(R.id.layout_section4);

        // section5
        rvSkillPackages = findViewById(R.id.rv_skill_packages);
        tvSkillName = findViewById(R.id.tv_skill_name);
        tvSkillSection = findViewById(R.id.tv_skill_section);
        layoutSection5 = findViewById(R.id.layout_section5);

        // section6
        rvLevelContent= findViewById(R.id.rv_level_content);

        // section7
        rvLectureAfter = findViewById(R.id.rv_lecture_after);
        tvLectureAfterTitle = findViewById(R.id.tv_lecture_after_title);
        layoutSection7 = findViewById(R.id.layout_section7);

        // section8
        rvCourseCurriculum = findViewById(R.id.rv_course_curriculum);

        // section9
        layoutTopSection9 = findViewById(R.id.layout_top_section9);
        tvPayTitle = findViewById(R.id.tv_section9_title);
        tvPrice = findViewById(R.id.tv_price);

        // section10
        rvCourseFaq = findViewById(R.id.rv_course_faq);
    }

    private void itemSetting(){
        section1();
        section2();
        section3();
        section4();
        section5();
        section6();
        section7();
        section8();
        section9();
        section10();

    }
    private void section1(){
        Glide
                .with(CourseDetailActivity.this)
                .load("https://picsum.photos/200/300") // 임시 테스트로 넘어오는 이미지는 localhost라서 적용이 안됨
                .centerCrop()
                .placeholder(R.drawable.test)
                .into(mainImage);

        tvMainTitle.setText(course.getTitle());
        tvMainTitle.setTextColor(Color.parseColor(course.getTextColor()));
        tvMainSubTitle.setText(course.getSubTitle());
        tvMainSubTitle.setTextColor(Color.parseColor(course.getTextColor()));
        tvMainLevel.setText(course.getLevel());
        tvMainLevel.setTextColor(Color.parseColor(course.getBackgroundColor()));
        layoutSection1.setBackgroundColor(Color.parseColor(course.getBackgroundColor()));

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvLangLogo.setLayoutManager(manager);

        List<Map<String,Object>> simpleImage = course.getSimpleImage();
        List<String> logos = new ArrayList<>();
        for (Map<String,Object> i:simpleImage) {
            String logoUrl = i.get("url").toString();
            logos.add(logoUrl);
        }
        rvLangLogo.setAdapter(new CourseLogoAdapter(logos,this));

    }

    private void section2(){

        tvVideoCount.setText(course.getVideoInfo().get("count").toString()+"개");
        tvVideoMinute.setText(course.getVideoInfo().get("totalMinute").toString()+"분");
        tvVideoLevel.setText(course.getLevel());

    }

    private void section3(){
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCourseSimpleInfo.setLayoutManager(manager);
        List<Map<String,Object>> simpleInfo = course.getSimpleInfo();
        List<CourseSimple> simples = new ArrayList<>();
        for (Map<String,Object> i:simpleInfo) {
            Map<String,Object> image =(Map<String,Object>)i.get("image");
            CourseSimple courseSimple = CourseSimple.builder()
                    .simpleImage(image.get("url").toString())
                    .title(i.get("title").toString())
                    .content(i.get("content").toString())
                    .build();
            simples.add(courseSimple);
        }
        rvCourseSimpleInfo.setAdapter(new CourseSimpleAdapter(simples,this));

    }

    private void section4(){

        layoutSection4.setBackgroundColor(Color.parseColor(course.getBackgroundColor()));
        tvConceptTitle.setTextColor(Color.parseColor(course.getTextColor()));

        // concept, 잠시 임시데이터로 (디자인 고민)
        List<Concept> concepts = new ArrayList<>();
        // First concept
        Concept concept1 = new Concept();
        concept1.setTitle("Users");
        List<String> contents1 = new ArrayList<>();
        for (int i = 0 ; i<5 ; i++){
            contents1.add("content"+i);
        }
        concept1.setContents(contents1);
        concepts.add(concept1);

        Concept concept2 = new Concept();
        concept2.setTitle("Videos");
        List<String> contents2 = new ArrayList<>();
        for (int i = 0 ; i<5 ; i++){
            contents2.add("content"+i);
        }
        concept2.setContents(contents2);
        concepts.add(concept2);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvConcept.setLayoutManager(manager);
        rvConcept.setAdapter(new CourseConceptAdapter(concepts,this));


    }

    private void section5(){
        layoutSection5.setBackgroundColor(Color.parseColor(course.getBackgroundColor()));
        tvSkillName.setText(course.getSkill().get("name").toString());
        tvSkillSection.setText(course.getSkill().get("section").toString());
        List<Map<String, Object>> packages = (List<Map<String, Object>>) course.getSkill().get("package");
        List<String> constents = new ArrayList<>();
        for (Map<String, Object> packageContent:packages) {
            String content = packageContent.get("content").toString();
            constents.add(content);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvSkillPackages.setLayoutManager(manager);
        rvSkillPackages.setAdapter(new CourseSkillAdapter(constents,this));

    }

    private void section6(){
        List<Map<String,Object>> levelContents = course.getLevelContent();
        List<String> contents = new ArrayList<>();
        for (Map<String,Object> levelContent:levelContents) {
            String content = levelContent.get("content").toString();
            contents.add(content);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvLevelContent.setLayoutManager(manager);
        rvLevelContent.setAdapter(new CourseLevelContentAdapter(contents));
    }

    private void section7(){
        tvLectureAfterTitle.setTextColor(Color.parseColor(course.getTextColor()));
        layoutSection7.setBackgroundColor(Color.parseColor(course.getBackgroundColor()));
        List<Map<String,Object>> lectureAfters = course.getLectureAfter();
        List<String> contents = new ArrayList<>();
        for (Map<String,Object> lectureAfter:lectureAfters) {
            String content = lectureAfter.get("content").toString();
            contents.add(content);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvLectureAfter.setLayoutManager(manager);
        rvLectureAfter.setAdapter(new CourseLectureAfterAdapter(contents,course));
    }

    private void section8(){

        // 임시 데이터
        List<Curriculum> curriculumList = new ArrayList<>();

        Curriculum curriculum = new Curriculum();
        List<String> curriculumContent = new ArrayList<>();
        // 첫번째
        curriculum.setChapter("#0 INTRODUCTION");
        curriculumContent.add("#0.0 Read this First");
        curriculumContent.add("#0.1 What Are We Building");
        curriculumContent.add("#0.2 The State of Fullstack");
        curriculum.setContents(curriculumContent);
        curriculumList.add(curriculum);
        // 두번째
        curriculum.setChapter("#1 NODEJS THEORY");
        curriculumContent.add("#1.0 What is NodeJS");
        curriculumContent.add("#1.1 Use Cases for NodeJS");
        curriculumContent.add("#1.2 Who Uses NodeJS");
        curriculum.setContents(curriculumContent);
        curriculumList.add(curriculum);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCourseCurriculum.setLayoutManager(manager);
        rvCourseCurriculum.setAdapter(new CourseCurriculumAdapter(curriculumList,this));

    }

    private void section9(){
        layoutTopSection9.setBackgroundColor(Color.parseColor(course.getBackgroundColor()));
        tvPayTitle.setTextColor(Color.parseColor(course.getTextColor()));
        tvPrice.setText("월 "+course.getPrice());
    }

    private void section10(){

        // 임시 데이터
        List<CourseFaqTitle> titles = new ArrayList<>();

        List<CourseFaqContent> courseFaqContents1 = new ArrayList<>();
        CourseFaqContent courseFaqContent1 = new CourseFaqContent("수강신청을 하신 후에 언제든이요! 이 수업은 본인이 원하시는 시간에, 본인에게 맞는 속도와 스피드로 페이스를 조정하여, 언제든지 다시 반복하여 들을 수 있는 온라인 수업입니다.");
        courseFaqContents1.add(courseFaqContent1);

        CourseFaqTitle courseFaqTitle1 = new CourseFaqTitle("수업은 언제 시작하고 종료되나요?",courseFaqContents1);
        titles.add(courseFaqTitle1);

        List<CourseFaqContent> courseFaqContents2 = new ArrayList<>();
        CourseFaqContent courseFaqContent2 = new CourseFaqContent("수강신청을 하신 후에 언제든이요! 이 수업은 본인이 원하시는 시간에, 본인에게 맞는 속도와 스피드로 페이스를 조정하여, 언제든지 다시 반복하여 들을 수 있는 온라인 수업입니다.");
        courseFaqContents2.add(courseFaqContent2);

        CourseFaqTitle courseFaqTitle2 = new CourseFaqTitle("수업은 언제까지 들을 수 있나요?",courseFaqContents2);
        titles.add(courseFaqTitle2);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCourseFaq.setLayoutManager(manager);
        rvCourseFaq.setAdapter(new CourseFaqAdapter(titles));
    }
}