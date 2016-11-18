package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.activity.ArticleDetailActivity;
import com.example.administrator.yisihqingdan.entity.ResponseBatching;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016-10-30.
 */

public class Main_up_slide_adapter extends PagerAdapter{
    private List<ResponseBatching.DataBean1.SlidesBean1.BodyBean.DataBean.SlidesBean> datas;
    private List<View> views;
    private Context context;

    public Main_up_slide_adapter(Context context, List<ResponseBatching.DataBean1.SlidesBean1.BodyBean.DataBean.SlidesBean> datas) {
        this.context = context;
        this.datas = datas;
        this.views = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(context);
        for (int i = 0; i < datas.size(); i++) {
            View view = inflater.inflate(R.layout.subview_main_slide_page,null);
            views.add(view);
        }
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = views.get(position % views.size());
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.up_slide_pager);
        simpleDraweeView.setImageURI(datas.get(position % views.size()).getFeaturedImageUrl());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ArticleDetailActivity.class);
                String str=datas.get(position % views.size()).getTarget();
                intent.putExtra("articleId",Integer.parseInt(str));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = views.get(position % views.size());
        container.removeView(view);
    }
}
