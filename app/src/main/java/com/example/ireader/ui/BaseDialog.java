package com.example.ireader.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.ireader.util.DeviceUtil;

/**
 * Created by ChenR on 2017/9/28.
 */

public abstract class BaseDialog extends Dialog {

    protected View root;

    public BaseDialog(Context context) {
        this(context, 0);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addContentView();
    }

    private void addContentView() {
        root = View.inflate(getContext(), getLayoutId(), null);
        ViewGroup.LayoutParams params = getRootLayoutParams();

        if (params != null) {
            setContentView(root, params);
        } else {
            int width = DeviceUtil.getScreenWidth() - DeviceUtil.dp2Px(24);
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            params = new ViewGroup.LayoutParams(width, height);

            setContentView(root, params);
        }

        initView();
        initData();
        bindAction();
    }

    protected abstract ViewGroup.LayoutParams getRootLayoutParams();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void bindAction();
}
