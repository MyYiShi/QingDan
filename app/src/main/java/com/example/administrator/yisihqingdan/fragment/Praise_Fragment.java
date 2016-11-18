package com.example.administrator.yisihqingdan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.PraiseAdapter;
import com.example.administrator.yisihqingdan.entity.ResponsePraise;
import com.example.administrator.yisihqingdan.mvp.present.PraisePresent;
import com.example.administrator.yisihqingdan.mvp.view.PraiseView_interf;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/9.
 */
public class Praise_Fragment extends Base_fragment implements PraiseView_interf {

    @BindView(R.id.recycleView_ranking_thing)
    RecyclerView recycleViewRankingThing;
    @BindView(R.id.refreshLayout)
    MaterialRefreshLayout refreshLayout;
    private int rankingId;
    private int sortTag;
    private boolean isLoading;


    public static Praise_Fragment newInstance(int rankingId, int sortTag){
        Praise_Fragment fragment = new Praise_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("rankingId", rankingId);
        bundle.putInt("sortTag", sortTag);
        fragment.setArguments(bundle);
        return fragment;
    }

    private LinearLayoutManager layoutManager;
    private PraiseAdapter adapter;
    private PraisePresent present;
    private String searchKey = "";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        rankingId = bundle.getInt("rankingId");
        sortTag = bundle.getInt("sortTag");
        present = new PraisePresent(this, sortTag, rankingId);
        present.loadNextData(searchKey);


        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleViewRankingThing.setLayoutManager(layoutManager);

        adapter = new PraiseAdapter(getActivity());
        recycleViewRankingThing.setAdapter(adapter);

        /**
         * 加载下一页的监听
         */
        recycleViewRankingThing.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading && layoutManager.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1){
                    present.loadNextData(searchKey);
                    isLoading = true;
                }
            }
        });

        /**
         * 设置下来刷新的监听
         */
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                adapter.updatefooter(adapter.TYPE_NOR);
                adapter.clearDatas();
                present.refreshData(searchKey);
            }
        });

    }

    @Override
    protected int getContentResId() {
        return R.layout.fragment_praise;
    }

    @Override
    public void showThingsToView(int page, List<ResponsePraise.DataBean.ThingsBean> things) {
        isLoading = false;
        adapter.addDatas(things);
        adapter.updatefooter(adapter.TYPE_NOR);
        refreshLayout.finishRefresh();

    }

    @Override
    public void showFooterLoading() {

    }

    @Override
    public void showFooterLoadFailed() {

    }

    @Override
    public void showFooterNoMoreData() {
        adapter.updatefooter(adapter.TYPE_NOMORE);
    }

    @Override
    public void showFooterNoSearchData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void setSearchKey(String searchKey){
        this.searchKey = searchKey;
    }


    public void showFragment(String searchKey) {
        if (searchKey.equals(this.searchKey)){
            return;
        }
        adapter.updatefooter(adapter.TYPE_NOR);
        adapter.clearDatas();
        this.searchKey = searchKey;
        present.refreshData(searchKey);
    }
}
