package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.adapter.CourseDetailAdapter;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.courses.Charge;
import com.cos.nomadapp.model.courses.Concept;
import com.cos.nomadapp.model.courses.CourseFaqContent;
import com.cos.nomadapp.model.courses.CourseFaqTitle;
import com.cos.nomadapp.model.courses.CourseSection1;
import com.cos.nomadapp.model.courses.CourseSection2;
import com.cos.nomadapp.model.courses.CourseSection3;
import com.cos.nomadapp.model.courses.Curriculum;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    private TextView tvToolbarTitle;
    private ImageView ivBack;
    private RecyclerView rvCourseDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("");

        ivBack.setOnClickListener(v -> {
            finish();
        });

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCourseDetail = findViewById(R.id.rv_course_detail);

        rvCourseDetail.setLayoutManager(manager);

        List<Item> items = new ArrayList<>();

        CourseSection1 courseSection1 = new CourseSection1(R.drawable.test,"[풀스택] 유튜브 클론코딩","유튜브 백엔드 + 프런트엔드 + 배포","초급","#f60000","#ffffff");
        items.add(new Item(0,courseSection1));


        CourseSection2 courseSection2 = new CourseSection2(131,1215,"초급");
        items.add(new Item(1,courseSection2));

        for (int i=0; i<3 ; i++){
            CourseSection3 courseSection3 = new CourseSection3(R.drawable.test,"클론코딩 이란","실용성 100% 교육 방식","클론코딩은 인스타그램, 카카오톡, 유튜브 등등 실제 서비스를 만들면서");
            items.add(new Item(2, courseSection3));
        }


        // concept 데이터 다운로드
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

        items.add(new Item(3,concepts));

        // LevelContent => 이 정도 수준인 분들 드루와요
        List<String> levelContent = new ArrayList<>();
        levelContent.add("이러한 부분들의 이해도가 필요합니다.");
        levelContent.add("이러한 분들은 오셔야 합니다.");
        levelContent.add("이러한 분들은 오셔야 합니다.");
        levelContent.add("이러한 분들은 오셔야 합니다.");

        items.add(new Item(4,levelContent));

        // 결과적으로
        List<String> lectureAfter = new ArrayList<>();
        lectureAfter.add("기초적인 수준이지만 웹, 프로그래밍, HTML, CSS에 대하여 이해할 수 있게된다.");
        lectureAfter.add("기초적인 수준이지만 웹, 프로그래밍, HTML, CSS에 대하여 이해할 수 있게된다.");
        lectureAfter.add("기초적인 수준이지만 웹, 프로그래밍, HTML, CSS에 대하여 이해할 수 있게된다.");

        items.add(new Item(5,lectureAfter));

        // 코스 커리큘럼 리스트 공간 배치하기
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

        items.add(new Item(6,curriculumList));

        // 결제 섹션
        Charge charge = new Charge();
        charge.setTitle("Lifetime Access");
        charge.setContent("본인이 원하시는 시간에, 본인에게 맞는 속도와 스피드로 페이스를 조정하여, 언제든지 다시 반복하여 들을 수 있는 온라인 수업입니다. 또한 6주 동안 100% 완주할 수 있는 챌린지 프로그램까지 추가로 제공됩니다.");
        charge.setPrice("60000");
        charge.setBackgroundColor("#f60000");
        charge.setTextColor("#ffffff");

        items.add(new Item(7,charge));

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

        items.add(new Item(8,titles));
        items.add(new Item(9));
        rvCourseDetail.setAdapter(new CourseDetailAdapter(items,CourseDetailActivity.this));

    }
}