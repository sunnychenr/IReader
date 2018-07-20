package com.example.ireader.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.ireader.util.DeviceUtil;

/**
 * Created by ChenR on 2017/9/28.
 */

public class SinglePageView extends View {
    private static final int DEFAULT_PADDING = DeviceUtil.dp2Px(10);

    private int padding = DEFAULT_PADDING;

    public SinglePageView(Context context) {
        this(context, null);
    }

    public SinglePageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SinglePageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
