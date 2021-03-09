package com.cos.nomadapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class NavigationViewHelper {

    private static final String TAG = "NavigationViewHelper";

    public static void enable(Context context, NavigationView view) {
        view.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            DrawerLayout drawer = (DrawerLayout)((Activity) context).findViewById(R.id.drawer);

            if (id == R.id.item1) {
                Log.d(TAG, "enable: Join 클릭");
                Intent intent = new Intent(context, JoinActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            } else if (id == R.id.item2) {
                Log.d(TAG, "enable: Login 클릭");
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            } else if (id == R.id.item3) {
                Log.d(TAG, "enable: Courses 클릭");
                Intent intent = new Intent(context, CoursesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            } else if (id == R.id.item4) {
                Log.d(TAG, "enable: Challenges 클릭");
                Intent intent = new Intent(context, ChallengesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            } else if (id == R.id.item5) {
                Log.d(TAG, "enable: Community 클릭");
                Intent intent = new Intent(context, CommunityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            } else if (id == R.id.item6) {
                Log.d(TAG, "enable: FAQ 클릭");
                Intent intent = new Intent(context, FaqActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            } else if (id == R.id.item7) {
                Log.d(TAG, "onCreate: Road Map 클릭");
            }
            drawer.closeDrawer(Gravity.LEFT);
            return false;
        });
    }

}
