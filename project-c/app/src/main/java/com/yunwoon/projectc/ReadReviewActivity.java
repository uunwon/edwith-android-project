package com.yunwoon.projectc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ReadReviewActivity extends AppCompatActivity {
    ListView reviewListView;
    ListViewAdapter listViewAdapter;
    ArrayList<ListViewItem> listViewItems;

    TextView writeTextView;

    String review, user;
    float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_review);

        reviewListView = findViewById(R.id.reviewListView);

        final Intent intent = getIntent();
        listViewItems = intent.getParcelableArrayListExtra("review");

        listViewAdapter = new ListViewAdapter();
        listViewAdapter.arrayList = listViewItems;

        reviewListView.setAdapter(listViewAdapter);
        intent.putExtra("review2", listViewItems);
        setResult(Activity.RESULT_OK, intent);

        writeTextView = findViewById(R.id.writeTextView);
        writeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWriteReviewPage();
            }
        });
    }

    public void showWriteReviewPage(){
        Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
        startActivityForResult(intent, 103);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 한줄평 페이지 데이터 리스트뷰에 셋업
        if(requestCode == 103) {
            if(resultCode == RESULT_OK) {
                user = data.getStringExtra("user");
                review = data.getStringExtra("review");
                rate = data.getFloatExtra("rate", (float)3.5);

                listViewAdapter.addItem(new ListViewItem(user, review, rate));
                reviewListView.setAdapter(listViewAdapter);
            }
        }
    }
}
