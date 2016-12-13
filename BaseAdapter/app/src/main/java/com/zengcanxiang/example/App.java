package com.zengcanxiang.example;

import android.app.Application;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Created by zengcanxiang on 2016/11/17.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CustomActivityOnCrash.install(this);
    }
}
