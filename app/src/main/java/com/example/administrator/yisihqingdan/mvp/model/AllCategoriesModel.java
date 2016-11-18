package com.example.administrator.yisihqingdan.mvp.model;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponseAllCategories;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;

/**
 * Created by Yishi on 2016/11/15.
 */

public class AllCategoriesModel implements AllCategoriesModel_interf{
    @Override
    public void loadDatas(final OnAllCategoriesCallback onAllCategoriesCallback) {
        Request.Builder builder = new Request.Builder()
                .url("http://api.eqingdan.com/v1/categories?depth=3")
                .get();
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseAllCategories responseAllCategories = JSON.parseObject(response,ResponseAllCategories.class);
                onAllCategoriesCallback.OnAllCategoriesLoadSuccess(responseAllCategories);
            }

            @Override
            public void onError() {
                onAllCategoriesCallback.OnAllCategoriesLoadFailed();
            }
        });
    }
}
