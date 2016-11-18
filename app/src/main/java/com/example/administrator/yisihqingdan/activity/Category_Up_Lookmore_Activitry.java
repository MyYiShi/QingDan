package com.example.administrator.yisihqingdan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.Category_Up_Lookmore_left_ListView_Adapter;
import com.example.administrator.yisihqingdan.entity.ResponseAllCategories;
import com.example.administrator.yisihqingdan.entity.ResponseCategoryMain;
import com.example.administrator.yisihqingdan.mvp.present.AllCategoriesPresent;
import com.example.administrator.yisihqingdan.mvp.view.AllCategoriesView_interf;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by Yishi on 2016/11/15.
 */

public class Category_Up_Lookmore_Activitry extends BaseActivity implements AllCategoriesView_interf{
    @BindView(R.id.textview_title_subview_title)
    TextView textviewTitleSubviewTitle;
    @BindView(R.id.imageView_back_subview_title)
    ImageView imageViewBackSubviewTitle;
    @BindView(R.id.category_uplookmore_left_listview)
    ListView categoryUplookmoreLeftListview;
    @BindView(R.id.category_uplookmore_right_scrollView)
    ScrollView categoryUplookmoreRightScrollView;
    @BindView(R.id.container)
    LinearLayout container;

    private Category_Up_Lookmore_left_ListView_Adapter leftadapter;
    private AllCategoriesPresent present;


    @Override
    protected int getContentResId() {
        return R.layout.category_lookmore;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        textviewTitleSubviewTitle.setText("全部分类");
        ResponseCategoryMain responseCategoryMain = (ResponseCategoryMain) getIntent().getSerializableExtra("categories");
        leftadapter = new Category_Up_Lookmore_left_ListView_Adapter(this, responseCategoryMain);
        categoryUplookmoreLeftListview.setAdapter(leftadapter);
        present = new AllCategoriesPresent(this);
        present.loadDatas();
    }

    private int currentIndex = -1;

    private void showCategoryRight(int index) {
        if (index == currentIndex) {
            return;
        }
        currentIndex = index;
        container.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        List<ResponseAllCategories.DataBean.CategoriesBean.ChildrenBean> childrenBeens =
                responseAllCategories.getData().getCategories().get(index).getChildren();
        for (int i = 0; i < childrenBeens.size(); i++) {
            View view = inflater.inflate(R.layout.category_lookmore_rigth_scrollview_item, null);
            ScroviewViewHolder scroviewViewHolder = new ScroviewViewHolder(view);
            scroviewViewHolder.categoryLookMoreRightScrollviewLlRelative.setTag(childrenBeens.get(i));
            scroviewViewHolder.categoryLookMoreRightScrollviewLlRelative.setOnClickListener(categoryOnClickListener);
            scroviewViewHolder.categoryLookmoreRightListviewText.setText(childrenBeens.get(i).getName());
            //向流式布局中循环添加标签
            for (int j = 0; j < childrenBeens.get(i).getChildren().size(); j++) {
                View view1 = inflater.inflate(R.layout.category_lookmore_rigth_scrollview_item_flowwater_item, null);
                TabHolder holder = new TabHolder(view1);
                holder.tabtextview.setTag(childrenBeens.get(i).getChildren().get(j));
                holder.tabtextview.setText(childrenBeens.get(i).getChildren().get(j).getName());
                holder.tabtextview.setOnClickListener(tabOnClickListener);
                scroviewViewHolder.allcategoriesFlowlayout.addView(view1);
            }
            container.addView(view);
        }
    }

    private ResponseAllCategories responseAllCategories;

    @Override
    public void showAllCategotiesSuccess(ResponseAllCategories responseAllCategories) {
        this.responseAllCategories = responseAllCategories;
        showCategoryRight(0);
        categoryUplookmoreLeftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                leftadapter.setSelectedPosition(position);
                showCategoryRight(position);
            }
        });
    }

    @Override
    public void showAllCategotiesFailed() {

    }

    //Scollview里面大标题的监听，如刀剪
    private View.OnClickListener categoryOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ResponseAllCategories.DataBean.CategoriesBean.ChildrenBean childrenbean =
                    (ResponseAllCategories.DataBean.CategoriesBean.ChildrenBean) v.getTag();
            Toast.makeText(Category_Up_Lookmore_Activitry.this, childrenbean.getName(), Toast.LENGTH_SHORT).show();
            //TODO  跳转
        }
    };

    //flowlayout里面每个textview空间的监听
    private View.OnClickListener tabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ResponseAllCategories.DataBean.CategoriesBean.ChildrenBean.ChildrenBean1 childrenBean1 =
                    (ResponseAllCategories.DataBean.CategoriesBean.ChildrenBean.ChildrenBean1) v.getTag();
            Intent intent = new Intent(Category_Up_Lookmore_Activitry.this,TagDetailActivity.class);
            intent.putExtra("tagId",childrenBean1.getTagId());
            intent.putExtra("titleText",childrenBean1.getName());
            startActivity(intent);
        }
    };


    static class ScroviewViewHolder {
        @BindView(R.id.category_lookmore_right_listview_text)
        TextView categoryLookmoreRightListviewText;
        @BindView(R.id.category_lookmore_right_listview_image)
        ImageView categoryLookmoreRightListviewImage;
        @BindView(R.id.allcategories_flowlayout)
        FlowLayout allcategoriesFlowlayout;
        @BindView(R.id.category_lookmore_right_scrollview_ll)
        LinearLayout categoryLookmoreRightScrollviewLl;
        @BindView(R.id.category_lookmore_right_scrollview_ll_relative)
        RelativeLayout categoryLookMoreRightScrollviewLlRelative;


        ScroviewViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class TabHolder {
        @BindView(R.id.tabtextview)
        TextView tabtextview;

        TabHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
