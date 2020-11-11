package com.yunwoon.projectb;

public class ListViewItem {
    private String userTextView;
    private String commentTextView;

    public ListViewItem(String userTextView, String commentTextView) {
        this.userTextView = userTextView;
        this.commentTextView = commentTextView;
    }

    public String getUserTextView() {
        return userTextView;
    }

    public String getCommentTextView() {
        return commentTextView;
    }
}
