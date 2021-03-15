package com.cos.nomadapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cos.nomadapp.model.faq.FaqGubun;
import com.cos.nomadapp.model.faq.FaqItem;
import com.cos.nomadapp.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class FaqAdapter extends ExpandableRecyclerViewAdapter<FaqAdapter.FaqGubunViewHolder, FaqAdapter.FaqItemViewHolder> {
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

    public class FaqGubunViewHolder extends GroupViewHolder {

        private static final String TAG = "FaqGubunViewHolder";
        private TextView tvFaqTitle;

        public FaqGubunViewHolder(View itemView) {
            super(itemView);

            tvFaqTitle = itemView.findViewById(R.id.tv_faq_title);
        }

        public void bind(FaqGubun faqGubun){

            tvFaqTitle.setText(faqGubun.getTitle());
            Log.d(TAG, "bind: "+faqGubun.getTitle());

        }
    }

    public class FaqItemViewHolder extends ChildViewHolder {

        private TextView tvFaqItem;

        public FaqItemViewHolder(View itemView) {
            super(itemView);
            tvFaqItem = itemView.findViewById(R.id.tv_faq_expand_item);
        }

        public void bind(FaqItem faqItem){
            tvFaqItem.setText(faqItem.getName());
        }
    }
}
