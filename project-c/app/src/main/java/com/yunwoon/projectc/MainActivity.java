package com.yunwoon.projectc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView thumbUpImageView, thumbDownImageView;
    TextView thumbUpTextView, thumbDownTextView, writeTextView;
    Button readButton;
    ListView reviewListView;

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

        writeTextView = findViewById(R.id.writeTextView); // 작성하기 Toast Message
        writeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "'작성하기'를 클릭하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        readButton = findViewById(R.id.readButton); // 모두보기 Toast Message
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "'모두보기'를 클릭하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        reviewListView = findViewById(R.id.reviewListView);

        ListViewAdapter adapter = new ListViewAdapter(); // 리스트뷰 세팅
        adapter.addItem(new ListViewItem("kym71**","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
        adapter.addItem(new ListViewItem("sozo3**","적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요."));
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
}