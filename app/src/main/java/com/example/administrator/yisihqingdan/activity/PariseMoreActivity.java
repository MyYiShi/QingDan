package com.example.administrator.yisihqingdan.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.PraisemorAdapter;
import com.example.administrator.yisihqingdan.entity.ResponsePraisemore;
import com.example.administrator.yisihqingdan.mvp.present.PraisemorePresent;
import com.example.administrator.yisihqingdan.mvp.view.PraisemoreView_interf;
import com.example.administrator.yisihqingdan.utils.Apis;

/**
 * Created by Yishi on 2016/11/8.
 */

public class PariseMoreActivity extends BaseActivity implements PraisemoreView_interf{
    private PraisemorePresent present;
    private ListView parisemore_listview;
    private PraisemorAdapter adapter;
    private ImageView imageView;
    private TextView textview_title_subview_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        present = new PraisemorePresent(Apis.URL_PRAISEMORE,this);
        present.loadParisemoreDatas();
    }

    @Override
    protected int getContentResId() {
        return R.layout.praisemore;
    }

    @Override
    protected void initView() {
        parisemore_listview = (ListView) findViewById(R.id.parisemore_listview);
        textview_title_subview_title = (TextView) findViewById(R.id.textview_title_subview_title);
        textview_title_subview_title.setText("口碑清单");
        View view = LayoutInflater.from(this).inflate(R.layout.prasiemore_listviewhead,null);
        parisemore_listview.addHeaderView(view);
        adapter = new PraisemorAdapter(this);
        parisemore_listview.setAdapter(adapter);
    }

    private boolean isLoading;
    @Override
    public void showParisemoreDatasSuccess(final ResponsePraisemore responsePraisemore) {
        adapter.setRankingsBeen(responsePraisemore.getData().getRankings());
        isLoading = false;
        parisemore_listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
                parisemore_listview.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!isLoading && firstVisibleItem+visibleItemCount == totalItemCount){
                            present.loadParisemoreDatas();
                            isLoading = true;
                        }
                    }
                });
            }
        });
    }

    @Override
    public void showParisemoreFailed() {

    }
}
