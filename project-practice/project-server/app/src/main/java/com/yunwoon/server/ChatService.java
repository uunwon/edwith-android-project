package com.yunwoon.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatService extends Service {
    // 서비스를 객체를 처음에 startService 로 실행하면 스레드가 만들어지면서 서버가 대기하는 상황이 만들어질거임
    public ChatService() {
    }

    @Override
    public void onCreate() { // 초기화 되는 시점
        super.onCreate();

        ServerThread thread = new ServerThread();
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class ServerThread extends Thread { // 서버 기능 구현
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
    }
}
