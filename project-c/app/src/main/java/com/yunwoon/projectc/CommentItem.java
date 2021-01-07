package com.yunwoon.projectc;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentItem implements Parcelable {
    private String userTextView;
    private String commentTextView;
    private float ratingBar;

    public CommentItem(String userTextView, String commentTextView, float ratingBar) {
        this.userTextView = userTextView;
        this.commentTextView = commentTextView;
        this.ratingBar = ratingBar;
    }

    protected CommentItem(Parcel in) {
        userTextView = in.readString();
        commentTextView = in.readString();
        ratingBar = in.readFloat();
    }

    public static final Creator<CommentItem> CREATOR = new Creator<CommentItem>() {
        @Override
        public CommentItem createFromParcel(Parcel in) {
            return new CommentItem(in);
        }

        @Override
        public CommentItem[] newArray(int size) {
            return new CommentItem[size];
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
