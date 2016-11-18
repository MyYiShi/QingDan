package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.entity.ResponseCategoryMain;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/15.
 */

public class Category_Up_Lookmore_left_ListView_Adapter extends BaseAdapter {
    private ResponseCategoryMain responseCategoryMain;
    private LayoutInflater inflater;

    public Category_Up_Lookmore_left_ListView_Adapter(Context context, ResponseCategoryMain responseCategoryMain) {
        this.responseCategoryMain = responseCategoryMain;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return responseCategoryMain.getData().getCategories().getBody().getData().getCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftListviewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.category_lookmore_left_listview_item, null);
            holder = new LeftListviewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (LeftListviewHolder) convertView.getTag();
        }
        if (selectPosition == position){
            holder.fategorymainGrideCategoriesImage.setImageURI(responseCategoryMain.getData().getCategories().getBody().getData().getCategories().get(position).getIcons().getActive().getImageUrl());
        }else {
            holder.fategorymainGrideCategoriesImage.setImageURI(responseCategoryMain.getData().getCategories().getBody().getData().getCategories().get(position).getIcons().getInactive().getImageUrl());
        }
        return convertView;
    }

    static class LeftListviewHolder {
        @BindView(R.id.fategorymain_gride_categories_image)
        SimpleDraweeView fategorymainGrideCategoriesImage;

        LeftListviewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private int selectPosition;
    public void setSelectedPosition(int selectedPosition){
        this.selectPosition = selectedPosition;
        notifyDataSetChanged();
    }
}
