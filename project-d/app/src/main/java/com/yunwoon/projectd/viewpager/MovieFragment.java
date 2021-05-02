package com.yunwoon.projectd.viewpager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yunwoon.projectd.R;

public class MovieFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_viewpager, container, false);

        Button button = rootView.findViewById(R.id.detailButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //내비게이션 컨트롤러를 이용해 상세 화면 전환
                Navigation.findNavController(view).navigate(R.id.action_nav_list_to_nav_detail);
            }
        });

        return rootView;
    }
}