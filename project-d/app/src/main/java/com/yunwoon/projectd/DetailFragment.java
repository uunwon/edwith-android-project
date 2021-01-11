package com.yunwoon.projectd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    private ImageView thumbUpImageView, thumbDownImageView;
    private TextView thumbUpTextView, thumbDownTextView, writeTextView;
    private Button readButton;
    private ListView reviewListView;

    private String review, user;
    private float rate;
    private ListViewAdapter adapter = new ListViewAdapter(); // 리스트뷰 세팅
    private  ArrayList<CommentItem> listViewItemArrayList = new ArrayList<>();

    private int thumbUpCount = 15, thumbDownCount = 1;
    private boolean thumbUpState = false, thumbDownState = false;

    MainActivity activity;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        context = container.getContext();

        thumbUpImageView = root.findViewById(R.id.thumbUpImageView);
        thumbDownImageView = root.findViewById(R.id.thumbDownImageView);
        thumbUpTextView = root.findViewById(R.id.thumbUpTextView);
        thumbDownTextView = root.findViewById(R.id.thumbDownTextView);

        reviewListView = root.findViewById(R.id.reviewListView);

        //좋아요 버튼 클릭 시
        thumbUpImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thumbDownState) { // 싫어요 버튼이 눌려있을 시에는
                    incrThumbUpCount();
                    thumbDownState = false;
                    decrThumbDownCount();
                } else { // 싫어요 버튼이 안눌려있을 시에는
                    if (thumbUpState) {
                        decrThumbUpCount();
                    } else {
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
                if (thumbUpState) { // 좋아요 버튼이 눌려있을 시에는
                    incrThumbDownCount();
                    thumbUpState = false;
                    decrThumbUpCount();
                } else { // 좋아요 버튼이 안눌려있을 시에는
                    if (thumbDownState) {
                        decrThumbDownCount();
                    } else {
                        incrThumbDownCount();
                    }
                }
                thumbDownState = !thumbDownState;
            }
        });

        // 한줄평 작성 페이지 이동
        writeTextView = root.findViewById(R.id.writeTextView);
        writeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.showWriteReviewPage();
            }
        });

        // 한줄평 모두 보기 페이지 이동
        readButton = root.findViewById(R.id.readButton);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listViewItemArrayList = adapter.arrayList;
                activity.showReadReviewPage(listViewItemArrayList);
            }
        });

        adapter.addItem(new CommentItem("kym71**", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", 4.5f));
        adapter.addItem(new CommentItem("angel**", "웃긴 내용보다는 좀 더 진지한 영화.", 4.0f));
        adapter.addItem(new CommentItem("beaut**", "연기가 부족한 느낌이 드는 배우도 있다. 그래도 전체적으로는 재밌다.", 5.0f));
        adapter.addItem(new CommentItem("sales**", "생각할 거리를 주는 영화, 즐거웠다.", 3.7f));
        adapter.addItem(new CommentItem("major**", "제목을 잘지었네요.", 4.2f));

        reviewListView.setAdapter(adapter);
        return root;
    }

    // 좋아요 선택
    private void incrThumbUpCount() {
        thumbUpCount += 1;
        thumbUpTextView.setText(String.valueOf(thumbUpCount));
        thumbUpImageView.setImageResource(R.drawable.ic_thumb_up_selected);
    }

    // 좋아요 선택 해제
    private void decrThumbUpCount() {
        thumbUpCount -= 1;
        thumbUpTextView.setText(String.valueOf(thumbUpCount));
        thumbUpImageView.setImageResource(R.drawable.ic_thumb_up);
    }

    // 싫어요 선택
    private void incrThumbDownCount() {
        thumbDownCount += 1;
        thumbDownTextView.setText(String.valueOf(thumbDownCount));
        thumbDownImageView.setImageResource(R.drawable.ic_thumb_down_selected);
    }

    //싫어요 선택 해제
    private void decrThumbDownCount() {
        thumbDownCount -= 1;
        thumbDownTextView.setText(String.valueOf(thumbDownCount));
        thumbDownImageView.setImageResource(R.drawable.ic_thumb_down);
    }

    // 작성하기에서 한줄평 페이지 데이터 리스트뷰에 셋업
    public void setDetailPage01(Intent data) {
        user = data.getStringExtra("user");
        review = data.getStringExtra("review");
        rate = data.getFloatExtra("rate", (float) 3.5);

        adapter.addItem(new CommentItem(user, review, rate));
        reviewListView.setAdapter(adapter);
    }

    // 모두보기에서 한줄평 페이지 데이터 리스트뷰에 셋업
    public void setDetailPage02(Intent data) {
        listViewItemArrayList = data.getParcelableArrayListExtra("review2");
        adapter.arrayList = listViewItemArrayList;
        reviewListView.setAdapter(adapter);
    }
}