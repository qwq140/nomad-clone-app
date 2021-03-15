package com.cos.nomadapp.model.faq;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class FaqItem implements Parcelable{

    private final String name;

    public FaqItem(String name) {
        this.name = name;
    }

    protected FaqItem(Parcel in) {
        name = in.readString();
    }

    public static final Creator<FaqItem> CREATOR = new Creator<FaqItem>() {
        @Override
        public FaqItem createFromParcel(Parcel in) {
            return new FaqItem(in);
        }

        @Override
        public FaqItem[] newArray(int size) {
            return new FaqItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
