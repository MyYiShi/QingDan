package com.example.administrator.yisihqingdan.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponseCollection;
import com.example.administrator.yisihqingdan.http.FormBody;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;
import com.example.administrator.yisihqingdan.http.RequestBody;

/**
 * Created by Yishi on 2016/11/7.
 */

public class CollectionModel implements CollectionModel_interf{
    @Override
    public void loadDatas(int collection_id,final CollectionCallback collectionCallback) {
        RequestBody body = new FormBody.Builder()
                .add("requests","{\"collections\":{\"method\":\"GET\",\"relative_url\":\"/v1/collections/"+collection_id+"/articles\"},\"collection\":{\"method\":\"GET\",\"relative_url\":\"/v1/collections/"+collection_id+"\"}}")
                .build();
        Request.Builder builder = new Request.Builder()
                .url("http://api.eqingdan.com/v1/batching")
                .post(body)
                .addHeader("qd-manufacturer", "Xiaomi")
                .addHeader("qd-model", "MI 5")
                .addHeader("qd-os", "Android")
                .addHeader("qd-os-version", "6.0")
                .addHeader("qd-screen-width", "1080")
                .addHeader("qd-screen-height", "1920")
                .addHeader("qd-carrier", "%E4%B8%AD%E5%9B%BD%E8%81%94%E9%80%9A")
                .addHeader("qd-network-type", "WIFI")
                .addHeader("qd-app-id", "com.eqingdan")
                .addHeader("qd-app-version", "2.6")
                .addHeader("qd-app-channel", "mi")
                .addHeader("qd-track-device-id", "eb51c9b1f01ac05c32170fc4cf18d0e7")
                .addHeader("User-Agent", "EQingDan/2.5 (Android; okhttp/2.4.0)");
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {

                Log.d("msgn", response);

                ResponseCollection responseCollection = JSON.parseObject(response,ResponseCollection.class);
                collectionCallback.onCollectionSuccess(responseCollection);
            }

            @Override
            public void onError() {
                collectionCallback.onCollectionFailed();
            }
        });
    }
}
