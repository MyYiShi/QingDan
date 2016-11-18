package com.example.administrator.yisihqingdan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CheckedTextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.MainFragmentPagerAdapter;
import com.example.administrator.yisihqingdan.fragment.Category_fragment;
import com.example.administrator.yisihqingdan.fragment.List_fragment;
import com.example.administrator.yisihqingdan.fragment.Personal_fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private int[] ctId;
    private CheckedTextView[] ct;
    private ViewPager main_viewpager;
    private MainFragmentPagerAdapter mainFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        main_viewpager = (ViewPager) findViewById(R.id.main_viewpager);
        ctId = new int[]{R.id.main_ll_list_ct,R.id.main_ll_category_ct,R.id.main_ll_personal_ct};
        ct = new CheckedTextView[ctId.length];
        for (int i = 0; i < ctId.length; i++) {
            ct[i] = (CheckedTextView) findViewById(ctId[i]);
            ct[i].setOnClickListener(this);
        }
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(),getFragment());
        main_viewpager.setAdapter(mainFragmentPagerAdapter);
        main_viewpager.setOffscreenPageLimit(3);
        main_viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                showPage(position);
            }
        });
        showPage(0);
    }

    /**
     * 获得fragment的List集合
     */
    private List<Fragment> getFragment(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new List_fragment());
        fragments.add(new Category_fragment());
        fragments.add(new Personal_fragment());
        return  fragments;
    }

    /**
     * 监听主菜单下面的三个选项
     */
    @Override
    public void onClick(View v) {
        for (int i = 0; i < ct.length; i++) {
            if (ct[i] == v){
                showPage(i);
                return;
            }
        }
    }

    /**
     * 显示当前页面
     */
    private void showPage(int index) {
        if (ct[index].isChecked()) return;
        for (int i = 0; i < ctId.length; i++) {
            if (i == index){
                ct[i].setChecked(true);
            }else{
                ct[i].setChecked(false);
            }
        }
        main_viewpager.setCurrentItem(index);
    }
}
