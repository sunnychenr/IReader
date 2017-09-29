package com.example.ireader.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.ireader.util.DeviceUtils;

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

        addContentView();
    }

    private void addContentView() {
        root = View.inflate(getContext(), getLayoutId(), null);
        ViewGroup.LayoutParams params = getRootLayoutParams();

        if (params != null) {
            setContentView(root, params);
        } else {
            int width = DeviceUtils.getScreenWidth() - DeviceUtils.dp2Px(24);
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
