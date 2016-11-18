package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponseMainListData;
import com.example.administrator.yisihqingdan.entity.ResponseReputation;

import java.util.List;

/**
 * Created by lenovo on 2016-10-30.
 */

public interface List_innerfragment_Model_interf {
    void loadNextPageDaters(String url,Callback callback);
    public interface Callback{
        void loadCollectionsSuccess(List<ResponseMainListData.DataBean.CollectionsBean> collections);
        void loadNodesSuccess(List<ResponseMainListData.DataBean.NodesBean> nodes);
        void loadArticlesSuccess(List<ResponseMainListData.DataBean.ArticlesBean> articles);
        void loadFailed();
        void noMoreData();
    }


    void loadReputationData(ReputationCallback callback);
    public interface ReputationCallback{
        void loadSuccess(List<ResponseReputation.DataBean.RankingsBean> rankings);
        void loadFailed();
    }
}
