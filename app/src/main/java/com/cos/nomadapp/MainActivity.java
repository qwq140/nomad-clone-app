package com.cos.nomadapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.cos.nomadapp.adapter.MainAdapter;
import com.cos.nomadapp.model.Item;
import com.cos.nomadapp.model.challenge.Challenge;
import com.cos.nomadapp.model.courses.Course;
import com.cos.nomadapp.model.common.MainTitle;
import com.google.android.material.navigation.NavigationView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarNomad;
    private ImageView ivMenu;
    private DrawerLayout drawer;
    private NavigationView nv;
    private RecyclerView rvMainList;
    private RoundedImageView rivUser;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarNomad = findViewById(R.id.toolbar_nomad);
        setSupportActionBar(toolbarNomad);

        ivMenu = findViewById(R.id.iv_back);
        drawer = findViewById(R.id.drawer);

        ivMenu.setOnClickListener(v -> {
            drawer.openDrawer(Gravity.LEFT);
        });

        //roundedImageView 이벤트
        rivUser = (RoundedImageView)findViewById(R.id.riv_user);
        rivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu p =new PopupMenu(
                        getApplicationContext(),//화면제어권자
                        v);             //팝업을 띄울 기준이될 위젯
                getMenuInflater().inflate(R.menu.user_menu,p.getMenu());
                //이벤트 처리
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Dashboard")){
                            Toast.makeText(getApplicationContext(), item.getTitle(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(v.getContext(), UserDashboardActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            v.getContext().startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), item.getTitle(),Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
                p.show();
            }
        });
        //roundedImageView End

        //navigationView
        nv = findViewById(R.id.nv);
        NavigationViewHelper.enable(MainActivity.this,nv);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvMainList = findViewById(R.id.rv_main_list);

        List<Item> items = new ArrayList<>();

        // type 0 : MainTitleItem 1 : CourseItem 2: main_link_item
        List<MainTitle> mainTitles = new ArrayList<>();
        mainTitles.add(new MainTitle("Clone Startups.\nLearn to code.","코딩은 진짜를 만들어보는거야!\n실제 구현되어 있는 서비스를 한땀 한땀\n따라 만들면서 코딩을 배우세요."));
        mainTitles.add(new MainTitle("Challenges","강의만으로는 부족해! 멱살잡고 캐리하는\n챌린지에 무료로 참여하세요!"));

        items.add(new Item(0,mainTitles.get(0)));

        for (int i = 0 ; i<5;i++){
            Course course = new Course("[풀스택] 유튜브 클론코딩","유튜브 백엔드 + 프런트엔드 + 배포",R.drawable.course_youtube);
            items.add(new Item(1,course));
        }

        items.add(new Item(2,"See all courses →"));

        items.add(new Item(0,mainTitles.get(1)));

        for(Long i = 0L ; i<3L; i++){
            Challenge challenge = new Challenge(i,"바닐라JS 2주 완성반",2,935);
            items.add(new Item(3,challenge));
        }

        items.add(new Item(2,"See all challenges →"));

        //footer
        items.add(new Item(4));
        rvMainList.setLayoutManager(manager);

        rvMainList.setAdapter(new MainAdapter(items));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}