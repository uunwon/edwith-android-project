package com.yunwoon.socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// 클라이언트용
public class MainActivity extends AppCompatActivity {

    TextView textView;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    class ClientThread extends Thread {
        public void run() {
            String host = "localhost"; // 어떤 컴퓨터로 접속할지 ip 를 지정 (localhost = 자기자신)
            int port = 5001; // 포트는 서버와 동일하게 작성

            try {
                Socket socket = new Socket(host, port);

                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream()); // 보내기 위한 통로 만들기
                outstream.writeObject("안녕!");
                outstream.flush(); // outstream 할 때는 꼭 flush 해주기!
                Log.d("ClientThread", "서버로 보냄");

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                final Object input = instream.readObject(); // 받는 데이터 , final을 붙여서 핸들러가 상수처럼 접근할 수 있게 해줌
                Log.d("ClientThread", "받은 데이터 : " + input);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("받은 데이터 : " + input);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
