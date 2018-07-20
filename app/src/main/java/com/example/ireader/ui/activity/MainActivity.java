package com.example.ireader.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.ireader.R;
import com.example.ireader.ui.BaseActivity;
import com.example.ireader.util.LogUtil;
import com.example.ireader.util.ToastUtil;

/**
 * Created by ChenR on 2017/9/28.
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private long mLastBackTime = 0;

    private Toolbar mToolBar = null;
    private DrawerLayout mDrawerLayout = null;
    private FrameLayout mLayoutContent = null;
    private NavigationView mLayoutMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindAction() {
        // 设置随打开关闭动作变化的toolbar左侧按钮;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolBar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mLayoutMenu.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        mDrawerLayout = findViewById(DrawerLayout.class, R.id.layout_root);
        mToolBar = findViewById(Toolbar.class, R.id.tool_bar);
        mLayoutContent = findViewById(FrameLayout.class, R.id.layout_content);
        mLayoutMenu = findViewById(NavigationView.class, R.id.layout_menu);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        } else {
            long now = System.currentTimeMillis();

            if (now - mLastBackTime < 2000) {
                super.onBackPressed();
            } else {
                mLastBackTime = now;
                ToastUtil.showToast(this, getString(R.string.exit_app_hint));
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_read:
                break;
            case R.id.menu_item_file:
                break;
            case R.id.menu_item_share:
                break;
            case R.id.menu_item_feedback:
                break;
        }
        mDrawerLayout.closeDrawer(Gravity.START);
        return false;
    }
}
