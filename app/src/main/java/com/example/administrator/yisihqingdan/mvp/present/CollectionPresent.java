package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponseCollection;
import com.example.administrator.yisihqingdan.mvp.model.CollectionModel;
import com.example.administrator.yisihqingdan.mvp.model.CollectionModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.CollectionView_interf;

/**
 * Created by Yishi on 2016/11/7.
 */

public class CollectionPresent implements CollectionPresent_interf{
    private CollectionModel_interf model;
    private CollectionView_interf view;
    private int collection_id;

    public CollectionPresent(CollectionView_interf view,int collection_id) {
        model = new CollectionModel();
        this.view = view;
        this.collection_id = collection_id;
    }

    @Override
    public void loadCollectionDatas() {
        model.loadDatas(collection_id, new CollectionModel_interf.CollectionCallback() {
            @Override
            public void onCollectionSuccess(ResponseCollection responseCollection) {
                view.showCollectionDatas(responseCollection);
            }

            @Override
            public void onCollectionFailed() {
                view.showCollentionFailed();
            }
        });
    }
}
