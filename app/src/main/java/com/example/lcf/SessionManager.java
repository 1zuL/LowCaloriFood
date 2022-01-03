package com.example.lcf;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.lcf.Activity.MainActivity;
import com.example.lcf.LoginRegister.Login;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "login";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAMALENGKAP = "namalengkap";
    public static final String ID = "id";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createdSession(String namalengkap, String id){
        editor.putBoolean(LOGIN, true);
        editor.putString(NAMALENGKAP, namalengkap);
        editor.putString(ID,id);
        editor.apply();
    }
    public boolean isLoggin(){
        return sharedPreferences.getBoolean(LOGIN, false);
    }
    public void checkLogin(){
        if (!this.isLoggin()){
            Intent i = new Intent(context, Login.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user =  new HashMap<>();
        user.put(NAMALENGKAP, sharedPreferences.getString(NAMALENGKAP, null));
        user.put(ID, sharedPreferences.getString(ID, null));

        return user;
    }
    public  void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, Login.class);
        context.startActivity(i);
        ((MainActivity) context).finish();
    }
}
