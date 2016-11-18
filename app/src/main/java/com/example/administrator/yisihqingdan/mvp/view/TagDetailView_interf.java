package com.example.administrator.yisihqingdan.mvp.view;

import com.example.administrator.yisihqingdan.entity.ResponseTagDetail;

/**
 * Created by Yishi on 2016/11/16.
 */

public interface TagDetailView_interf {
    void showTagDetailSuccess(ResponseTagDetail responseTagDetail);
    void showTagDetailFailed();
}
