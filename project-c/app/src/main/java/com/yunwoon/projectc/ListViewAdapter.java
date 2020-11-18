package com.yunwoon.projectc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

// 리뷰 리스트뷰 어댑터
public class ListViewAdapter extends BaseAdapter {
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
        ListItemView view = new ListItemView(parent.getContext());

        ListViewItem item = arrayList.get(position);
        view.setUser(item.getUserTextView());
        view.setComment(item.getCommentTextView());
        view.setRatingBar(item.getRatingBar());

        return view;
    }
}
