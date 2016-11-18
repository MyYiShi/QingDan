package com.example.administrator.yisihqingdan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yishi on 2016/10/27.
 */

public abstract class Base_fragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentResId(),null);
    }
    protected abstract int getContentResId();

}
