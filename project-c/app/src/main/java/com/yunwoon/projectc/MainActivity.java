package com.yunwoon.projectc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView thumbUpImageView, thumbDownImageView;
    TextView thumbUpTextView, thumbDownTextView, writeTextView;
    Button readButton;
    ListView reviewListView;

    String review, user;
    float rate;
    ListViewAdapter adapter;

    int thumbUpCount = 15, thumbDownCount = 1;
    boolean thumbUpState = false, thumbDownState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbUpImageView = findViewById(R.id.thumbUpImageView); thumbDownImageView = findViewById(R.id.thumbDownImageView);
        thumbUpTextView = findViewById(R.id.thumbUpTextView); thumbDownTextView = findViewById(R.id.thumbDownTextView);

        //좋아요 버튼 클릭 시
        thumbUpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thumbDownState){ // 싫어요 버튼이 눌려있을 시에는
                    incrThumbUpCount();
                    thumbDownState = false;
                    decrThumbDownCount();
                } else { // 싫어요 버튼이 안눌려있을 시에는
                    if(thumbUpState){
                        decrThumbUpCount();
                    } else{
                        incrThumbUpCount();
                    }
                }
                thumbUpState = !thumbUpState;
            }
        });

        //싫어요 버튼 클릭 시
        thumbDownImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thumbUpState) { // 좋아요 버튼이 눌려있을 시에는
                    incrThumbDownCount();
                    thumbUpState = false;
                    decrThumbUpCount();
                } else { // 좋아요 버튼이 안눌려있을 시에는
                    if(thumbDownState){
                        decrThumbDownCount();
                    } else {
                        incrThumbDownCount();
                    }
                }
                thumbDownState = !thumbDownState;
            }
        });

        // 한줄평 작성 페이지 이동
        writeTextView = findViewById(R.id.writeTextView);
        writeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWriteReviewPage();
            }
        });

        // 한줄평 모두 보기 페이지 이동
        readButton = findViewById(R.id.readButton);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReadReviewPage();
            }
        });

        reviewListView = findViewById(R.id.reviewListView);

        adapter = new ListViewAdapter(); // 리스트뷰 세팅
        adapter.addItem(new ListViewItem("kym71**","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", (float)3.5));
        adapter.addItem(new ListViewItem("sozo3**","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", (float)5.0));
        reviewListView.setAdapter(adapter);
    }

    // 좋아요 선택
    private void incrThumbUpCount(){
        thumbUpCount += 1;
        thumbUpTextView.setText(String.valueOf(thumbUpCount));
        thumbUpImageView.setImageResource(R.drawable.ic_thumb_up_selected);
    }

    // 좋아요 선택 해제
    private void decrThumbUpCount(){
        thumbUpCount -= 1;
        thumbUpTextView.setText(String.valueOf(thumbUpCount));
        thumbUpImageView.setImageResource(R.drawable.ic_thumb_up);
    }

    // 싫어요 선택
    private void incrThumbDownCount(){
        thumbDownCount += 1;
        thumbDownTextView.setText(String.valueOf(thumbDownCount));
        thumbDownImageView.setImageResource(R.drawable.ic_thumb_down_selected);
    }

    //싫어요 선택 해제
    private void decrThumbDownCount(){
        thumbDownCount -= 1;
        thumbDownTextView.setText(String.valueOf(thumbDownCount));
        thumbDownImageView.setImageResource(R.drawable.ic_thumb_down);
    }


    // 한줄평 작성 페이지 이동
    public void showWriteReviewPage(){
        Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 한줄평 페이지 데이터 리스트뷰에 셋업
        if(requestCode == 101) {
            if(resultCode == RESULT_OK) {
                user = data.getStringExtra("user");
                review = data.getStringExtra("review");
                rate = data.getFloatExtra("rate", (float)3.5);

                adapter.addItem(new ListViewItem(user, review, rate));
                reviewListView.setAdapter(adapter);
            }
        }
    }

    // 한줄평 모두 보기 페이지 이동
    public void showReadReviewPage(){
        Intent intent = new Intent(getApplicationContext(), ReadReviewActivity.class);
        startActivityForResult(intent, 102);
    }
}