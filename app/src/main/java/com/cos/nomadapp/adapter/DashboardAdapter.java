package com.cos.nomadapp.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.cos.nomadapp.FooterViewHolder;
import com.cos.nomadapp.R;
import com.cos.nomadapp.model.common.Item;
import com.cos.nomadapp.model.user.UserDashboardFirstSection;
import com.cos.nomadapp.model.user.UserDashboardSecondSection;
import com.cos.nomadapp.model.user.UserDashboardThirdSection;
import com.cos.nomadapp.model.user.UserDashboardFourthSection;
import com.google.android.material.tabs.TabLayout;


import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items;
    private static final String TAG = "DashboardAdapter :";
    private Context context;

    public DashboardAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new UserDashboardSectionFirstViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.footer,
                            parent,
                            false
                    )
            );
        } else if (viewType == 1) {
            return new UserDashboardSectionSecondViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.user_dashboard_section2,
                            parent,
                            false
                    )
            );
        } else if(viewType == 2){
            return new UserDashboardSectionThirdViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.user_dashboard_section3,
                            parent,
                            false
                    )
            );
        }else if(viewType == 3){
            return new UserDashboardSectionFourthViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.user_dashboard_section4,
                            parent,
                            false
                    )
            );
        } else{
            return new FooterViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.footer,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        if(getItemViewType(position) == 0){
//            UserDashboardFirstSection userDashboardFirstSection = (UserDashboardFirstSection) items.get(position).getObject();
//            ((UserDashboardSectionFirstViewHolder) holder).setUserDashboardItem1(userDashboardFirstSection);
//        }else if(getItemViewType(position) == 1){
//            UserDashboardSecondSection userDashboardSecondSection = (UserDashboardSecondSection)items.get(position).getObject();
//            ((UserDashboardSectionSecondViewHolder) holder).setUserDashboardItem2(userDashboardSecondSection);
//        }else if(getItemViewType(position) == 2){
//            UserDashboardThirdSection userDashboardThirdSection = (UserDashboardThirdSection)items.get(position).getObject();
//            ((UserDashboardSectionThirdViewHolder) holder).setUserDashboardItem3(userDashboardThirdSection);
//        }else if (getItemViewType(position) == 3){
//            UserDashboardFourthSection userDashboardFourthSection = (UserDashboardFourthSection)items.get(position).getObject();
//            ((UserDashboardSectionFourthViewHolder) holder).setUserDashboardItem4(userDashboardFourthSection);
//        }else{
//            Log.d(TAG, "onBindViewHolder: footer");
//        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public static class UserDashboardSectionFirstViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDashUsername, tvDashName;

        public UserDashboardSectionFirstViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDashName=itemView.findViewById(R.id.tv_dash_name);
            tvDashUsername=itemView.findViewById(R.id.tv_dash_username);
        }

        void setUserDashboardItem1(UserDashboardFirstSection userDashboardFirstSection){
            tvDashName.setText(userDashboardFirstSection.getName());
            tvDashUsername.setText(userDashboardFirstSection.getUsername());
        }
    }





    public static class UserDashboardSectionSecondViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDashPlain;

        public UserDashboardSectionSecondViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDashPlain = itemView.findViewById(R.id.tv_dash_plain);
        }
        void setUserDashboardItem2(UserDashboardSecondSection userDashboardSecondSection){
            tvDashPlain.setText(userDashboardSecondSection.getPlain());
        }
    }


    public static class UserDashboardSectionThirdViewHolder extends RecyclerView.ViewHolder{
        private ImageButton ib1,ib2,ib3,ib4,ib5,ib6,ib7,ib8,ib9,ib10,ib11,ib12,ib13,ib14,ib15;
        public UserDashboardSectionThirdViewHolder(@NonNull View itemView) {
            super(itemView);
            ib1 = itemView.findViewById(R.id.imageButton2);
            ib2 = itemView.findViewById(R.id.imageButton2);
            ib3 = itemView.findViewById(R.id.imageButton2);
            ib4 = itemView.findViewById(R.id.imageButton2);
            ib5 = itemView.findViewById(R.id.imageButton2);
            ib6 = itemView.findViewById(R.id.imageButton2);
            ib7 = itemView.findViewById(R.id.imageButton2);
            ib8 = itemView.findViewById(R.id.imageButton2);
            ib9 = itemView.findViewById(R.id.imageButton2);
            ib10 = itemView.findViewById(R.id.imageButton2);
            ib11 = itemView.findViewById(R.id.imageButton2);
            ib12 = itemView.findViewById(R.id.imageButton2);
            ib13 = itemView.findViewById(R.id.imageButton2);
            ib14 = itemView.findViewById(R.id.imageButton2);
            ib15 = itemView.findViewById(R.id.imageButton2);
        }

        void setUserDashboardItem3(UserDashboardThirdSection userDashboardThirdSection){
            Log.d(TAG, "setUserDashboardItem3: "+ userDashboardThirdSection.toString());
        }
    }

    public static class UserDashboardSectionFourthViewHolder extends RecyclerView.ViewHolder{
        private TabLayout myTabs;
        private ViewPager vpMyContainer;

        public UserDashboardSectionFourthViewHolder(@NonNull View itemView) {
            super(itemView);
            myTabs = itemView.findViewById(R.id.mytabs);
            vpMyContainer = itemView.findViewById((R.id.vp_mycontainer));
        }
        void setUserDashboardItem4(UserDashboardFourthSection userDashboardFourthSection){
            Log.d(TAG, "setUserDashboardItem4: "+ userDashboardFourthSection.toString());
        }
    }
}
