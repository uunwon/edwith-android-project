package com.yunwoon.projectd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.ArrayList;

public class WriteReviewActivity extends AppCompatActivity {
    Button storeReviewButton, cancelReviewButton;
    RatingBar reviewRatingBar;
    EditText userEditText, reviewEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        reviewRatingBar = findViewById(R.id.reviewRatingBar);
        reviewEditText = findViewById(R.id.reviewEditText); userEditText = findViewById(R.id.userEditText);


        // 저장 버튼 클릭 -> 리뷰 저장
        storeReviewButton = findViewById(R.id.storeReviewButton);
        storeReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeMovieReview();
            }
        });

        // 취소 버튼 클릭 -> 이전 페이지로 되돌아가기
        cancelReviewButton = findViewById(R.id.cancelReviewButton);
        cancelReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // 리뷰 내용 저장하기
    public void storeMovieReview(){
        float rate = reviewRatingBar.getRating();
        String user = userEditText.getText().toString();
        String review = reviewEditText.getText().toString();

        Intent intent = getIntent();
        intent.putExtra("user", user);
        intent.putExtra("review", review);
        intent.putExtra("rate", rate);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
