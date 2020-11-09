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

    public void setUserTextView(String userTextView) {
        this.userTextView = userTextView;
    }

    public String getCommentTextView() {
        return commentTextView;
    }

    public void setCommentTextView(String commentTextView) {
        this.commentTextView = commentTextView;
    }
}
