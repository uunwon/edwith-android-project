package com.yunwoon.projectd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadReviewActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_SETUP = 103;
    ListView reviewListView;
    ListViewAdapter listViewAdapter = new ListViewAdapter();
    ArrayList<CommentItem> commentItems;

    TextView writeTextView;

    Intent intent;
    String review, user;
    float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_review);

        reviewListView = findViewById(R.id.reviewListView);

        intent = getIntent();
        commentItems = intent.getParcelableArrayListExtra("review");

        listViewAdapter.arrayList = commentItems;
        reviewListView.setAdapter(listViewAdapter);

        writeTextView = findViewById(R.id.writeTextView);
        writeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWriteReviewPage();
            }
        });
    }

    // 뒤로가기 버튼 클릭 시 업로드된 리뷰 아이템 전달
    @Override
    public void onBackPressed() {
        intent.putExtra("review2", commentItems);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }

    public void showWriteReviewPage(){
        Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SETUP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 한줄평 페이지 데이터 리스트뷰에 셋업
        if(requestCode == REQUEST_CODE_SETUP) {
            if(resultCode == RESULT_OK) {
                user = data.getStringExtra("user");
                review = data.getStringExtra("review");
                rate = data.getFloatExtra("rate", 3.5f);

                listViewAdapter.addItem(new CommentItem(user, review, rate));
                reviewListView.setAdapter(listViewAdapter);
            }
        }
    }
}