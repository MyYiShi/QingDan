package com.example.administrator.yisihqingdan.mvp.present;

import android.text.TextUtils;

import com.example.administrator.yisihqingdan.entity.ResponsePraise;
import com.example.administrator.yisihqingdan.http.UrlHandler;
import com.example.administrator.yisihqingdan.mvp.model.PraiseModel;
import com.example.administrator.yisihqingdan.mvp.model.PraiseModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.PraiseView_interf;
import com.example.administrator.yisihqingdan.utils.Apis;
import com.example.administrator.yisihqingdan.utils.Contants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Yishi on 2016/11/9.
 */

public class PraisePresent implements PraisePresent_interf, PraiseModel_interf.OnPraiseCallback {
    private PraiseModel_interf model;
    private PraiseView_interf view;
    private int sortTag;
    private int nextPage = 1;
    private int rankingId;

    public PraisePresent(PraiseView_interf view,int sortTag,int rankingId) {
        this.model = new PraiseModel();
        this.view = view;
        this.sortTag = sortTag;
        this.rankingId = rankingId;
    }

    @Override
    public void loadNextData(String key) {
        if(!TextUtils.isEmpty(key)){
            try {
                key = URLEncoder.encode(key,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else {
            key = "";
        }
        String url = "";
        switch (sortTag){
            case Contants.SORT_BY_REVIEW_COUNT:
                url = UrlHandler.handlUrl(Apis.API_REPUTATION_THING_SORT_BY_REVIEW_COUNT,rankingId,key,nextPage);
                break;
            case Contants.SORT_BY_RATING_SCORE:
                url = UrlHandler.handlUrl(Apis.API_REPUTATION_THING_SORT_BY_rating_score,rankingId,key,nextPage);
                break;
            case Contants.SORT_BY_BRAND_NAME:
                url = UrlHandler.handlUrl(Apis.API_REPUTATION_THING_SORT_BY_BRAND_NAME,rankingId,key,nextPage);
                break;
        }
        model.loadData(url,this);
        view.showFooterLoading();
    }

    @Override
    public void refreshData(String key) {
        nextPage = 1;
        loadNextData(key);
    }

    @Override
    public void loadSuccess(List<ResponsePraise.DataBean.ThingsBean> things) {
        view.showThingsToView(nextPage,things);
        nextPage++;
    }

    @Override
    public void loadFailed() {

    }

    @Override
    public void noMoreData() {
        view.showFooterNoMoreData();
    }

    @Override
    public void noSearchData() {

    }
}
