package com.example.administrator.yisihqingdan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.activity.Category_Up_Lookmore_Activitry;
import com.example.administrator.yisihqingdan.entity.ResponseCategoryMain;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/15.
 */
public class CategoryMain_Categories_Adapter extends BaseAdapter {

    private ResponseCategoryMain responseCategoryMain;
    private LayoutInflater inflater;
    private Context context;

    public CategoryMain_Categories_Adapter(Context context, ResponseCategoryMain responseCategoryMain) {
        this.responseCategoryMain = responseCategoryMain;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 9;
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_category_gridview_categories_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 8) {
            convertView = inflater.inflate(R.layout.fragment_category_gridview_lookmore, null);
            LookMoreViewHolder lookMoreViewHolder = new LookMoreViewHolder(convertView);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Category_Up_Lookmore_Activitry.class);
                    intent.putExtra("categories", responseCategoryMain);
                    context.startActivity(intent);
                }
            });

            return convertView;
        }
        holder.fategorymainGrideCategoriesImage.setImageURI(responseCategoryMain.getData().getCategories().getBody().getData().getCategories().get(position).getFeaturedImageUrl());
        holder.fategorymainGrideCategoriesText.setText(responseCategoryMain.getData().getCategories().getBody().getData().getCategories().get(position).getName());
        int[] colors = new int[]{R.color.category1, R.color.category2, R.color.category3, R.color.category4};
        holder.fragmentCategoryGridviewCategories.setBackgroundResource(colors[position % colors.length]);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.fategorymain_gride_categories_image)
        SimpleDraweeView fategorymainGrideCategoriesImage;
        @BindView(R.id.fategorymain_gride_categories_text)
        TextView fategorymainGrideCategoriesText;
        @BindView(R.id.fragment_category_gridview_categories)
        FrameLayout fragmentCategoryGridviewCategories;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class LookMoreViewHolder {
        @BindView(R.id.category_gridview_lookmoretext)
        TextView categoryGridviewLookmoretext;

        LookMoreViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
