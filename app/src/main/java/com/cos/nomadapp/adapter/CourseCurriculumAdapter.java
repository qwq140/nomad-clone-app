package com.cos.nomadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.model.common.Item;
import com.cos.nomadapp.model.courses.Curriculum;

import java.util.ArrayList;
import java.util.List;

public class CourseCurriculumAdapter extends RecyclerView.Adapter<CourseCurriculumAdapter.MyViewHolder>{

    private final List<Curriculum> curriculumList;
    private Context context;

    public CourseCurriculumAdapter(List<Curriculum> curriculumList, Context context) {

        this.curriculumList = curriculumList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.curriculum_space_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(0,curriculumList.get(position).getChapter()));
        for (int i=0; i<curriculumList.get(position).getContents().size() ; i++){
            String content = curriculumList.get(position).getContents().get(i);
            items.add(new Item(1,content));
        }

        LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rvCurriculumSpace.setLayoutManager(manager);
        holder.rvCurriculumSpace.setAdapter(new CurriculumSpaceAdapter(items));

    }


    @Override
    public int getItemCount() {
        return curriculumList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView rvCurriculumSpace;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rvCurriculumSpace = itemView.findViewById(R.id.rv_curriculum_space);
        }

    }
}