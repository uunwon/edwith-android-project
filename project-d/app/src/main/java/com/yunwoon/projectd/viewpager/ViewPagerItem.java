package com.yunwoon.projectd.viewpager;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewPagerItem implements Parcelable {
    private int movieImageView;
    private int orderTextView; // 순서
    private String movieNameTextView; // 영화 제목
    private float rateTextView; // 예매율
    private int ageTextView; // 관람 등급

    public ViewPagerItem(int movieImageView, int orderTextView, String movieNameTextView, float rateTextView, int ageTextView) {
        this.movieImageView = movieImageView;
        this.orderTextView = orderTextView;
        this.movieNameTextView = movieNameTextView;
        this.rateTextView = rateTextView;
        this.ageTextView = ageTextView;
    }

    protected ViewPagerItem(Parcel in) {
        movieImageView = in.readInt();
        orderTextView = in.readInt();
        movieNameTextView = in.readString();
        rateTextView = in.readFloat();
        ageTextView = in.readInt();
    }

    public static final Creator<ViewPagerItem> CREATOR = new Creator<ViewPagerItem>() {
        @Override
        public ViewPagerItem createFromParcel(Parcel in) {
            return new ViewPagerItem(in);
        }

        @Override
        public ViewPagerItem[] newArray(int size) {
            return new ViewPagerItem[size];
        }
    };

    public int getMovieImageView() {
        return movieImageView;
    }

    public int getOrderTextView() {
        return orderTextView;
    }

    public String getMovieNameTextView() {
        return movieNameTextView;
    }

    public float getRateTextView() {
        return rateTextView;
    }

    public int getAgeTextView() {
        return ageTextView;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(movieImageView);
        parcel.writeInt(orderTextView);
        parcel.writeString(movieNameTextView);
        parcel.writeFloat(rateTextView);
        parcel.writeInt(ageTextView);
    }
}
