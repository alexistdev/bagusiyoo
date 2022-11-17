package com.coder.bagusiyoo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.coder.bagusiyoo.config.Constants;

public class SessionUtils {
    public static void dataSensor(Context context, String Statuspupuk, String Statussiram){

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                Constants.KEY_USER_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(Statuspupuk != null){
            editor.putString("Statuspupuk", Statuspupuk);
        }

        if(Statussiram != null){
            editor.putString("Statussiram", Statussiram);
        }
        editor.apply();
    }
}
