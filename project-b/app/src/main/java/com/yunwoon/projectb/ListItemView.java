package com.yunwoon.projectb;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ListItemView extends LinearLayout {
    TextView userTextView, commentTextView;

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true);

        userTextView = findViewById(R.id.userTextView);
        commentTextView = findViewById(R.id.commentTextView);
    }

    public void setUser(String user) {
        userTextView.setText(user);
    }

    public void setComment(String comment) {
        commentTextView.setText(comment);
    }
}
