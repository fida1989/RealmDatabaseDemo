package com.realmdemo;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by knroy on 2/13/2018.
 * Don't modify without proper privileges
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}