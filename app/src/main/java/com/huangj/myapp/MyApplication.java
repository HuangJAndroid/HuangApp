package com.huangj.myapp;

import android.app.Application;

import org.xutils.x;


/**
 * Created by tjj on 2016/6/5.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }



}
