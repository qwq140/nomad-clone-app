package com.cos.nomadapp;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class FaqGubun extends ExpandableGroup<FaqItem> {

    public FaqGubun(String title, List<FaqItem> items) {
        super(title, items);
    }
}
