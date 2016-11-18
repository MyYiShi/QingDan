package com.example.administrator.yisihqingdan.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.TagDetaiRecylcerAdapter;
import com.example.administrator.yisihqingdan.entity.ResponseTagDetail;
import com.example.administrator.yisihqingdan.mvp.present.TagDetailPresent;
import com.example.administrator.yisihqingdan.mvp.view.TagDetailView_interf;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yishi on 2016/11/16.
 */

public class TagDetailActivity extends BaseActivity implements TagDetailView_interf{
    @BindView(R.id.textview_title_subview_title)
    TextView textviewTitleSubviewTitle;
    @BindView(R.id.imageView_back_subview_title)
    ImageView imageViewBackSubviewTitle;
    @BindView(R.id.categories_detail_recyclerview)
    RecyclerView categoriesDetailRecyclerview;

    private TagDetailPresent present;
    private TagDetaiRecylcerAdapter adapter;

    @Override
    protected int getContentResId() {
        return R.layout.categories_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        int tagId = getIntent().getIntExtra("tagId",0);
        String titleText = getIntent().getStringExtra("titleText");
        textviewTitleSubviewTitle.setText(titleText);
        present = new TagDetailPresent(this);
        present.loadDatas(tagId);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position <= 2){
                    return 1;
                }
                return 2;
            }
        });
        categoriesDetailRecyclerview.setLayoutManager(gridLayoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_space);
        categoriesDetailRecyclerview.addItemDecoration(new SpaceItemDecoration(spacingInPixels/2));
    }

    //设置recycleview之间的间距
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildAdapterPosition(view) != 0){
                outRect.bottom = space;
                outRect.left = space;
                outRect.right = space;
                outRect.top = space;
            }
        }

    }


    @Override
    public void showTagDetailSuccess(ResponseTagDetail responseTagDetail) {

    }

    @Override
    public void showTagDetailFailed() {

    }
}
