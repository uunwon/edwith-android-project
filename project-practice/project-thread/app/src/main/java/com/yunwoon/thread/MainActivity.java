package com.yunwoon.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    // int value = 0;

    ValueHandler handler = new ValueHandler();
    Handler handler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button); // 스레드 만들어서 실행 (내부 클래스로)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // BackgroundThread thread = new BackgroundThread();
                // thread.start(); //start 했을 때 run 메서드 호출됨

                /*new Thread(new Runnable() { // Runnable 인터페이스를 이용해 직접 한번 실행될 객체 정의 가능
                    int value = 0;
                    boolean running = false;
                    @Override
                    public void run() {
                        running = true;
                        while (running) {
                            value += 1;

                            handler2.post(new Runnable() { // 핸들러를 직접적으로 던짐, Runnable 객체가 handler2 로 던져져서 실행됨
                                @Override
                                public void run() {
                                    textView.setText("간결한 현재 값 : " + value);
                                }
                            });
                            try {
                                Thread.sleep(1000); // 1초 동안 쉬기
                            } catch (Exception e) {}
                        }
                    }
                }).start(); */
                ProgressTask task = new ProgressTask();
                task.execute("시작"); // doInBackground 에 시작이라는 문자열이 전달됨 , 의미는 없어
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // textView.setText("현재 값: " + value); // 메인 스레드 안에서 ui 를 접근하는 거라 문제 없음
            }
        });
    }

    class ProgressTask extends AsyncTask<String, Integer, Integer> { // 에이씽크테스크
        int value = 0;

        @Override
        protected Integer doInBackground(String... strings) { // 콜백 메서드라 자동 실행됨, 스레드 안에 들어가는 코드를 그대로 넣기 / ...은 가변길이 파라미터를 의미함
            // 어떤 값을 전달하냐에 따라 결과값을 전달받는 onPostExecute 의 타입이 결정됨.
            // 처리하는 코드
            while (true) {
                if(value > 10) {
                    break;
                }
                value += 1;
                publishProgress(value); // onProgressUpdate 가 실행되면서 중간중간 업데이트 가능!

                try {
                    Thread.sleep(1000); // 1초 동안 쉬기
                } catch (Exception e) {}
            }
            return value; // 과정이 다 끝나면 onPostExecute 이 실행돼라~
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 중간중간 ui 업데이트
            super.onProgressUpdate(values);
            textView.setText("에이씽크 값 : " + values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) { // 결과 값을 전달받음
            super.onPostExecute(integer);

            Toast.makeText(getApplicationContext(), "완료됨", Toast.LENGTH_SHORT).show();
        }

    }

    class BackgroundThread extends Thread {
        int value = 0;
        boolean running = false;

        public void run() { // 처음 시작 시 실행
            running = true;
            while (running) {
                value += 1;

                Message message = handler.obtainMessage(); //메시지 객체를 참조해서 메시지를 만드는 거임
                Bundle bundle = new Bundle();
                bundle.putInt("value", value); //put 으로 넣고 get 으로 빼고
               message.setData(bundle); //set 으로 넣고 get 으로 빼고
                handler.sendMessage(message); // 핸들러로 전달되는 거야, handleMessage 가 자동 호출됨
                try {
                    Thread.sleep(1000); // 1초 동안 쉬기
                } catch (Exception e) {}

            }
        }
    }

    class ValueHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");

            textView.setText("현재 값: " + value); // 핸들러는 메인 스레드에서 동작하기 때문에 문제 없음
        }
    }
}
