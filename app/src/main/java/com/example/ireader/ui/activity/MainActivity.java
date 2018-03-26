package com.example.ireader.ui.activity;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.example.ireader.R;
import com.example.ireader.ui.BaseActivity;
import com.example.ireader.util.ToastUtils;

/**
 * Created by ChenR on 2017/9/28.
 */

public class MainActivity extends BaseActivity implements DrawerLayout.DrawerListener{
    private DrawerLayout mLayoutRoot;
    private View mLayoutContent;
    private View mLayoutMenu;

    private long mLastBackTime = 0;

    @Override
    protected void bindAction() {
        mLayoutRoot.addDrawerListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mLayoutRoot = findViewById(DrawerLayout.class, R.id.layout_root);
        mLayoutContent = findViewById(R.id.layout_content);
        mLayoutMenu = findViewById(R.id.layout_menu);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();

        if (now - mLastBackTime < 2000) {
            super.onBackPressed();
        } else {
            mLastBackTime = now;
            ToastUtils.showToast(this, getString(R.string.exit_app_hint));
        }

    }
}
