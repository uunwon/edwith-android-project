package com.yunwoon.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 서버용
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ServerThread thread = new ServerThread();
                // thread.start();

                Intent intent = new Intent(getApplicationContext(), ChatService.class);
                startService(intent);
            }
        });
    }

    /* class ServerThread extends Thread { // 서버 기능 구현
        public void run() {
            int port = 5001;

            try {
                ServerSocket server = new ServerSocket(port); // 서버 실행
                Log.d("ServerThread", "서버가 실행됨.");

                while (true) {
                    Socket socket = server.accept(); // 대기 상태로 들어감
                    ObjectInputStream instream = new ObjectInputStream(socket.getInputStream()); // 들어오는 데이터 처리
                    Object input = instream.readObject(); // 읽어들임
                    Log.d("ServerThread", "input : " + input);

                    ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream()); // 데이터 내보냄
                    outstream.writeObject(input + " from server.");
                    outstream.flush();
                    Log.d("ServerThread", "output 보냄.");

                    socket.close(); // 더이상 유지 안하면, 소켓 끊어주기 (낭비니까)
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } */

}
