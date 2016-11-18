package com.example.administrator.yisihqingdan.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

/**
 * Created by Yishi on 2016/10/27.
 */

public abstract class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(getContentResId());
        initView();

    }

    /**
     * 获得activity布局的ID
     */
    protected abstract int getContentResId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置全屏
     */
    private void setFullScreen(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
