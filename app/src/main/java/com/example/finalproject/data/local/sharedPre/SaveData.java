package com.example.finalproject.data.local.sharedPre;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class SaveData {
    SharedPreferences mSharedPreferences;

    public SaveData(@NonNull Context context) {
        mSharedPreferences = context.getSharedPreferences("myData", Context.MODE_PRIVATE);
    }

    public void updateUserStatus(boolean status) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("status", status);
        editor.apply();
    }
    public void UserInfo(String name,String email) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.apply();
    }

    public boolean getUserStatus() {
        return mSharedPreferences.getBoolean("status", false);
    }
    public String getUserName() {
        return mSharedPreferences.getString("name","a");
    }
    public String getEmail() {
        return mSharedPreferences.getString("email","a");
    }
}
