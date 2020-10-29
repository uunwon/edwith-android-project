package com.yunwoon.projectb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView thumb_up, thumb_up_selected, thumb_down, thumb_down_selected;
    TextView thumb_up_txt, thumb_down_txt, writeReview;
    Button readReview;
    int thumbUp = 0, thumbDown = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thumb_up_txt = findViewById(R.id.thumb_up_text);
        thumb_down_txt = findViewById(R.id.thumb_down_text);

        thumb_up = findViewById(R.id.thumb_up); thumb_up_selected = findViewById(R.id.thumb_up_selected);
        thumb_down = findViewById(R.id.thumb_down); thumb_down_selected = findViewById(R.id.thumb_down_selected);

        writeReview = findViewById(R.id.writeReview);
        writeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "'작성하기'를 클릭하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        readReview = findViewById(R.id.readReview);
        readReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "'모두보기'를 클릭하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
