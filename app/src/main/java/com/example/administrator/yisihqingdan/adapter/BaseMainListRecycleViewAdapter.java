package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/1.
 */

public abstract class BaseMainListRecycleViewAdapter<T> extends BaseRecycleView<T>{

    public BaseMainListRecycleViewAdapter(Context context) {
        super(context);
    }

    public static final int STATE_LOADING = 1;
    public static final int STATE_FAILED = 2;
    public static final int STATE_NO_MORE_DATA = 3;
    private int state;
    public void updateFooterViewState(int state){
        this.state = state;
        notifyDataSetChanged();//刷新adapter
    }

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder,int position){
        FooterViewHoder footerViewHoder = (FooterViewHoder) holder;
        switch(state){
            case STATE_LOADING:
                footerViewHoder.progressbarSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                footerViewHoder.textviewSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
                break;
            case STATE_FAILED:
                footerViewHoder.progressbarSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
                footerViewHoder.textviewSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                footerViewHoder.textviewSubviewRecycleviewLoadfooter.setText("加载失败，点击重试~");
                break;
            case STATE_NO_MORE_DATA:
                footerViewHoder.progressbarSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
                footerViewHoder.textviewSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                footerViewHoder.textviewSubviewRecycleviewLoadfooter.setText("没有更多数据了");
                break;
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView_front_top_image)
        SimpleDraweeView imageViewFrontTopImage;
        @BindView(R.id.view_temp)
        View viewTemp;
        @BindView(R.id.textView_front_main_title)
        TextView textViewFrontMainTitle;
        @BindView(R.id.textView_front_subtitle)
        TextView textViewFrontSubtitle;
        @BindView(R.id.imageView_front_like)
        ImageView imageViewFrontLike;
        @BindView(R.id.textView_front_num_liked)
        TextView textViewFrontNumLiked;
        @BindView(R.id.textView_num_reviews)
        TextView textViewNumReviews;
        @BindView(R.id.linear_temp)
        LinearLayout linearTemp;
        @BindView(R.id.rotate_textView_date)
        TextView rotateTextViewDate;
        @BindView(R.id.rela_special_tag)
        TextView relaSpecialTag;
        @BindView(R.id.linear_bottom_count)
        LinearLayout linearBottomCount;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickPosition = getAdapterPosition();
                    if (onItemClickListen != null){
                        onItemClickListen.onItemClick(v,clickPosition);
                    }
                }
            });
        }
    }


    public class FooterViewHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.progressbar_subview_recycleview_loadfooter)
        ProgressBar progressbarSubviewRecycleviewLoadfooter;
        @BindView(R.id.textview_subview_recycleview_loadfooter)
        TextView textviewSubviewRecycleviewLoadfooter;
        @BindView(R.id.layout_subview_recycleview_loadfooter)
        RelativeLayout layoutSubviewRecycleviewLoadfooter;

        FooterViewHoder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            textviewSubviewRecycleviewLoadfooter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRetryClickListener != null){
                        onRetryClickListener.onRetryClick();
                    }
                }
            });
        }
    }

    //失败重试按钮的监听
    public interface OnRetryClickListener{
        void onRetryClick();
    }
    private OnRetryClickListener onRetryClickListener;
    public void setOnRetryClickListener(OnRetryClickListener onRetryClickListener){
        this.onRetryClickListener = onRetryClickListener;
    }

    //item单击监听
    public interface OnItemClickListen{
        void onItemClick(View view,int position);
    }
    private OnItemClickListen onItemClickListen;
    public void setOnItemClickListen(OnItemClickListen onItemClickListen){
        this.onItemClickListen = onItemClickListen;
    }
}
