package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponseAllCategories;

/**
 * Created by Yishi on 2016/11/15.
 */

public interface AllCategoriesModel_interf {
    void loadDatas(OnAllCategoriesCallback onAllCategoriesCallback);
    public interface OnAllCategoriesCallback{
        void OnAllCategoriesLoadSuccess(ResponseAllCategories responseAllCategories);
        void OnAllCategoriesLoadFailed();
    }
}
