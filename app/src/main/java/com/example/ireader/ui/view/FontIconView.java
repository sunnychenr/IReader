package com.example.ireader.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.ireader.R;
import com.example.ireader.util.LogUtil;

/**
 * Created by ChenR on 2017/12/5.
 */

public class FontIconView extends TextView {
    private static final String DEFAULT_FONT_NAME = "icomoon";

    private String fontName;

    public FontIconView(Context context, String fontName) {
        super(context);
        this.fontName = fontName;
        init(context);
    }

    public FontIconView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FontIconView, 0, 0);

        try {
            fontName = typedArray.getString(R.styleable.FontIconView_fontName);
        } catch (Exception e) {
            LogUtil.error(e);
        }

        init(context);
    }

    private void init(Context context) {
        String font = fontName + ".ttf";

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), font);
        this.setTypeface(typeface);
    }


}
