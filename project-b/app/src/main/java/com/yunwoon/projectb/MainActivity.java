package com.yunwoon.projectb;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView thumbUpImageView, thumbDownImageView;
    TextView thumbUpTextView, thumbDownTextView, writeTextView;
    Button readButton;
    ListView reviewListView;

    int likeCount = 15, hateCount = 1;
    boolean thumbUpState = false, thumbDownState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumbUpImageView = findViewById(R.id.thumbUpImageView); thumbDownImageView = findViewById(R.id.thumbDownImageView);
        thumbUpTextView = findViewById(R.id.thumbUpTextView); thumbDownTextView = findViewById(R.id.thumbDownTextView); // 싫어요 버튼

        //좋아요 버튼 클릭 시
        thumbUpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thumbDownState){ // 싫어요 버튼이 눌려있을 시에는
                    likeCount += 1; hateCount -= 1;
                    thumbDownState = false;
                    thumbUpTextView.setText(String.valueOf(likeCount));
                    thumbDownTextView.setText(String.valueOf(hateCount));
                    thumbUpImageView.setImageResource(R.drawable.ic_thumb_up_selected);
                    thumbDownImageView.setImageResource(R.drawable.ic_thumb_down);
                } else { // 싫어요 버튼이 안눌려있을 시에는
                    if(thumbUpState){
                        likeCount -= 1;
                        thumbUpTextView.setText(String.valueOf(likeCount));
                        thumbUpImageView.setImageResource(R.drawable.ic_thumb_up);
                    } else{
                        likeCount += 1;
                        thumbUpTextView.setText(String.valueOf(likeCount));
                        thumbUpImageView.setImageResource(R.drawable.ic_thumb_up_selected);
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
                    hateCount += 1; likeCount -= 1;
                    thumbUpState = false;
                    thumbUpTextView.setText(String.valueOf(likeCount));
                    thumbDownTextView.setText(String.valueOf(hateCount));
                    thumbDownImageView.setImageResource(R.drawable.ic_thumb_down_selected);
                    thumbUpImageView.setImageResource(R.drawable.ic_thumb_up);
                } else { // 좋아요 버튼이 안눌려있을 시에는
                    if(thumbDownState){
                        hateCount -= 1;
                        thumbDownTextView.setText(String.valueOf(hateCount));
                        thumbDownImageView.setImageResource(R.drawable.ic_thumb_down);
                    } else {
                        hateCount += 1;
                        thumbDownTextView.setText(String.valueOf(hateCount));
                        thumbDownImageView.setImageResource(R.drawable.ic_thumb_down_selected);
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
        adapter.addItem(new ListViewItem("sozo3**","행복하게 봤네요. 간만에 흥미진진했습니다."));
        reviewListView.setAdapter(adapter);
    }

    // 리뷰 리스트뷰 어댑터
    class ListViewAdapter extends BaseAdapter{
        ArrayList<ListViewItem> arrayList = new ArrayList<>();

        @Override
        public int getCount() {
            return arrayList.size();
        }

        public void addItem(ListViewItem item){
            arrayList.add(item);
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListItemView view = new ListItemView(getApplicationContext());

            ListViewItem item = arrayList.get(position);
            view.setUser(item.getUserTextView());
            view.setComment(item.getCommentTextView());

            return view;
        }
    }
}