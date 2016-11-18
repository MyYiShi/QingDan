package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponseTagDetail;
import com.example.administrator.yisihqingdan.mvp.model.TagDetailModel;
import com.example.administrator.yisihqingdan.mvp.model.TagDetailModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.TagDetailView_interf;

/**
 * Created by Yishi on 2016/11/16.
 */

public class TagDetailPresent implements TagDetailPresent_interf{
    private TagDetailModel_interf model;
    private TagDetailView_interf view;

    public TagDetailPresent(TagDetailView_interf view) {
        this.view = view;
        model = new TagDetailModel();
    }

    @Override
    public void loadDatas(int tagId) {
        model.loadDatas(tagId, new TagDetailModel_interf.OnTagDetailCallback() {
            @Override
            public void onTagDetailSuccess(ResponseTagDetail responseTagDetail) {
                view.showTagDetailSuccess(responseTagDetail);
            }

            @Override
            public void onTagDetailFailed() {
                view.showTagDetailFailed();
            }
        });
    }
}
