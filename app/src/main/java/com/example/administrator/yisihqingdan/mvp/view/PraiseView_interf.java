package com.example.administrator.yisihqingdan.mvp.view;


import com.example.administrator.yisihqingdan.entity.ResponsePraise;

import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */

public interface PraiseView_interf {
    void showThingsToView(int page, List<ResponsePraise.DataBean.ThingsBean> things);

    void showFooterLoading();
    void showFooterLoadFailed();
    void showFooterNoMoreData();
    void showFooterNoSearchData();


}
