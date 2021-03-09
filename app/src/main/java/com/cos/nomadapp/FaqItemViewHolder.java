package com.cos.nomadapp;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

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
