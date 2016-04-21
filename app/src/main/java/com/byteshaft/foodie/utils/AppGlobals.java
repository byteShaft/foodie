package com.byteshaft.foodie.utils;

import android.app.Application;
import android.content.Context;


public class AppGlobals extends Application {

    private static Context sContext;
    public static final String USER_LOGIN_KEY = "user_login_key";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String BASE_URL = "http://tode.ca/tode_rest_php/";
    public static final String SEND_IMAGES_URL = String.format("%sr_sa_add_food_entry.php", BASE_URL);
    public static final String LOGIN_URL = String.format("%sr_sa_user_select.php?", BASE_URL);


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
