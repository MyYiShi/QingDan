package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponsePraisemore;
import com.example.administrator.yisihqingdan.http.UrlHandler;
import com.example.administrator.yisihqingdan.mvp.model.PraisemoreModel;
import com.example.administrator.yisihqingdan.mvp.model.PraisemoreModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.PraisemoreView_interf;

/**
 * Created by Yishi on 2016/11/8.
 */

public class PraisemorePresent implements PraisemorePresent_interf {
    private PraisemoreModel_interf model;
    private PraisemoreView_interf view;
    private String urlTag;
    private int nextPage = 1;

    public PraisemorePresent(String urlTag,PraisemoreView_interf view) {
        model = new PraisemoreModel();
        this.view = view;
        this.urlTag = urlTag;
    }

    @Override
    public void loadParisemoreDatas() {
        String url = UrlHandler.handlUrl(urlTag,nextPage);
        model.loadDatas(url,new PraisemoreModel_interf.PraisemoreCallback() {
            @Override
            public void onPraismoreSuccess(ResponsePraisemore responsePraisemore) {
                view.showParisemoreDatasSuccess(responsePraisemore);
                nextPage++;
            }

            @Override
            public void onPraismoreFailed() {

            }
        });
    }
}
