package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.entity.ResponseCollection;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/7.
 */

public class CollectionAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ResponseCollection.DataBean.CollectionsBean.BodyBean.DataBean1.ArticlesBean> articlesBeen;

    public CollectionAdapter(Context context,List<ResponseCollection.DataBean.CollectionsBean.BodyBean.DataBean1.ArticlesBean> articlesBeen) {
        inflater = LayoutInflater.from(context);
        this.articlesBeen = articlesBeen;
    }

    @Override
    public int getCount() {
        return articlesBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return articlesBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.collection_item, parent, false);
        CollectionViewHolder collectionViewHolder = new CollectionViewHolder(view);
        collectionViewHolder.collectItemTitle.setText(articlesBeen.get(position).getTitle());
        collectionViewHolder.collectionImage.setImageURI(articlesBeen.get(position).getFeaturedImageUrl());
        collectionViewHolder.collectLikecount.setText(articlesBeen.get(position).getLikeCount()+"");
        collectionViewHolder.collectSeecount.setText(articlesBeen.get(position).getHitCount()+"");
        return view;
    }

    static class CollectionViewHolder {
        @BindView(R.id.collection_image)
        SimpleDraweeView collectionImage;
        @BindView(R.id.collect_Item_title)
        TextView collectItemTitle;
        @BindView(R.id.collect_likecount)
        TextView collectLikecount;
        @BindView(R.id.collect_seecount)
        TextView collectSeecount;
        @BindView(R.id.collection_ll)
        LinearLayout collectionLl;

        CollectionViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
