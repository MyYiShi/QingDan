package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponseBatching;

/**
 * Created by lenovo on 2016-10-30.
 */

public interface MainModel_interf {
    void loadDatas(OnBatchingLoadCallback callback);
    public interface OnBatchingLoadCallback{
        void onBatchingLoadSuccess(ResponseBatching batching);
        void onBatchingLoadFailed();
    }
}
