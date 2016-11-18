package com.example.administrator.yisihqingdan.mvp.model;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponseTagDetail;
import com.example.administrator.yisihqingdan.http.FormBody;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;
import com.example.administrator.yisihqingdan.http.RequestBody;

/**
 * Created by Yishi on 2016/11/16.
 */

public class TagDetailModel implements TagDetailModel_interf{
    @Override
    public void loadDatas(int tagId, final OnTagDetailCallback onTagDetailCallback) {
        RequestBody body = new FormBody.Builder()
                .add("requests","{\"tagDetailsArticles\":{\"method\":\"GET\",\"relative_url\":\"/v1/tags/2782/articles?sortBy\\u003ddefault\\u0026page\\u003d1\"},\"tagDetailsThings\":{\"method\":\"GET\",\"relative_url\":\"/v1/tags/"+tagId+"/things?sortBy\\u003dnewest\\u0026page\\u003d1\"},\"tagDetailsRelatedTags\":{\"method\":\"GET\",\"relative_url\":\"/v1/tags/"+tagId+"?include\\u003drelatedTags\"}}")
                .build();
        Request.Builder builder = new Request.Builder()
                .url("http://api.eqingdan.com/v1/batching")
                .post(body);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseTagDetail responseTagDetail = JSON.parseObject(response,ResponseTagDetail.class);
                onTagDetailCallback.onTagDetailSuccess(responseTagDetail);
            }

            @Override
            public void onError() {
                onTagDetailCallback.onTagDetailFailed();
            }
        });
    }
}
