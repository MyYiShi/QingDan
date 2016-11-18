package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponseTagDetail;

/**
 * Created by Yishi on 2016/11/16.
 */

public interface TagDetailModel_interf {
    void loadDatas(int tagId,OnTagDetailCallback onTagDetailCallback);
    public interface OnTagDetailCallback{
        void onTagDetailSuccess(ResponseTagDetail responseTagDetail);
        void onTagDetailFailed();
    }
}
