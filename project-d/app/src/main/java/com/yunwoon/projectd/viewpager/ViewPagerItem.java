package com.yunwoon.projectd.viewpager;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewPagerItem implements Parcelable {
    private int movieImageView;
    private String orderTextView; // 순서
    private String movieNameTextView; // 영화 제목
    private String rateTextView; // 예매율
    private String ageTextView; // 관람 등급

    public ViewPagerItem(int movieImageView, String orderTextView, String movieNameTextView, String rateTextView, String ageTextView) {
        this.movieImageView = movieImageView;
        this.orderTextView = orderTextView;
        this.movieNameTextView = movieNameTextView;
        this.rateTextView = rateTextView;
        this.ageTextView = ageTextView;
    }

    protected ViewPagerItem(Parcel in) {
        movieImageView = in.readInt();
        orderTextView = in.readString();
        movieNameTextView = in.readString();
        rateTextView = in.readString();
        ageTextView = in.readString();
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

    public String getOrderTextView() {
        return orderTextView;
    }

    public String getMovieNameTextView() {
        return movieNameTextView;
    }

    public String getRateTextView() {
        return rateTextView;
    }

    public String getAgeTextView() {
        return ageTextView;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(movieImageView);
        parcel.writeString(orderTextView);
        parcel.writeString(movieNameTextView);
        parcel.writeString(rateTextView);
        parcel.writeString(ageTextView);
    }
}
