package com.example.administrator.yisihqingdan.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Yishi on 2016/10/31.
 */

public class QingdanApplication extends Application{
    public static QingdanApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
    }
}
