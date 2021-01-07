package com.yunwoon.projectc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

// 리뷰 리스트뷰 어댑터
public class ListViewAdapter extends BaseAdapter {
    ArrayList<CommentItem> arrayList = new ArrayList<>();

    @Override
    public int getCount() {
        if(arrayList != null)
            return arrayList.size();
        else
            return 0;
    }

    public void addItem(CommentItem item){
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
        CommentView view;

        if(convertView==null){
            view = new CommentView(parent.getContext());
        }else{
            view = (CommentView) convertView;
        }

        CommentItem item = arrayList.get(position);
        view.setUser(item.getUserTextView());
        view.setComment(item.getCommentTextView());
        view.setRatingBar(item.getRatingBar());

        return view;
    }
}
