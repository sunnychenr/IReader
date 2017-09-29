package com.example.ireader;

import android.app.Application;

/**
 * Created by ChenR on 2017/9/28.
 */

public class ApplicationEx extends Application {

    private static ApplicationEx instance;

    public static ApplicationEx getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
