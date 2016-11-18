package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.entity.ResponseRelatedArticles;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/4.
 */
public class GridViewAdapter extends BaseAdapter {
    private List<ResponseRelatedArticles.DataBean.ArticlesBean> articlesBeen;
    private LayoutInflater inflater;

    public GridViewAdapter(Context context, List<ResponseRelatedArticles.DataBean.ArticlesBean> articlesBeen) {
        this.articlesBeen = articlesBeen;
        inflater = LayoutInflater.from(context);
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
        View view = inflater.inflate(R.layout.gridview_item, null);
        GridViewHolder holder = new GridViewHolder(view);
        holder.gridviewImage.setImageURI(articlesBeen.get(position).getFeaturedImageUrl());
        holder.gridviewText.setText(articlesBeen.get(position).getTitle());
        return view;
    }


    class GridViewHolder {
        @BindView(R.id.gridview_image)
        SimpleDraweeView gridviewImage;
        @BindView(R.id.gridview_text)
        TextView gridviewText;

        GridViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
