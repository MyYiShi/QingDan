package com.example.administrator.yisihqingdan.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponsePraisemore;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;

/**
 * Created by Yishi on 2016/11/8.
 */

public class PraisemoreModel implements PraisemoreModel_interf{
    @Override
    public void loadDatas(String url, final PraisemoreCallback praisemoreCallback) {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {

                Log.d("msgn", response);

                ResponsePraisemore responsePraisemore = JSON.parseObject(response,ResponsePraisemore.class);
                praisemoreCallback.onPraismoreSuccess(responsePraisemore);
            }

            @Override
            public void onError() {
                praisemoreCallback.onPraismoreFailed();
            }
        });
    }
}
