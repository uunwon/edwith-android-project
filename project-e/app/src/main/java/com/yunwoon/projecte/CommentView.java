package com.yunwoon.projecte;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yunwoon.projecte.R;

import androidx.annotation.Nullable;

public class CommentView extends LinearLayout {
    TextView userTextView, commentTextView;
    RatingBar ratingBar;

    public CommentView(Context context) {
        super(context);
        init(context);
    }

    public CommentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listitem, this, true); // XML 파일을 뷰로 생성해줌

        userTextView = findViewById(R.id.userTextView);
        commentTextView = findViewById(R.id.commentTextView);
        ratingBar = findViewById(R.id.ratingBar);
    }

    public void setUser(String user) {
        userTextView.setText(user);
    }

    public void setComment(String comment) {
        commentTextView.setText(comment);
    }

    public void setRatingBar(float rate){
        ratingBar.setRating(rate);
    }
}
