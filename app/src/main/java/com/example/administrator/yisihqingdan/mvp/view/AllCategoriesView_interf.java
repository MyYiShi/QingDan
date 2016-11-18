package com.example.administrator.yisihqingdan.mvp.view;

import com.example.administrator.yisihqingdan.entity.ResponseAllCategories;

/**
 * Created by Yishi on 2016/11/16.
 */

public interface AllCategoriesView_interf {
    void showAllCategotiesSuccess(ResponseAllCategories responseAllCategories);
    void showAllCategotiesFailed();
}
