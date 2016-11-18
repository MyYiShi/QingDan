package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.activity.PariseMoreActivity;
import com.example.administrator.yisihqingdan.activity.PraiseActivity;
import com.example.administrator.yisihqingdan.entity.ResponseMainListData;
import com.example.administrator.yisihqingdan.entity.ResponseReputation;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/1.
 */

public class NodesRecycleViewAdapter extends BaseMainListRecycleViewAdapter<ResponseMainListData.DataBean.NodesBean> {
    private Context context;

    public NodesRecycleViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getFooterCount() {
        return 1;
    }

    @Override
    protected int getHeaderCount() {
        return 1;
    }

    private List<ResponseReputation.DataBean.RankingsBean> rankings = new ArrayList<>();

    public void setRankings(List<ResponseReputation.DataBean.RankingsBean> rankings) {
        this.rankings.clear();
        this.rankings.addAll(rankings);
        notifyItemChanged(0);
    }

    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_HEADER = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderViewHolder(inflater.inflate(R.layout.layout_head_enter_rankings, parent, false));
        }
        if (viewType == TYPE_CONTENT) {
            return new MyViewHolder(inflater.inflate(R.layout.fragment_list_innerfragment_item, parent, false));
        }
        return new FooterViewHoder(inflater.inflate(R.layout.subview_recycleview_loadfooter, parent, false));
    }

    private List<SimpleDraweeView> simpleDraweeViews;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < getHeaderCount()){
            //设置头部数据
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            if (headerViewHolder.llItemRankingEnter.getChildCount() == 0){
                simpleDraweeViews = new ArrayList<>();
                for (final ResponseReputation.DataBean.RankingsBean ranking :rankings){
                    View view = inflater.inflate(R.layout.subview_reputation,headerViewHolder.llItemRankingEnter,false);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PraiseActivity.class);
                            intent.putExtra("rankingId",ranking.getId());
                            context.startActivity(intent);
                        }
                    });
                    simpleDraweeViews.add((SimpleDraweeView) view.findViewById(R.id.img_ranking_all_topic_enter));
                    headerViewHolder.llItemRankingEnter.addView(view);
                }
            }
            for (int i = 0; i < simpleDraweeViews.size(); i++) {
                simpleDraweeViews.get(i).setImageURI(rankings.get(i).getThumbnailImageUrl());
            }
            headerViewHolder.imgRankingAllTopicEnter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, PariseMoreActivity.class));
                }
            });
            return;
        }
        if (position >= getItemCount() - getFooterCount()){
            onBindFooterViewHolder(holder,position);
            return;
        }
        MyViewHolder mHolder = (MyViewHolder) holder;
        ResponseMainListData.DataBean.NodesBean nodesBean = getItem(position);
        mHolder.imageViewFrontTopImage.setImageURI(nodesBean.getFeaturedImageUrl());
        mHolder.textViewFrontMainTitle.setText(nodesBean.getTitle());
        mHolder.textViewFrontSubtitle.setText(nodesBean.getSubtitle());
        if (nodesBean.getCategories().size() != 0){
            mHolder.relaSpecialTag.setText(nodesBean.getCategories().get(0).getName());
        }
        mHolder.textViewFrontNumLiked.setText(nodesBean.getLikeCount() + "");
        mHolder.textViewNumReviews.setText(nodesBean.getHitCount() + "");
    }


    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_item_ranking_enter)
        LinearLayout llItemRankingEnter;
        @BindView(R.id.img_ranking_all_topic_enter)
        ImageView imgRankingAllTopicEnter;
        @BindView(R.id.hs_temp)
        HorizontalScrollView hsTemp;

        public HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
