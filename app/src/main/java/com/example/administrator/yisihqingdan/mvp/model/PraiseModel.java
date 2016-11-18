package com.example.administrator.yisihqingdan.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponsePraise;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;

/**
 * Created by Yishi on 2016/11/9.
 */

public class PraiseModel implements PraiseModel_interf{
    @Override
    public void loadData(String url, final OnPraiseCallback onPraiseCallback) {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponsePraise responsePraise = JSON.parseObject(response,ResponsePraise.class);
                onPraiseCallback.loadSuccess(responsePraise.getData().getThings());

                if (responsePraise.getData().getMeta().getPagination().getTotal_pages() == 0){
                    onPraiseCallback.noSearchData();
                    return;
                }
                if (responsePraise.getData().getMeta().getPagination().getTotal_pages() ==
                        responsePraise.getData().getMeta().getPagination().getCurrent_page()){
                    onPraiseCallback.noMoreData();
                    return;
                }
            }

            @Override
            public void onError() {
                onPraiseCallback.loadFailed();
            }
        });
    }
}
