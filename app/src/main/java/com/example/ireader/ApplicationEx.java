package com.example.ireader;

import android.app.Application;

import com.example.ireader.db.manager.BookDbManager;

/**
 * Created by ChenR on 2017/9/28.
 */

public class ApplicationEx extends Application {

    private static ApplicationEx instance;
    public static ApplicationEx getInstance() {
        return instance;
    }

    private BookDbManager mBookDbManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initBookDb();
    }

    private void initBookDb() {
        mBookDbManager = new BookDbManager(this);
    }

    public BookDbManager getBookDbManager () {
        return mBookDbManager;
    }
}
