package com.shir.androidfinalproject.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shir on 01-Sep-17.
 */

public class DataManager {

    public static final String CHECK = "CHECK";
    public static final String IS_CONNECTED_WITH_FACEBOOK = "IS_CONNECTED_WITH_FACEBOOK";
    public static final String USER_ID = "USER_ID";
    private static DataManager ourInstance;
    private Context mContext;

    public static DataManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DataManager(context);
        }
        return ourInstance;
    }

    private DataManager(Context context) {
        mContext = context;
    }

    public void setLoginWithFacebook(boolean isLogin) {
        getSharedPreferences().edit().putBoolean(IS_CONNECTED_WITH_FACEBOOK, isLogin).apply();
        getSharedPreferences().edit().putString(USER_ID, null).apply();
    }

    public boolean isLoggedIn() {
        return getSharedPreferences().getBoolean(IS_CONNECTED_WITH_FACEBOOK, false);
    }

    private SharedPreferences getSharedPreferences() {
        return mContext.getSharedPreferences(CHECK, Context.MODE_PRIVATE);
    }

    public void destroy() {
        mContext = null;
        ourInstance = null;
    }

    public void saveUserId(String id) {
        getSharedPreferences().edit().putString(USER_ID, id).apply();
    }

    public String getUserId() {
        return getSharedPreferences().getString(USER_ID, null);
    }
}
