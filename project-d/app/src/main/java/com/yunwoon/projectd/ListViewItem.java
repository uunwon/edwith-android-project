package com.yunwoon.projectd;

import android.os.Parcel;
import android.os.Parcelable;

public class ListViewItem implements Parcelable {
    private String userTextView;
    private String commentTextView;
    private float ratingBar;

    public ListViewItem(String userTextView, String commentTextView, float ratingBar) {
        this.userTextView = userTextView;
        this.commentTextView = commentTextView;
        this.ratingBar = ratingBar;
    }

    protected ListViewItem(Parcel in) {
        userTextView = in.readString();
        commentTextView = in.readString();
        ratingBar = in.readFloat();
    }

    public static final Creator<ListViewItem> CREATOR = new Creator<ListViewItem>() {
        @Override
        public ListViewItem createFromParcel(Parcel in) {
            return new ListViewItem(in);
        }

        @Override
        public ListViewItem[] newArray(int size) {
            return new ListViewItem[size];
        }
    };

    public String getUserTextView() {
        return userTextView;
    }

    public String getCommentTextView() {
        return commentTextView;
    }

    public float getRatingBar(){
        return ratingBar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userTextView);
        parcel.writeString(commentTextView);
        parcel.writeFloat(ratingBar);
    }
}
