package com.example.alexandr.bindingrecycler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexandr on 27.01.2017.
 */

public class User implements Parcelable {

    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("login")
    private String userName;


    public User(String userName, String avatarUrl) {
        this.userName = userName;
        this.avatarUrl = avatarUrl;
    }


    protected User(Parcel in) {
        avatarUrl = in.readString();
        userName = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(avatarUrl);
        dest.writeString(userName);
    }
}
