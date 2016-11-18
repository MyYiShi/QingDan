package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponseCollection;

/**
 * Created by Yishi on 2016/11/7.
 */

public interface CollectionModel_interf {
    void loadDatas(int collection_id,CollectionCallback collectionCallback);

    public interface CollectionCallback{
        void onCollectionSuccess(ResponseCollection responseCollection);
        void onCollectionFailed();
    }
}
