package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponseThingsDetail;
import com.example.administrator.yisihqingdan.http.UrlHandler;
import com.example.administrator.yisihqingdan.mvp.model.ThingsDetailModel;
import com.example.administrator.yisihqingdan.mvp.model.ThingsDetailModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.ThingsDetailView_interf;
import com.example.administrator.yisihqingdan.utils.Apis;

/**
 * Created by Yishi on 2016/11/14.
 */

public class ThingsDetailPresent implements ThingsDetailPresent_interf{
    private ThingsDetailModel_interf model;
    private ThingsDetailView_interf view;

    public ThingsDetailPresent(ThingsDetailView_interf view) {
        model = new ThingsDetailModel();
        this.view = view;
    }

    @Override
    public void loadDatas(int thingsId) {
        String url = UrlHandler.handlUrl(Apis.API_REPUTATION_THINGS_DETAIL,thingsId);
        model.loadDatas(url, new ThingsDetailModel_interf.OnThingsDetaiCallback() {
            @Override
            public void onThingsDetailSuccess(ResponseThingsDetail responseThingsDetail) {
                view.showCommentsSuccess(responseThingsDetail);
            }
        });
    }
}
