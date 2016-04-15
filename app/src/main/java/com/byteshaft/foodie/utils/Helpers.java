package com.byteshaft.foodie.utils;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
        return sharedPreferences.getBoolean(AppGlobals.USER_LOGIN_KEY, false);
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
}
