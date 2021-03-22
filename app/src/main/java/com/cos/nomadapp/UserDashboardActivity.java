package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.cos.nomadapp.adapter.DashboardAdapter;
import com.cos.nomadapp.model.common.Item;
import com.cos.nomadapp.model.user.UserDashboardSecondSection;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class UserDashboardActivity extends AppCompatActivity {
    private static final String TAG = "UserDashboardActivity";
    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private RoundedImageView rivUser;
    private RecyclerView rvDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("DashBoard");

        ivBack.setOnClickListener(v -> {
            finish();
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

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDashboard = findViewById(R.id.rv_dashboard);
        rvDashboard.setLayoutManager(manager);

        List<Item> items = new ArrayList<>();

        UserDashboardSecondSection userDashboardSecondSection = new UserDashboardSecondSection("Join more courses and complete challenges to unlock the next level!");
        items.add(new Item(1, userDashboardSecondSection));
        Log.d(TAG, "onCreate: "+ userDashboardSecondSection);

        rvDashboard.setAdapter(new DashboardAdapter(items,UserDashboardActivity.this));

    }
}