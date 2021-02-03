package com.yunwoon.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String urlStr;
    private ImageView imageView;

    private static HashMap<String, Bitmap> bitmapHashMap = new HashMap<>(); // 요청 url과 비트맵 객체를 해시테이블로 매핑해둠

    public ImageLoadTask(String urlStr, ImageView imageView) {
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap = null;

        try {
            if(bitmapHashMap.containsKey(urlStr)) {
                Bitmap oldBitmap = bitmapHashMap.remove(urlStr);
                if(oldBitmap != null) {
                    oldBitmap.recycle();
                }
            }
            URL url = new URL(urlStr);
            // 해당 주소로 접속해서 스트림 받는데 이미지면 이미지 스트림대로 넘어오고, decodeStream을 이용해 비트맵으로 바꿔줌
            // 그 비트맵 이미지를 이미지 뷰에 보여주면 되지!
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            bitmapHashMap.put(urlStr, bitmap);
       } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        // doInBackground 에서 리턴받은 bitmap 인거임~
        imageView.setImageBitmap(bitmap);
        imageView.invalidate(); // 다시 그려줌
    }

}
