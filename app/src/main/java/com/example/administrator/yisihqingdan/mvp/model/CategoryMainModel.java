package com.example.administrator.yisihqingdan.mvp.model;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponseCategoryMain;
import com.example.administrator.yisihqingdan.http.FormBody;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;
import com.example.administrator.yisihqingdan.http.RequestBody;

/**
 * Created by Yishi on 2016/11/15.
 */

public class CategoryMainModel implements CategoryMainModel_interf{
    @Override
    public void loadDatas(final OnCategoryMainCallback onCategoryMainCallback) {
        RequestBody body = new FormBody.Builder()
                .add("requests","{\"tags\":{\"method\":\"GET\",\"relative_url\":\"/v1/tags/recommended\"},\"categories\":{\"method\":\"GET\",\"relative_url\":\"/v1/categories\"}}")
                .build();

        Request.Builder builder = new Request.Builder()
                .post(body)
                .url("http://api.eqingdan.com/v1/batching");
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseCategoryMain responseCategoryMain = JSON.parseObject(response,ResponseCategoryMain.class);
                onCategoryMainCallback.loadSuccess(responseCategoryMain);
            }

            @Override
            public void onError() {
                onCategoryMainCallback.loadFailed();
            }
        });
    }
}
