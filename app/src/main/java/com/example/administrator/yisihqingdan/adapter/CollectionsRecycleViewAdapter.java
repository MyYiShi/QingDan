package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.entity.ResponseMainListData;

/**
 * Created by Yishi on 2016/11/1.
 */

public class CollectionsRecycleViewAdapter extends BaseMainListRecycleViewAdapter<ResponseMainListData.DataBean.CollectionsBean>{
    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_FOOTER = 1;

    public CollectionsRecycleViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getFooterCount() {
        return 1;
    }

    @Override
    protected int getHeaderCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CONTENT) {
            return new MyViewHolder(inflater.inflate(R.layout.fragment_list_innerfragment_item, parent, false));
        }
        return new FooterViewHoder(inflater.inflate(R.layout.subview_recycleview_loadfooter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position < getHeaderCount()){
            return;
        }
        if(position >= getItemCount()-getFooterCount()){
            onBindFooterViewHolder(holder,position);
            return;
        }
        MyViewHolder mHolder = (MyViewHolder) holder;
        ResponseMainListData.DataBean.CollectionsBean nodesBean = getItem(position);
        mHolder.imageViewFrontTopImage.setImageURI(nodesBean.getFeaturedImageUrl());
        mHolder.textViewFrontMainTitle.setText(nodesBean.getTitle());
        mHolder.textViewFrontSubtitle.setText(nodesBean.getSubtitle());
        mHolder.linearBottomCount.setVisibility(View.GONE);
    }
}
