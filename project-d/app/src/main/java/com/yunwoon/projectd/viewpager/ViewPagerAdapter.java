package com.yunwoon.projectd.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yunwoon.projectd.CommentItem;
import com.yunwoon.projectd.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

// 영화 뷰페이저 어댑터
public class ViewPagerAdapter extends PagerAdapter {
    ArrayList<ViewPagerItem> movies = new ArrayList<>();
    private Context mContext = null;

    public ViewPagerAdapter() { }

    public ViewPagerAdapter(Context context) {
        this.mContext = context;
    }

    public void addItem(ViewPagerItem item){
        movies.add(item);
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = null;
        ViewPagerItem item = movies.get(position);

        mContext = container.getContext();

        if(mContext != null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_viewpager, container, false);

            ImageView movieImageView = view.findViewById(R.id.movieImageView); // 영화 이미지
            movieImageView.setImageResource(item.getMovieImageView());

            TextView orderTextView = view.findViewById(R.id.orderTextView); // 순위
            orderTextView.setText(item.getOrderTextView());

            TextView movieNameTextView = view.findViewById(R.id.movieNameTextView); // 영화 제목
            movieNameTextView.setText(item.getMovieNameTextView());

            TextView rateTextView = view.findViewById(R.id.rateTextView); // 영화 예매율
            rateTextView.setText(item.getRateTextView());

            TextView ageTextView = view.findViewById(R.id.ageTextView); // 관람 등급
            ageTextView.setText(item.getAgeTextView());

            Button detailButton = view.findViewById(R.id.detailButton);
            detailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 내비게이션 컨트롤러를 이용해 상세 화면 전환
                    Navigation.findNavController(view).navigate(R.id.action_nav_list_to_nav_detail);
                }
            });
        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(movies != null)
            return movies.size();
        else
            return 0;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object); // 뷰 페이저에서 삭제
    }

    // 페이지뷰가 키 객체와 연관되는지 확인
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }
}
