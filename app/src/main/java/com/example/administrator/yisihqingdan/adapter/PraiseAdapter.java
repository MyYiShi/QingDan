package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.activity.ThingsDetailActivity;
import com.example.administrator.yisihqingdan.entity.ResponsePraise;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/9.
 */
public class PraiseAdapter extends BaseRecycleView<ResponsePraise.DataBean.ThingsBean> {
    private Context context;

    public PraiseAdapter(Context context) {
        super(context);
        this.context = context;
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_CONTENT) {
            return new PraiseViewHolder(inflater.inflate(R.layout.praise_recycleview_item, parent, false));
        }
        return new PraiseViewFooterHolder(inflater.inflate(R.layout.praise_recycleview_item_footer, parent, false));
    }


    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_FOOTER = 1;


    /**
     * 判断footer的形态
     */
    private int statie;
    public static final int TYPE_NOR = 0;
    public static final int TYPE_NOMORE = 1;

    public void updatefooter(int statie) {
        this.statie = statie;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position >= getItemCount() - getFooterCount()) {
            PraiseViewFooterHolder footerHolder = (PraiseViewFooterHolder) holder;
            switch (statie) {
                case TYPE_NOR:
                    if (position == 0) {
                        footerHolder.praiseItemFooterProgressbar.setVisibility(View.GONE);
                        footerHolder.praiseItemFooterText.setVisibility(View.GONE);
                        footerHolder.praiseRecycleviewItemFooterLl.setVisibility(View.GONE);
                        return;
                    }
                    footerHolder.praiseItemFooterProgressbar.setVisibility(View.VISIBLE);
                    footerHolder.praiseItemFooterText.setVisibility(View.GONE);
                    footerHolder.praiseRecycleviewItemFooterLl.setVisibility(View.GONE);
                    break;
                case TYPE_NOMORE:
                    footerHolder.praiseItemFooterProgressbar.setVisibility(View.GONE);
                    footerHolder.praiseItemFooterText.setVisibility(View.VISIBLE);
                    footerHolder.praiseRecycleviewItemFooterLl.setVisibility(View.VISIBLE);
                    break;
            }
            return;
        }
        PraiseViewHolder viewHolder = (PraiseViewHolder) holder;
        final ResponsePraise.DataBean.ThingsBean bean = getItem(position);
        viewHolder.imgItemRankingThingCover.setImageURI(bean.getFeaturedImageUrl());
        viewHolder.tvItemRankingThingName.setText(bean.getFullName().replace(bean.getName(), ""));
        viewHolder.tvItemRankingThingTitle.setText(bean.getName());
        viewHolder.tvScoreNum.setText(  context.getString(R.string.score_and_review, bean.getRatingScore() + "", bean.getReviewCount()));
        viewHolder.ratingScore.setRating(bean.getRatingScore());

        viewHolder.praiserecycleviewitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ThingsDetailActivity.class);
                intent.putExtra("bean",bean);
                context.startActivity(intent);
            }
        });
    }



    static class PraiseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item_ranking_thing_cover)
        SimpleDraweeView imgItemRankingThingCover;
        @BindView(R.id.tv_item_ranking_thing_name)
        TextView tvItemRankingThingName;
        @BindView(R.id.tv_item_ranking_thing_title)
        TextView tvItemRankingThingTitle;
        @BindView(R.id.img_review)
        ImageView imgReview;
        @BindView(R.id.tv_review_btn)
        TextView tvReviewBtn;
        @BindView(R.id.ll_review)
        LinearLayout llReview;
        @BindView(R.id.rating_score)
        RatingBar ratingScore;
        @BindView(R.id.tv_score_num)
        TextView tvScoreNum;
        @BindView(R.id.praise_recycleview_item)
        RelativeLayout praiserecycleviewitem;


        public PraiseViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static class PraiseViewFooterHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.praise_item_footer_text)
        TextView praiseItemFooterText;
        @BindView(R.id.praise_recycleview_item_footer_ll)
        LinearLayout praiseRecycleviewItemFooterLl;
        @BindView(R.id.praise_item_footer_progressbar)
        ProgressBar praiseItemFooterProgressbar;
        @BindView(R.id.praise_item_footer)
        LinearLayout praiseItemFooter;

        PraiseViewFooterHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
