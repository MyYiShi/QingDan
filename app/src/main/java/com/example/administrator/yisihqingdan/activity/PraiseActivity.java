package com.example.administrator.yisihqingdan.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.fragment.Praise_Fragment;
import com.example.administrator.yisihqingdan.utils.Contants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yishi on 2016/11/9.
 */

public class PraiseActivity extends BaseActivity {
    @BindView(R.id.textview_title_subview_title)
    TextView textviewTitleSubviewTitle;
    @BindView(R.id.imageView_back_subview_title)
    ImageView imageViewBackSubviewTitle;
    @BindView(R.id.search_edit_input)
    EditText searchEditInput;
    @BindView(R.id.img_search_delete)
    ImageView imgSearchDelete;
    @BindView(R.id.top_input)
    RelativeLayout topInput;
    @BindView(R.id.tv_review_tab)
    RadioButton tvReviewTab;
    @BindView(R.id.tv_rating_tab)
    RadioButton tvRatingTab;
    @BindView(R.id.tv_brand_tab)
    RadioButton tvBrandTab;
    @BindView(R.id.tab_list)
    RadioGroup tabList;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.root)
    RelativeLayout root;
    @BindView(R.id.img_search)
    ImageView imgsearch;


    private List<Praise_Fragment> fragments;
    private FragmentManager fragmentManager;
    private String searchKey = "";

    @Override
    protected int getContentResId() {
        return R.layout.praise;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        int rankingId = getIntent().getIntExtra("rankingId",0);
        fragments = new ArrayList<>();
        fragments.add(Praise_Fragment.newInstance(rankingId, Contants.SORT_BY_REVIEW_COUNT));
        fragments.add(Praise_Fragment.newInstance(rankingId, Contants.SORT_BY_RATING_SCORE));
        fragments.add(Praise_Fragment.newInstance(rankingId, Contants.SORT_BY_BRAND_NAME));
        fragmentManager = getSupportFragmentManager();
        switchFragment(0);

        searchEditInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)){
                    imgSearchDelete.setVisibility(View.GONE);
                }else{
                    imgSearchDelete.setVisibility(View.VISIBLE);
                }
                doSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.tv_review_tab:
                        switchFragment(0);
                        break;
                    case R.id.tv_rating_tab:
                        switchFragment(1);
                        break;
                    case R.id.tv_brand_tab:
                        switchFragment(2);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.imageView_back_subview_title, R.id.search_edit_input, R.id.img_search_delete})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_back_subview_title:
                finish();
                break;
            case R.id.search_edit_input:
                break;
            case R.id.img_search_delete:
                searchEditInput.getText().clear();
                break;
        }
    }

    /**
     * 执行搜素
     */
    private void doSearch(String searchKey) {
        ((Praise_Fragment)lastFragment).showFragment(searchKey);
        this.searchKey = searchKey;
    }


    private Fragment lastFragment;
    private void switchFragment(int i) {
        Praise_Fragment fragment = fragments.get(i);
        if (fragment == lastFragment){
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment.isAdded()){
            fragment.showFragment(searchKey);
            transaction.show(fragment);
        }else{
            fragment.setSearchKey(searchKey);
            transaction.add(R.id.container,fragment);
        }
        if (lastFragment != null){
            transaction.hide(lastFragment);
        }
        transaction.commit();
        lastFragment = fragment;
    }
}
