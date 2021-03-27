package com.cos.nomadapp.ui.community;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.model.CMRespDto;
import com.cos.nomadapp.model.community.Category;
import com.cos.nomadapp.service.NomadApi;

import java.util.ArrayList;
import java.util.List;

import in.nashapp.androidsummernote.Summernote;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityWriteActivity extends AppCompatActivity {

    private ImageView ivBack;
    private AutoCompleteTextView autoCompleteTextView;
    private Summernote summernote;
    private static final String TAG = "CommunityWriteActivity";
    private List<Category> comCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_write);

        ivBack = findViewById(R.id.iv_back);

        ivBack.setOnClickListener(v -> {
            finish();
        });

        autoCompleteTextView = findViewById(R.id.autoCompleteText);

        List<String> category = new ArrayList<>();
        NomadApi nomadApi = NomadApi.retrofit.create(NomadApi.class);
        Call<CMRespDto<List<Category>>> call = nomadApi.comCategoryFindAll();
        call.enqueue(new Callback<CMRespDto<List<Category>>>() {
            @Override
            public void onResponse(Call<CMRespDto<List<Category>>> call, Response<CMRespDto<List<Category>>> response) {

            }
            @Override
            public void onFailure(Call<CMRespDto<List<Category>>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.community_category_item,category);

        // 디폴트 값
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(),false);

        autoCompleteTextView.setAdapter(arrayAdapter);

        summernote = (Summernote) findViewById(R.id.summernote);
        summernote.setRequestCodeforFilepicker(5);//Any Number which is not being used by other OnResultActivity



    }


}