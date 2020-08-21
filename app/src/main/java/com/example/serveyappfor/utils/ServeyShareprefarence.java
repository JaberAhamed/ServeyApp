package com.example.serveyappfor.utils;

import android.content.SharedPreferences;
import android.content.pm.ServiceInfo;

import com.example.serveyappfor.models.Serveyinfo;

public class ServeyShareprefarence {

    static final String KEY_CHECK = "product";
    static final String KEY_TEXT = "adress";
    static final String KEY_MULTY = "how";
    static final String KEY_DROP = "use";
    static final String KEY_NUMBER = "number";

    private static SharedPreferences mSharedPreferences;

    public ServeyShareprefarence(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }



    public static boolean saveserveyInfo(Serveyinfo serveyinfo){

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_CHECK, serveyinfo.getCeck());
        editor.putString(KEY_TEXT, serveyinfo.getAddress());


        return editor.commit();
    }


    public static String  getCheck() {

        String check = mSharedPreferences.getString(KEY_CHECK, "");


        return  check;
    }

    public static String  getText() {

        String text= mSharedPreferences.getString(KEY_TEXT, "");


        return  text;
    }


}
