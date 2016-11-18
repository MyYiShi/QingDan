package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponsePraisemore;

/**
 * Created by Yishi on 2016/11/8.
 */

public interface PraisemoreModel_interf {
    void loadDatas(String url,PraisemoreCallback praisemoreCallback);

    public interface PraisemoreCallback{
        void onPraismoreSuccess(ResponsePraisemore responsePraisemore);
        void onPraismoreFailed();
    }
}
