package com.example.administrator.yisihqingdan.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.MainTabFragmentPagerAdapter;
import com.example.administrator.yisihqingdan.adapter.Main_up_slide_adapter;
import com.example.administrator.yisihqingdan.entity.ResponseBatching;
import com.example.administrator.yisihqingdan.mvp.present.MainPresenter;
import com.example.administrator.yisihqingdan.mvp.view.MainView_interf;
import com.example.administrator.yisihqingdan.widget.PagerDotIndicator;

import java.util.List;

/**
 * Created by Yishi on 2016/10/27.
 */

public class List_fragment extends Base_fragment implements MainView_interf{
    private MainPresenter mainPresenter;
    private Main_up_slide_adapter main_up_slide_adapter;
    private ViewPager up_viewpager;
    /**ViewPager的指示器容器**/
    private LinearLayout mainIndicatorContainer;
    /**管理指示器的对象**/
    private PagerDotIndicator pagerDotIndicator;

    private TabLayout tabLayout;
    private ViewPager mainListFragmentViewPager; //装Fragment的ViewPager
    private MainTabFragmentPagerAdapter mainTabFragmentPagerAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        up_viewpager = (ViewPager) getView().findViewById(R.id.up_viewpager);
        mainPresenter = new MainPresenter(this);
        mainPresenter.loadBatching();

        mainIndicatorContainer = (LinearLayout) getView().findViewById(R.id.up_viewpager_indicator);
        pagerDotIndicator = new PagerDotIndicator(getActivity(),mainIndicatorContainer,up_viewpager);

        tabLayout = (TabLayout) getView().findViewById(R.id.tablayout);
        mainListFragmentViewPager = (ViewPager) getView().findViewById(R.id.list_innerfragment_viewpager);
        tabLayout.setupWithViewPager(mainListFragmentViewPager);
    }

    @Override
    protected int getContentResId() {
        return R.layout.fragment_list;
    }

    @Override
    public void showBatchingData(ResponseBatching batching) {
        List<ResponseBatching.DataBean1.SlidesBean1.BodyBean.DataBean.SlidesBean> slides =
                batching.getData().getSlides().getBody().getData().getSlides();
        main_up_slide_adapter = new Main_up_slide_adapter(getActivity(),slides);
        up_viewpager.setAdapter(main_up_slide_adapter);
        pagerDotIndicator.setDotNums(slides.size());

        //设置下面的ViewPager
        mainTabFragmentPagerAdapter = new MainTabFragmentPagerAdapter(
                batching.getData().getChannels().getBody().getData().getChannels(),getFragmentManager());
        mainListFragmentViewPager.setAdapter(mainTabFragmentPagerAdapter);
    }

    @Override
    public void showLoadBatchingError() {

    }
}
