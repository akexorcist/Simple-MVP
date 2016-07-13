package com.akexorcist.mvpsimple.utility;

import android.content.Context;

/**
 * Created by Akexorcist on 7/14/16 AD.
 */
public class Contextor {
    private static Contextor contextor;

    public static Contextor getInstance() {
        if (contextor == null) {
            contextor = new Contextor();
        }
        return contextor;
    }

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }
}
