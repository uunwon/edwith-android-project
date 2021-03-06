package com.yunwoon.projecte.navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yunwoon.projecte.MainActivity;
import com.yunwoon.projecte.R;
import com.yunwoon.projecte.viewpager.ViewPagerAdapter;
import com.yunwoon.projecte.viewpager.ViewPagerItem;

public class ListFragment extends Fragment {
    MainActivity activity;

    @Override
    public void onAttach(Context context) { // 프래그먼트가 올라오는 순간
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        ViewPager pager = rootView.findViewById(R.id.viewPager);
        pager.setOffscreenPageLimit(3); // 담을 프래그먼트 갯수 설정

        ViewPagerAdapter adapter = new ViewPagerAdapter();
        adapter.addItem(new ViewPagerItem(R.drawable.image1,"1","군 도", "61.6", "15"));
        adapter.addItem(new ViewPagerItem(R.drawable.image2,"2","공 조", "15.5", "15"));
        adapter.addItem(new ViewPagerItem(R.drawable.image3,"3","더 킹", "11.2", "12"));
        pager.setAdapter(adapter);

        return rootView;
    }
}