package com.example.administrator.yisihqingdan.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.ThingsDetailViewPager;
import com.example.administrator.yisihqingdan.entity.ResponsePraise;
import com.example.administrator.yisihqingdan.entity.ResponseThingsDetail;
import com.example.administrator.yisihqingdan.mvp.present.ThingsDetailPresent;
import com.example.administrator.yisihqingdan.mvp.view.ThingsDetailView_interf;
import com.example.administrator.yisihqingdan.widget.PagerDotIndicator;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/11.
 */

public class ThingsDetailActivity extends BaseActivity implements ThingsDetailView_interf {
    @BindView(R.id.textView_like_count)
    TextView textViewLikeCount;
    @BindView(R.id.layout_like_count)
    LinearLayout layoutLikeCount;
    @BindView(R.id.textView_comments_count)
    TextView textViewCommentsCount;
    @BindView(R.id.layout_comments_count)
    LinearLayout layoutCommentsCount;
    @BindView(R.id.textView_write_comment)
    TextView textViewWriteComment;
    @BindView(R.id.rela_no_comments)
    RelativeLayout relaNoComments;
    @BindView(R.id.layout_comments)
    LinearLayout layoutComments;
    @BindView(R.id.layout_container_subiew_comments)
    LinearLayout layoutContainerSubiewComments;
    @BindView(R.id.things_detail_viewpager)
    ViewPager thingsDetailViewpager;
    @BindView(R.id.textview_title_subview_title)
    TextView textviewTitleSubviewTitle;
    @BindView(R.id.imageView_back_subview_title)
    ImageView imageViewBackSubviewTitle;
    @BindView(R.id.up_viewpager_indicator)
    LinearLayout upViewpagerIndicator;
    @BindView(R.id.things_detail_text1)
    TextView thingsDetailText1;
    @BindView(R.id.things_detail_text2)
    TextView thingsDetailText2;
    @BindView(R.id.things_detail_ratingbar)
    RatingBar thingsDetailRatingbar;
    @BindView(R.id.things_detail_text3)
    TextView thingsDetailText3;
    @BindView(R.id.things_detail_description)
    RelativeLayout thingsDetailDescription;
    @BindView(R.id.things_detail_buy_text)
    TextView thingsDetailBuyText;
    @BindView(R.id.things_detail_buy_image)
    ImageView thingsDetailBuyImage;
    @BindView(R.id.things_detail_buy)
    RelativeLayout thingsDetailBuy;
    @BindView(R.id.subview_comments_writeimage)
    ImageView subviewCommentsWriteimage;

    private ThingsDetailViewPager adapter;
    private PagerDotIndicator pagerDotIndicator;
    private ThingsDetailPresent present;

    @Override
    protected int getContentResId() {
        return R.layout.things_detail;
    }


    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ResponsePraise.DataBean.ThingsBean bean = (ResponsePraise.DataBean.ThingsBean) getIntent().getSerializableExtra("bean");
        textviewTitleSubviewTitle.setText("商品详情");
        thingsDetailText1.setText(bean.getFullName().replace(bean.getName(), ""));
        thingsDetailText2.setText(bean.getName());
        thingsDetailText3.setText(this.getString(R.string.score_and_review, bean.getRatingScore() + "", bean.getReviewCount()));
        thingsDetailRatingbar.setRating(bean.getRatingScore());
        adapter = new ThingsDetailViewPager(bean.getImageUrls(), this);
        thingsDetailViewpager.setAdapter(adapter);
        thingsDetailViewpager.setCurrentItem(bean.getImageUrls().size() * 100);
        pagerDotIndicator = new PagerDotIndicator(this, upViewpagerIndicator, thingsDetailViewpager);
        pagerDotIndicator.setDotNums(bean.getImageUrls().size());
        thingsDetailBuyText.setText(bean.getPrice());
        if (bean.getDescription().equals("")) {
            thingsDetailDescription.setVisibility(View.GONE);
        }
        textViewLikeCount.setText("喜欢(" + bean.getLikeCount() + ")");
        textViewCommentsCount.setText("拥有(" + bean.getCommentCount() + ")");

        subviewCommentsWriteimage.setVisibility(View.GONE);

        //设置评论的MVP
        present = new ThingsDetailPresent(this);
        present.loadDatas(bean.getId());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showCommentsSuccess(ResponseThingsDetail responseThingsDetail) {
        textViewWriteComment.setText("全部评论（" + responseThingsDetail.getData().getMeta().getPagination().getTotal() + "）");
        layoutComments.setVisibility(View.VISIBLE);
        layoutComments.removeAllViews();//不加这一行在刷新的时候 评论条数会一直增加
        for (int i = 0; i < responseThingsDetail.getData().getMeta().getPagination().getCount(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.list_item_comment, layoutComments, false);
            ViewHolder holder = new ViewHolder(view);
            List<ResponseThingsDetail.DataBean.ReviewsBean> reviews = responseThingsDetail.getData().getReviews();
            holder.imageViewAuthorAvatar.setImageURI(reviews.get(i).getUser().getAvatarUrl());
            holder.textViewAuthorName.setText(reviews.get(i).getUser().getNickname());
            holder.textViewComments.setText(reviews.get(i).getBody());
            holder.textViewCommentTimeTag.setText(reviews.get(i).getCreatedAt());
            holder.listItemCommentRatingbar.setRating(reviews.get(i).getRatingValue());
            holder.listItemCommentShareImage.setVisibility(View.GONE);
            holder.textViewCommentLikeCount.setText(reviews.get(i).getHitCount()+"");
            layoutComments.addView(view);
        }
    }

    @Override
    public void showFailed() {

    }

    static class ViewHolder {
        @BindView(R.id.imageView_author_avatar)
        SimpleDraweeView imageViewAuthorAvatar;
        @BindView(R.id.textView_author_name)
        TextView textViewAuthorName;
        @BindView(R.id.textView_comment_time_tag)
        TextView textViewCommentTimeTag;
        @BindView(R.id.textView_comment_like_count)
        TextView textViewCommentLikeCount;
        @BindView(R.id.imageView_comment_like)
        ImageView imageViewCommentLike;
        @BindView(R.id.textView_comments)
        TextView textViewComments;
        @BindView(R.id.list_item_comment_ratingbar)
        RatingBar listItemCommentRatingbar;
        @BindView(R.id.list_item_comment_share_image)
        ImageView listItemCommentShareImage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
