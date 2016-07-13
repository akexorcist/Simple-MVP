package com.akexorcist.mvpsimple;

import android.app.Application;

import com.akexorcist.mvpsimple.utility.Contextor;

/**
 * Created by Akexorcist on 7/14/16 AD.
 */
public class MVCApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().setContext(getApplicationContext());
    }
}
