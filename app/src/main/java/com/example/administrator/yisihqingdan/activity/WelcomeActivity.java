package com.example.administrator.yisihqingdan.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.administrator.yisihqingdan.R;

/**
 * Created by Yishi on 2016/10/27.
 */

public class WelcomeActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {

    }
}
