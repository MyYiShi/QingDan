package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponsePraise;

import java.util.List;

/**
 * Created by Yishi on 2016/11/9.
 */

public interface PraiseModel_interf {
    void loadData(String url, OnPraiseCallback onPraiseCallback);

    public interface OnPraiseCallback {
        void loadSuccess(List<ResponsePraise.DataBean.ThingsBean> things);
        void loadFailed();
        void noMoreData();
        void noSearchData();
    }
}
