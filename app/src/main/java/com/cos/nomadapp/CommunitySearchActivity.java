package com.cos.nomadapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.cos.nomadapp.adapter.CommunityAdapter;
import com.cos.nomadapp.model.community.Community;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class CommunitySearchActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvToolbarTitle;
    private TextInputEditText etSearch;
    private RecyclerView rvSearchList;
    private List<Community> communities;
    private CommunityAdapter communityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_search);

        ivBack = findViewById(R.id.iv_back);

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("검색");

        ivBack.setOnClickListener(v -> {
            finish();
        });

        rvSearchList = findViewById(R.id.rv_search_list);

        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        communities = new ArrayList<>();


        Community community = new Community(0L,"자바스크립트", "내용","bla-bla");
        communities.add(community);

        community = new Community(1L,"파이썬", "내용","bla-bla");
        communities.add(community);

        community = new Community(2L,"리액트", "내용","bla-bla");
        communities.add(community);


        rvSearchList.setLayoutManager(manager);

        communityAdapter = new CommunityAdapter(communities);

        rvSearchList.setAdapter(communityAdapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString());
            }
        });
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<Community> filterItems = new ArrayList<>();


        List<String> names = new ArrayList<>();
        for (int i=0; i<communities.size();i++){
            names.add(communities.get(i).getTitle());
        }
        //looping through existing elements
        for (String s : names) {
            //if the existing elements contains the search input
            if (s.toLowerCase().contains(text.toLowerCase())) {
                //adding the element to filtered list
                for (int i=0; i<communities.size();i++){
                    if (communities.get(i).getTitle().equals(s)){
                        filterItems.add(communities.get(i));
                    }
                }
            }
        }

        //calling a method of the adapter class and passing the filtered list
        communityAdapter.filterList(filterItems);
    }
}