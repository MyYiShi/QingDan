package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponseCategoryMain;

/**
 * Created by Yishi on 2016/11/15.
 */

public interface CategoryMainModel_interf {
    void loadDatas(OnCategoryMainCallback onCategoryMainCallback);
    public interface OnCategoryMainCallback{
        void loadSuccess(ResponseCategoryMain responseCategoryMain);
        void loadFailed();
    }
}
