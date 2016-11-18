package com.example.administrator.yisihqingdan.mvp.view;

import com.example.administrator.yisihqingdan.entity.ResponseMainListData;
import com.example.administrator.yisihqingdan.entity.ResponseReputation;

import java.util.List;

/**
 * Created by Yishi on 2016/10/31.
 */

public interface List_innerfragment_View_interf {
    void showNodesToView(List<ResponseMainListData.DataBean.NodesBean> nodes);
    void showArticlesToView(List<ResponseMainListData.DataBean.ArticlesBean> articles);
    void showCollectionsToView(List<ResponseMainListData.DataBean.CollectionsBean> collections);

    void showRecycleViewFooterLoading();
    void showRecycleViewFooterLoadFailed();
    void showRecycleViewFooterNoMoreData();

    void showReputation(List<ResponseReputation.DataBean.RankingsBean> rankings);
}
