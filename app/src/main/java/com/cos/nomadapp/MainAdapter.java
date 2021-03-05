package com.cos.nomadapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.model.courses.Course;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

    private static final String TAG = "UserAdapter";
    // 4번 컬렉션 생성
    private final List<Course> courses;

    public MainAdapter(List<Course> courses) {
        this.courses = courses;
    }

    // 5번 addItem, removeItem
    public void addItem(Course course){
        courses.add(course);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        courses.remove(position);
        notifyDataSetChanged();
    }

    // 7번 getView랑 똑같음
    // 차이점이 있다면 listView는 화면에 3개가 필요하면 최초 로딩시에 3개를 그려야하니까 getView가 3번 호출됨.
    // 그다음 스크로롤을 해서 2개가 추가되야 될때, 다시 getView를 호출함.
    // 하지만 recyclerView는 스크롤을 해서 2개가 추가되야 될때 onBindViewHolder를 호출함.
    // onCreateViewHolder는 해당 Activity 실행시에만 호출 됨.
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.course_item,parent, false);
        return new MainViewHolder(view); // view가 리스트뷰에 하나 그려짐
    }

    // 0, 1, 2, 3
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

    }

    // 6번 컬렉션 크기 알려주기 (화면에 몇개 그려야될지를 알아야 하기 때문)
    @Override
    public int getItemCount() {
        return courses.size();
    }

    // 1번 ViewHolder 만들기
    // ViewHolder란 하나의 View를 가지고 있다.
    public static class MainViewHolder extends RecyclerView.ViewHolder {


        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}