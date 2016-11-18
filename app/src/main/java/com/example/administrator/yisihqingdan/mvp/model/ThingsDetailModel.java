package com.example.administrator.yisihqingdan.mvp.model;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponseThingsDetail;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;

/**
 * Created by Yishi on 2016/11/14.
 */

public class ThingsDetailModel implements ThingsDetailModel_interf{
    @Override
    public void loadDatas(String url, final OnThingsDetaiCallback onThingsDetaiCallback) {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseThingsDetail responseThingsDetail = JSON.parseObject(response,ResponseThingsDetail.class);
                onThingsDetaiCallback.onThingsDetailSuccess(responseThingsDetail);
            }

            @Override
            public void onError() {

            }
        });
    }
}
