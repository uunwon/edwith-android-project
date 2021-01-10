package com.yunwoon.projectd.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.yunwoon.projectd.R;
import com.yunwoon.projectd.viewpager.FirstmvFragment;
import com.yunwoon.projectd.viewpager.SecondmvFragment;
import com.yunwoon.projectd.viewpager.ThirdmvFragment;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        ViewPager pager = rootView.findViewById(R.id.viewPager);
        pager.setOffscreenPageLimit(3); // 담을 프래그먼트 갯수 설정

        MoviePagerAdapter adapter = new MoviePagerAdapter(getFragmentManager());

        FirstmvFragment firstmvFragment = new FirstmvFragment();
        adapter.addItem(firstmvFragment);
        SecondmvFragment secondmvFragment = new SecondmvFragment();
        adapter.addItem(secondmvFragment);
        ThirdmvFragment thirdmvFragment = new ThirdmvFragment();
        adapter.addItem(thirdmvFragment);

        pager.setAdapter(adapter);

        return rootView;
    }

    class MoviePagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<>();

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
}