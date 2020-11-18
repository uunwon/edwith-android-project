package com.yunwoon.projectc;

import android.widget.RatingBar;

public class ListViewItem {
    private String userTextView;
    private String commentTextView;
    private float ratingBar;

    public ListViewItem(String userTextView, String commentTextView, float ratingBar) {
        this.userTextView = userTextView;
        this.commentTextView = commentTextView;
        this.ratingBar = ratingBar;
    }

    public String getUserTextView() {
        return userTextView;
    }

    public String getCommentTextView() {
        return commentTextView;
    }

    public float getRatingBar(){
        return ratingBar;
    }
}
