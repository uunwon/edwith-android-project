package com.yunwoon.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Volley 를 이용해서 요청하고 싶음
                sendRequest();
            }
        });

        if(AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void sendRequest() {
        // Request 객체를 만들고 RequestQueue 에 넣어주면 됨
        String url = "http://www.google.co.kr";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url, // 어떤 url 로 요청할 것인가
                new Response.Listener<String>() { // 응답을 문자열로 받아 넣어줌
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);
                    }
                },
                new Response.ErrorListener() { // 중간에 에러 발생시 자동 호출
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {
            // Request 안에 메서드 재정의 가능
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        request.setShouldCache(false); // 이전 결과가 있더라도 새로 요청해서 결과를 보여주도록 함
        AppHelper.requestQueue.add(request);
        println("요청 보냄.");
    }

    public void println(String data) {
        textView.append(data + "\n"); // 데이터 한 줄씩 추가하기
    }
}
