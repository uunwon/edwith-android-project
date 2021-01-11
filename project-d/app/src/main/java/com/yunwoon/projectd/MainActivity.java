package com.yunwoon.projectd;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_WRITE = 101;
    private static final int REQUEST_CODE_READ = 102;

    private AppBarConfiguration mAppBarConfiguration;

    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_list, R.id.nav_review, R.id.nav_detail)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        detailFragment = new DetailFragment();
        //detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.nav_detail);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // 한줄평 작성 페이지 이동
    public void showWriteReviewPage() {
        Intent intent = new Intent(getApplicationContext(), WriteReviewActivity.class);
        startActivityForResult(intent, REQUEST_CODE_WRITE);
    }

    // 한줄평 모두 보기 페이지 이동
    public void showReadReviewPage(ArrayList<CommentItem> listViewItemArrayList) {
        Intent intent = new Intent(getApplicationContext(), ReadReviewActivity.class);
        intent.putParcelableArrayListExtra("review", listViewItemArrayList); // 리스트뷰 내 내용 전달
        startActivityForResult(intent, REQUEST_CODE_READ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 작성하기에서 한줄평 페이지 데이터 리스트뷰에 셋업
        if (requestCode == REQUEST_CODE_WRITE) {
            if (resultCode == RESULT_OK) {
                detailFragment.setDetailPage01(data);
            }
        }

        // 모두보기에서 한줄평 페이지 데이터 리스트뷰에 셋업
        if (requestCode == REQUEST_CODE_READ) {
            if (resultCode == RESULT_OK) {
                detailFragment.setDetailPage02(data);
            }
        }
    }
}
