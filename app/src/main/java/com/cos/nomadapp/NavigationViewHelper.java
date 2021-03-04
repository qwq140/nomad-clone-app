package com.cos.nomadapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;

public class NavigationViewHelper {

    private static final String TAG = "NavigationViewHelper";

    public static void enable(Context context, NavigationView view){
        view.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id == R.id.item1){
                Log.d(TAG, "onCreate: 메뉴1 클릭됨");
            }else if(id == R.id.item2){
                Log.d(TAG, "onCreate: 메뉴2 클릭됨");
                Intent intent = new Intent(context, CoursesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }else if(id == R.id.item3){
                Log.d(TAG, "onCreate: 메뉴3 클릭됨");
            }else if(id == R.id.item4){
                Log.d(TAG, "onCreate: 메뉴4 클릭됨");
            }else if(id == R.id.item5){
                Log.d(TAG, "onCreate: 메뉴5 클릭됨");
            }else if(id == R.id.item6){
                Log.d(TAG, "onCreate: 메뉴6 클릭됨");
            }

            return false;
        });
    }

}
