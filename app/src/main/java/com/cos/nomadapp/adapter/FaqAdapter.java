package com.cos.nomadapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cos.nomadapp.FaqGubun;
import com.cos.nomadapp.FaqGubunViewHolder;
import com.cos.nomadapp.FaqItem;
import com.cos.nomadapp.FaqItemViewHolder;
import com.cos.nomadapp.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class FaqAdapter extends ExpandableRecyclerViewAdapter<FaqGubunViewHolder, FaqItemViewHolder> {
    public FaqAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public FaqGubunViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item,parent,false);
        return new FaqGubunViewHolder(view);
    }

    @Override
    public FaqItemViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_expand_item,parent,false);
        return new FaqItemViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(FaqItemViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final FaqItem faqItem = (FaqItem) group.getItems().get(childIndex);
        holder.bind(faqItem);
    }

    @Override
    public void onBindGroupViewHolder(FaqGubunViewHolder holder, int flatPosition, ExpandableGroup group) {
        final FaqGubun faqGubun = (FaqGubun) group;
        holder.bind(faqGubun);
    }
}
