package com.example.ireader.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by ChenR on 2018/4/10.
 */

public class ThreadPoolManager {
    private static ThreadPoolManager instance = null;

    private ExecutorService mExecutorService = null;

    private ThreadPoolManager () {
        mExecutorService = new ThreadPoolExecutor(5, 15, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(20));
    }

    public static ThreadPoolManager getInstance () {
        if (instance == null) {
            synchronized (ThreadPoolManager.class) {
                if (instance == null) instance = new ThreadPoolManager();
            }
        }
        return instance;
    }

    public void wrappedSubmit (Runnable task) {
        if (mExecutorService != null) {
            mExecutorService.submit(task);
        }
    }


}
