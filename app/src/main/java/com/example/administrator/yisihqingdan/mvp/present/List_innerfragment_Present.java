package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponseMainListData;
import com.example.administrator.yisihqingdan.entity.ResponseReputation;
import com.example.administrator.yisihqingdan.http.UrlHandler;
import com.example.administrator.yisihqingdan.mvp.model.List_innerfragment_Model;
import com.example.administrator.yisihqingdan.mvp.model.List_innerfragment_Model_interf;
import com.example.administrator.yisihqingdan.mvp.view.List_innerfragment_View_interf;

import java.util.List;

/**
 * Created by Yishi on 2016/10/31.
 */

public class List_innerfragment_Present implements List_innerfragment_Present_interf {
    private List_innerfragment_Model_interf model;
    private List_innerfragment_View_interf view;
    private int nextPage = 1;
    private String urlTag;

    public List_innerfragment_Present(List_innerfragment_View_interf view,String urlTag) {
        this.view = view;
        this.urlTag = urlTag;
        model = new List_innerfragment_Model();
    }

    @Override
    public void loadNextPageDatas() {
        String url = UrlHandler.handlUrl(urlTag,nextPage);
        model.loadNextPageDaters(url, new List_innerfragment_Model_interf.Callback() {
            @Override
            public void loadCollectionsSuccess(List<ResponseMainListData.DataBean.CollectionsBean> collections) {
                view.showCollectionsToView(collections);
            }

            @Override
            public void loadNodesSuccess(List<ResponseMainListData.DataBean.NodesBean> nodes) {
                view.showNodesToView(nodes);
            }

            @Override
            public void loadArticlesSuccess(List<ResponseMainListData.DataBean.ArticlesBean> articles) {
                view.showArticlesToView(articles);
            }

            @Override
            public void loadFailed() {
                view.showRecycleViewFooterLoadFailed();
                nextPage--;
            }

            @Override
            public void noMoreData() {
                view.showRecycleViewFooterNoMoreData();
            }
        });
        nextPage++;
    }

    @Override
    public void loadReputationData() {
        model.loadReputationData(new List_innerfragment_Model_interf.ReputationCallback(){
            @Override
            public void loadSuccess(List<ResponseReputation.DataBean.RankingsBean> rankings) {
                view.showReputation(rankings);
            }

            @Override
            public void loadFailed() {

            }
        });
    }
}
