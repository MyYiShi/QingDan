package com.example.administrator.yisihqingdan.mvp.model;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponseMainListData;
import com.example.administrator.yisihqingdan.entity.ResponseReputation;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;
import com.example.administrator.yisihqingdan.utils.Apis;

/**
 * Created by lenovo on 2016-10-30.
 */

public class List_innerfragment_Model implements List_innerfragment_Model_interf {
    @Override
    public void loadNextPageDaters(String url, final Callback callback) {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseMainListData responseMainListData = JSON.parseObject(response,ResponseMainListData.class);
                if (responseMainListData.getData().getMeta().getPagination().getTotal_pages() == responseMainListData
                        .getData().getMeta().getPagination().getCurrent_page()){
                    callback.noMoreData();
                }
                if (responseMainListData.getData().getNodes()!=null){
                    callback.loadNodesSuccess(responseMainListData.getData().getNodes());
                }
                if (responseMainListData.getData().getArticles()!=null){
                    callback.loadArticlesSuccess(responseMainListData.getData().getArticles());
                }
                if (responseMainListData.getData().getCollections()!=null){
                    callback.loadCollectionsSuccess(responseMainListData.getData().getCollections());
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void loadReputationData(final ReputationCallback callback) {
        Request.Builder builder = new Request.Builder()
                .get()
                .url(Apis.URL_REPUTATION);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseReputation responseReputation = JSON.parseObject(response,ResponseReputation.class);
                if (responseReputation.getCode() == 0){
                    callback.loadSuccess(responseReputation.getData().getRankings());
                }
            }

            @Override
            public void onError() {
                callback.loadFailed();
            }
        });
    }
}
