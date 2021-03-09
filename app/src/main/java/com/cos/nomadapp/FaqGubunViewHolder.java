package com.cos.nomadapp;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class FaqGubunViewHolder extends GroupViewHolder {

    private TextView tvFaqTitle;

    public FaqGubunViewHolder(View itemView) {
        super(itemView);

        tvFaqTitle = itemView.findViewById(R.id.tv_faq_title);
    }

    public void bind(FaqGubun faqGubun){
        tvFaqTitle.setText(faqGubun.getTitle());
    }
}