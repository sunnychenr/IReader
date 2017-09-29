package com.example.ireader.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.example.ireader.ApplicationEx;

public abstract class BaseActivity extends Activity {
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

    protected abstract void bindAction();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getContentViewId();

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById (Class<T> type, int resId) {
        View target = findViewById(resId);
        if (target != null) {
            return (T) target;
        }
        return null;
    }
}
