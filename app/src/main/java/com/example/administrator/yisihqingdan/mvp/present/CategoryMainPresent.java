package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponseCategoryMain;
import com.example.administrator.yisihqingdan.mvp.model.CategoryMainModel;
import com.example.administrator.yisihqingdan.mvp.model.CategoryMainModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.CategoryMainView_interf;

/**
 * Created by Yishi on 2016/11/15.
 */

public class CategoryMainPresent implements CategoryMainPresent_interf{
    private CategoryMainModel_interf model;
    private CategoryMainView_interf view;

    public CategoryMainPresent(CategoryMainView_interf view) {
        this.view = view;
        this.model = new CategoryMainModel();
    }

    @Override
    public void loadDatas() {
        model.loadDatas(new CategoryMainModel_interf.OnCategoryMainCallback() {
            @Override
            public void loadSuccess(ResponseCategoryMain responseCategoryMain) {
                view.showCategoryMainSuccess(responseCategoryMain);
            }

            @Override
            public void loadFailed() {
                view.showCategoryMainFailed();
            }
        });
    }
}
