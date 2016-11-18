package com.example.administrator.yisihqingdan.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.yisihqingdan.R;

/**
 * Created by Yishi on 2016/10/31.
 */

public class PagerDotIndicator {
    private LinearLayout indicatorContainer;
    private ViewPager viewPager;
    private LayoutInflater inflater;
    private int normalDotRes;
    private int selectedDotRes;

    public PagerDotIndicator(Context context,final LinearLayout indicatorContainer,ViewPager viewPager){
        this.indicatorContainer = indicatorContainer;
        this.viewPager = viewPager;
        this.inflater = LayoutInflater.from(context);
        normalDotRes = R.drawable.home_page_controls_nor;
        selectedDotRes = R.drawable.home_page_controls_hl;
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i = 0; i < indicatorContainer.getChildCount(); i++) {
                    ImageView imageView = (ImageView) indicatorContainer.getChildAt(i).findViewById(R.id.imageView_indicator_dot);
                    if (position % indicatorContainer.getChildCount() == i){
                        imageView.setImageResource(selectedDotRes);
                    }else{
                        imageView.setImageResource(normalDotRes);
                    }
                }
            }
        });
    }


    /**
     * 外部可以设置指示器的图片资源（如果不设置就使用本类中默认的指示器图片）
     */
    public void setDotRes(int normalDotRes,int selectedDotRes){
        this.normalDotRes = normalDotRes;
        this.selectedDotRes = selectedDotRes;
    }

    /**
     * 设置指示器的个数
     */
    public void setDotNums(int size){
        for (int i = 0; i < size; i++) {
            View view = inflater.inflate(R.layout.subview_indicator_dots,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView_indicator_dot);
            if (i == 0){
                imageView.setImageResource(selectedDotRes);
            }else{
                imageView.setImageResource(normalDotRes);
            }
            indicatorContainer.addView(view);
        }
    }
}
