package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponseAllCategories;
import com.example.administrator.yisihqingdan.mvp.model.AllCategoriesModel;
import com.example.administrator.yisihqingdan.mvp.model.AllCategoriesModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.AllCategoriesView_interf;

/**
 * Created by Yishi on 2016/11/16.
 */

public class AllCategoriesPresent implements AllCategoriesPresent_interf{
    private AllCategoriesModel_interf model;
    private AllCategoriesView_interf view;

    public AllCategoriesPresent(AllCategoriesView_interf view) {
        this.view = view;
        this.model = new AllCategoriesModel();
    }

    @Override
    public void loadDatas() {
        model.loadDatas(new AllCategoriesModel_interf.OnAllCategoriesCallback() {
            @Override
            public void OnAllCategoriesLoadSuccess(ResponseAllCategories responseAllCategories) {
                view.showAllCategotiesSuccess(responseAllCategories);
            }

            @Override
            public void OnAllCategoriesLoadFailed() {

            }
        });
    }
}
