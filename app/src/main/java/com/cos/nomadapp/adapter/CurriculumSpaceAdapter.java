package com.cos.nomadapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.nomadapp.R;
import com.cos.nomadapp.model.common.Item;

import java.util.List;

public class CurriculumSpaceAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> items;

    public CurriculumSpaceAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //0 : CourseTitle 1 : Challenge
        if(viewType == 0){
            return new ChapterViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.curriculum_chapter_item,
                            parent,
                            false
                    )
            );
        }else{
            return new ContentViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.curriculum_content_item,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==0){
            String chapter = (String) items.get(position).getObject();
            ((ChapterViewHolder) holder).setItem(chapter);
        }else if(getItemViewType(position)==1){
            String content = (String) items.get(position).getObject();
            ((ContentViewHolder) holder).setItem(content);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder{

        private TextView tvChapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
        }

        void setItem(String chapter){
            tvChapter.setText(chapter);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCurriculumContent;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCurriculumContent = itemView.findViewById(R.id.tv_curriculum_content);
        }

        void setItem(String content){
           tvCurriculumContent.setText(content);
        }
    }

}