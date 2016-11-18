package com.example.administrator.yisihqingdan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.CategoryMain_Categories_Adapter;
import com.example.administrator.yisihqingdan.adapter.CategoryMain_Tags_Adapter;
import com.example.administrator.yisihqingdan.entity.ResponseCategoryMain;
import com.example.administrator.yisihqingdan.mvp.present.CategoryMainPresent;
import com.example.administrator.yisihqingdan.mvp.view.CategoryMainView_interf;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/10/27.
 */

public class Category_fragment extends Base_fragment implements CategoryMainView_interf{

    @BindView(R.id.category_search_editext)
    EditText categorySearchEditext;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.category_gridview_up)
    GridView categoryGridviewUp;
    @BindView(R.id.category_gridview_down)
    GridView categoryGridviewDown;


    private CategoryMainPresent present;
    private CategoryMain_Categories_Adapter categorise_adapter;
    private CategoryMain_Tags_Adapter tags_adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        present = new CategoryMainPresent(this);
        present.loadDatas();
    }

    @Override
    protected int getContentResId() {
        return R.layout.fragment_category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void showCategoryMainSuccess(ResponseCategoryMain responseCategoryMain) {
        categorise_adapter = new CategoryMain_Categories_Adapter(this.getActivity(),responseCategoryMain);
        categoryGridviewUp.setAdapter(categorise_adapter);
        tags_adapter = new CategoryMain_Tags_Adapter(getActivity(),responseCategoryMain);
        categoryGridviewDown.setAdapter(tags_adapter);
    }

    @Override
    public void showCategoryMainFailed() {

    }
}
