package com.example.attenence.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class shared_pref {


    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public shared_pref() {
    }

    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(pref_util.LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static void setName(Context context, String Name) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(pref_util.LOGGED_IN_NAME, Name);
        editor.apply();
    }

    public static void setEmail(Context context, String Email) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(pref_util.LOGGED_IN_EMAIL, Email);
        editor.apply();
    }
    public static void setPhoneNumber(Context context, int phn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(pref_util.LOGGED_IN_PHONE_NUMBER, phn);
        editor.apply();
    }

    public static void setDept(Context context, String Dept) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(pref_util.LOGGED_IN_DEPT, Dept);
        editor.apply();
    }



    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(pref_util.LOGGED_IN_PREF, false);
    }

    public static String getName(Context context) {
        return getPreferences(context).getString(pref_util.LOGGED_IN_NAME,"name");
    }

    public static String getEmail(Context context) {
        return getPreferences(context).getString(pref_util.LOGGED_IN_EMAIL,"email");
    }
    public static int getPhn(Context context) {
        return getPreferences(context).getInt(pref_util.LOGGED_IN_PHONE_NUMBER,0);
    }
    public static String getDept(Context context) {
        return getPreferences(context).getString(pref_util.LOGGED_IN_DEPT,"dept");
    }

}
