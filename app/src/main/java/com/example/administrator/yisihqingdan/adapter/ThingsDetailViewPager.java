package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yisihqingdan.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Yishi on 2016/11/14.
 */
public class ThingsDetailViewPager extends PagerAdapter{
    private List<String> images;
    private Context context;

    public ThingsDetailViewPager(List<String> images, Context context) {
        this.images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (images.size() == 1){
            return 1;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.things_detail_viewpager_item,null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.things_detail_item_simpledrawee);
        simpleDraweeView.setImageURI(images.get(position % images.size()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = LayoutInflater.from(context).inflate(R.layout.things_detail_viewpager_item,null);
        container.removeView(view);
    }
}
