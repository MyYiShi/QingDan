package com.example.administrator.yisihqingdan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.activity.ArticleDetailActivity;
import com.example.administrator.yisihqingdan.activity.CollectionActivity;
import com.example.administrator.yisihqingdan.adapter.ArticlesRecycleViewAdapter;
import com.example.administrator.yisihqingdan.adapter.BaseMainListRecycleViewAdapter;
import com.example.administrator.yisihqingdan.adapter.CollectionsRecycleViewAdapter;
import com.example.administrator.yisihqingdan.adapter.NodesRecycleViewAdapter;
import com.example.administrator.yisihqingdan.entity.ResponseMainListData;
import com.example.administrator.yisihqingdan.entity.ResponseReputation;
import com.example.administrator.yisihqingdan.mvp.present.List_innerfragment_Present;
import com.example.administrator.yisihqingdan.mvp.present.List_innerfragment_Present_interf;
import com.example.administrator.yisihqingdan.mvp.view.List_innerfragment_View_interf;
import com.example.administrator.yisihqingdan.utils.Contants;

import java.util.List;

/**
 * Created by Yishi on 2016/10/31.
 */

public class List_fragment_innerfragment extends Base_fragment implements List_innerfragment_View_interf, BaseMainListRecycleViewAdapter.OnItemClickListen, View.OnClickListener {
    /***
     * 代表是哪一种数据类型
     **/
    private int dataCategoryTag;
    /**
     * 代表访问数据的接口
     **/
    private String urlTag;

    private List_innerfragment_Present_interf presenter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private BaseMainListRecycleViewAdapter recycleViewAdapter;
    private FloatingActionButton button;

    public static List_fragment_innerfragment newInstance(int dataCategoryTag,String urlTag) {
        List_fragment_innerfragment fragment = new List_fragment_innerfragment();
        Bundle bundle = new Bundle();
        bundle.putInt("dataCategoryTag", dataCategoryTag);
        bundle.putString("urlTag", urlTag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);

        Bundle bundle = getArguments();
        dataCategoryTag = bundle.getInt("dataCategoryTag");
        urlTag = bundle.getString("urlTag");

        presenter = new List_innerfragment_Present(this,urlTag);

        //根据数据的类型 来创建对应的Adapter
        switch (dataCategoryTag){
            case Contants.TAG_NODES:
                presenter.loadReputationData();
                recycleViewAdapter = new NodesRecycleViewAdapter(getActivity());
                break;
            case Contants.TAG_COLLECTIONS:
                recycleViewAdapter = new CollectionsRecycleViewAdapter(getActivity());
                break;
            case Contants.TAG_ARTICLES:
                recycleViewAdapter = new ArticlesRecycleViewAdapter(getActivity());
                break;
        }

        recycleViewAdapter.setOnRetryClickListener(new BaseMainListRecycleViewAdapter.OnRetryClickListener() {
            @Override
            public void onRetryClick() {
                loadNextDatas();
            }
        });
        recycleViewAdapter.setOnItemClickListen(this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.addOnScrollListener(onScrollListener);
        presenter.loadNextPageDatas();
        button = (FloatingActionButton) getView().findViewById(R.id.fab_fragment_main_list);
        initFab();
    }

    private void initFab() {
        button.setOnClickListener(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager.findFirstVisibleItemPosition() == 0){
                    button.hide();
                    return;
                }
                if (dy > 0 && button.getVisibility() == View.VISIBLE){
                    button.hide();
                }else if (dy < 0 && button.getVisibility() != View.VISIBLE){
                    button.show();
                }
            }
        });
    }


    private void loadNextDatas() {
        presenter.loadNextPageDatas();
        isLoading = true;
    }

    @Override
    protected int getContentResId() {
        return R.layout.fragment_list_innerfragment;
    }

    @Override
    public void showNodesToView(List<ResponseMainListData.DataBean.NodesBean> nodes) {
        recycleViewAdapter.addDatas(nodes);
        isLoading = false;

    }

    @Override
    public void showArticlesToView(List<ResponseMainListData.DataBean.ArticlesBean> articles) {
        recycleViewAdapter.addDatas(articles);
        isLoading = false;
    }

    @Override
    public void showCollectionsToView(List<ResponseMainListData.DataBean.CollectionsBean> collections) {
        recycleViewAdapter.addDatas(collections);
        isLoading = false;
    }

    @Override
    public void showRecycleViewFooterLoading() {
        recycleViewAdapter.updateFooterViewState(BaseMainListRecycleViewAdapter.STATE_LOADING);
    }

    @Override
    public void showRecycleViewFooterLoadFailed() {
        isLoading = false;
        recycleViewAdapter.updateFooterViewState(BaseMainListRecycleViewAdapter.STATE_FAILED);
    }

    @Override
    public void showRecycleViewFooterNoMoreData() {
        isNoMoreDate = true;
        recycleViewAdapter.updateFooterViewState(BaseMainListRecycleViewAdapter.STATE_NO_MORE_DATA);
    }

    @Override
    public void showReputation(List<ResponseReputation.DataBean.RankingsBean> rankings) {
        if (recycleViewAdapter instanceof NodesRecycleViewAdapter){
            NodesRecycleViewAdapter nodesRecycleViewAdapter = (NodesRecycleViewAdapter) recycleViewAdapter;
            nodesRecycleViewAdapter.setRankings(rankings);
        }
    }

    private boolean isNoMoreDate;
    private boolean isLoading;
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (isNoMoreDate){
                return;
            }
            if (!isLoading && layoutManager.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1){
                isLoading = true;
                presenter.loadNextPageDatas();
            }
        }
    };

    @Override
    public void onItemClick(View view, int position) {
        if (dataCategoryTag == Contants.TAG_NODES){
            ResponseMainListData.DataBean.NodesBean nodesBean
                    = (ResponseMainListData.DataBean.NodesBean) recycleViewAdapter.getItem(position);
            Intent intent = null;
            if (nodesBean.getCategories().size() == 0){
                intent = new Intent(getActivity(),CollectionActivity.class);
                intent.putExtra("collection_id",nodesBean.getTarget_id());
                startActivity(intent);
                return;
            }
            intent = new Intent(getActivity(),ArticleDetailActivity.class);
            intent.putExtra("articleId",nodesBean.getTarget_id());
            startActivity(intent);
        }else if (dataCategoryTag == Contants.TAG_ARTICLES){
            ResponseMainListData.DataBean.ArticlesBean articlesBean
                    = (ResponseMainListData.DataBean.ArticlesBean) recycleViewAdapter.getItem(position);
            Intent intent = new Intent(getActivity(),ArticleDetailActivity.class);
            intent.putExtra("articleId",articlesBean.getId());
            startActivity(intent);
        }else if (dataCategoryTag == Contants.TAG_COLLECTIONS){
            ResponseMainListData.DataBean.CollectionsBean collectionsBean
                    = (ResponseMainListData.DataBean.CollectionsBean) recycleViewAdapter.getItem(position);
            Intent intent = new Intent(getActivity(),CollectionActivity.class);
            intent.putExtra("collection_id",collectionsBean.get_id());
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == button){
            if (layoutManager.findLastVisibleItemPosition() < 10){
                recyclerView.smoothScrollToPosition(0);
            }else{
                recyclerView.scrollToPosition(0);
            }
        }
    }
}
