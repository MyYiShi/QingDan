package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponseThingsDetail;

/**
 * Created by Yishi on 2016/11/14.
 */

public interface ThingsDetailModel_interf {
    void loadDatas(String url,OnThingsDetaiCallback onThingsDetaiCallback);

    public interface OnThingsDetaiCallback{
        void onThingsDetailSuccess(ResponseThingsDetail responseThingsDetail);
    }
}
