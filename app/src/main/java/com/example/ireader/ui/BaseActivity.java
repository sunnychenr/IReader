package com.example.ireader.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.ireader.ApplicationEx;

import java.lang.ref.WeakReference;

public abstract class BaseActivity extends Activity {
    private BaseActivityHandler mHandler = new BaseActivityHandler(this);

    protected ApplicationEx appEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        appEx = ApplicationEx.getInstance();

        initView();
        initData();
        bindAction();
    }

    protected abstract int getContentViewId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void bindAction();

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById (Class<T> type, int resId) {
        View target = findViewById(resId);
        if (target != null) {
            return (T) target;
        }
        return null;
    }

    protected void handleMessage (Message msg) {

    }

    protected BaseActivityHandler getActivityHandler () {
        return mHandler;
    }

    static class BaseActivityHandler extends Handler {
        private WeakReference<BaseActivity> mActivity = null;

        public BaseActivityHandler (BaseActivity activity) {
            mActivity = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity != null && mActivity.get() != null && !mActivity.get().isFinishing()) {
                mActivity.get().handleMessage(msg);
            }
        }
    }
}
