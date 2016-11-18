package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponseBatching;
import com.example.administrator.yisihqingdan.mvp.model.MainModel;
import com.example.administrator.yisihqingdan.mvp.model.MainModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.MainView_interf;

/**
 * Created by lenovo on 2016-10-30.
 */

public class MainPresenter implements MainPresenter_interf {
    private MainModel_interf mainModel;
    private MainView_interf mainView;

    public MainPresenter(MainView_interf mainView) {
        this.mainModel = new MainModel();
        this.mainView = mainView;
    }

    @Override
    public void loadBatching() {
        mainModel.loadDatas(new MainModel_interf.OnBatchingLoadCallback() {
            @Override
            public void onBatchingLoadSuccess(ResponseBatching batching) {
                mainView.showBatchingData(batching);
            }

            @Override
            public void onBatchingLoadFailed() {
                mainView.showLoadBatchingError();
            }
        });
    }
}
