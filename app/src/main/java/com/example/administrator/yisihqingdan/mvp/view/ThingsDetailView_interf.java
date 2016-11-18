package com.example.administrator.yisihqingdan.mvp.view;

import com.example.administrator.yisihqingdan.entity.ResponseThingsDetail;

/**
 * Created by Yishi on 2016/11/14.
 */

public interface ThingsDetailView_interf {
    void showCommentsSuccess(ResponseThingsDetail responseThingsDetail);
    void showFailed();
}
