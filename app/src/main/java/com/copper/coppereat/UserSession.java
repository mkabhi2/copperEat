package com.copper.coppereat;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kumarpallav on 23/11/17.
 */

public class UserSession {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public UserSession(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("myapp",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void setLoggedIn(boolean loggedIn){
        editor.putBoolean("loggedinmode",loggedIn);
        editor.commit();
    }

    public boolean loggedIn(){
        return sharedPreferences.getBoolean("loggedinmode",false);

    }

}
