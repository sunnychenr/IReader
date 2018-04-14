package com.example.ireader.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.ireader.ApplicationEx;

/**
 * Created by ChenR on 2017/9/28.
 */

public class DeviceUtil {

    public static int dp2Px (int dp) {
        final float scale = ApplicationEx.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2Dp(float px) {
        final float scale = ApplicationEx.getInstance().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) ApplicationEx.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return Math.min(metrics.widthPixels, metrics.heightPixels);
    }

    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) ApplicationEx.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return Math.max(metrics.widthPixels, metrics.heightPixels);
    }

    //px
    public static int getStatusBarHeight() {
        Resources resources = ApplicationEx.getInstance().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

}
