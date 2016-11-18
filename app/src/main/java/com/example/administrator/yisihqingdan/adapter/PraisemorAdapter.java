package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.activity.PraiseActivity;
import com.example.administrator.yisihqingdan.entity.ResponsePraisemore;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yishi on 2016/11/8.
 */

public class PraisemorAdapter extends BaseAdapter {
    private List<ResponsePraisemore.DataBean.RankingsBean> rankingsBeen;
    private Context context;
    private LayoutInflater inflater;

    public PraisemorAdapter(Context context) {
        this.rankingsBeen = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public void setRankingsBeen(List<ResponsePraisemore.DataBean.RankingsBean> rankingsBeen1){
        rankingsBeen.addAll(rankingsBeen1);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return rankingsBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return rankingsBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.praisemore_listview_item, parent, false);
        PraiseHolder pholder = new PraiseHolder();
        pholder.praisemore_item_image = (SimpleDraweeView) view.findViewById(R.id.praisemore_item_image);
        pholder.praisemore_item_title = (TextView) view.findViewById(R.id.praisemore_item_title);
        pholder.praise_item_thingcount = (TextView) view.findViewById(R.id.praise_item_thingcount);
        pholder.praise_item_reviewcount = (TextView) view.findViewById(R.id.praise_item_reviewcount);
        pholder.praisemore_item_image.setImageURI(rankingsBeen.get(position).getFeaturedImageUrl());
        pholder.praisemore_item_title.setText(rankingsBeen.get(position).getTitle());
        pholder.praise_item_thingcount.setText(rankingsBeen.get(position).getThingCount()+"");
        pholder.praise_item_reviewcount.setText(rankingsBeen.get(position).getReviewCount()+"");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PraiseActivity.class);
                intent.putExtra("rankingId",rankingsBeen.get(position).getId());
                context.startActivity(intent);
            }
        });

        return view;
    }

    class PraiseHolder{
        SimpleDraweeView praisemore_item_image;
        TextView praisemore_item_title,praise_item_thingcount,praise_item_reviewcount;
    }
}
