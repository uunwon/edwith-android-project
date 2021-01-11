package com.yunwoon.projectd.navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yunwoon.projectd.MainActivity;
import com.yunwoon.projectd.R;
import com.yunwoon.projectd.ViewPagerAdapter;
import com.yunwoon.projectd.viewpager.FirstmvFragment;
import com.yunwoon.projectd.viewpager.SecondmvFragment;
import com.yunwoon.projectd.viewpager.ThirdmvFragment;

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

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());

        FirstmvFragment firstmvFragment = new FirstmvFragment();
        adapter.addItem(firstmvFragment);
        SecondmvFragment secondmvFragment = new SecondmvFragment();
        adapter.addItem(secondmvFragment);
        ThirdmvFragment thirdmvFragment = new ThirdmvFragment();
        adapter.addItem(thirdmvFragment);

        pager.setAdapter(adapter);

        return rootView;
    }
}