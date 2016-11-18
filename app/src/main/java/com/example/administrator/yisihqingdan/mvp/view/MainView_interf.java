package com.example.administrator.yisihqingdan.mvp.view;

import com.example.administrator.yisihqingdan.entity.ResponseBatching;

/**
 * Created by lenovo on 2016-10-30.
 */

public interface MainView_interf {
    void showBatchingData(ResponseBatching batching);
    void showLoadBatchingError();
}
