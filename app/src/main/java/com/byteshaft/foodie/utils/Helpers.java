package com.byteshaft.foodie.utils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Helpers {

    private static SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }

    // save boolean value for login status of user , takes boolean value as parameter
    public static void userLogin(boolean value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(AppGlobals.USER_LOGIN_KEY, value).apply();
    }

    // get user login status and manipulate app functions by its returned boolean value
    public static boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(AppGlobals.USER_LOGIN_KEY, true);
    }

    /**
     * Method to save String type data to sharedPreferences Requires String key
     * and value as parameter*
     */
    public static void saveDataToSharedPreferences(String key, String value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * Method to save boolean type data to sharedPreferences Requires String key
     * and boolean value as parameter*
     */
    public static void saveBooleanToSharedPreference(String key, boolean value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    // Method to get boolean value from sharedPreference requires key as parameter
    public static Boolean getBooleanValueFromSharedPreference(String key) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(key, false);
    }

    // Method to get String value from sharedPreference requires key as parameter
    public static String getStringDataFromSharedPreference(String key) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(key, "");
    }

    public static void authPostRequest(String link, String key, String value) throws IOException {
        URL url;
        url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        String jsonFormattedData = getJsonObjectString(key, value);
        sendRequestData(connection, jsonFormattedData);
    }

    public static void sendRequestData(HttpURLConnection connection, String body) throws IOException {
        byte[] outputInBytes = body.getBytes("UTF-8");
        OutputStream os = connection.getOutputStream();
        os.write(outputInBytes);
        os.close();
    }

    private static String getJsonObjectString(String key, String pushKey) {
        return String.format("{\"%s\": \"%s\"}", key, pushKey);
    }
}