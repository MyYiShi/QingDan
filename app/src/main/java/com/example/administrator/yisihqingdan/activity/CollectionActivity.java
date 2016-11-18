package com.example.administrator.yisihqingdan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.CollectionAdapter;
import com.example.administrator.yisihqingdan.entity.ResponseCollection;
import com.example.administrator.yisihqingdan.mvp.present.CollectionPresent;
import com.example.administrator.yisihqingdan.mvp.view.CollectionView_interf;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Yishi on 2016/11/7.
 */

public class CollectionActivity extends BaseActivity implements CollectionView_interf{
    private ListView collection_listview;
    private int collection_id;
    private CollectionPresent present;
    private View viewhead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collection_id = getIntent().getIntExtra("collection_id",0);
        present = new CollectionPresent(this,collection_id);
        present.loadCollectionDatas();
    }

    @Override
    protected int getContentResId() {
        return R.layout.collection;
    }

    @Override
    protected void initView() {
        collection_listview = (ListView) findViewById(R.id.collection_listview);
    }

    @Override
    public void showCollectionDatas(final ResponseCollection responseCollection) {
        if (viewhead == null){
            viewhead = LayoutInflater.from(this).inflate(R.layout.collection_head,null);
            SimpleDraweeView image = (SimpleDraweeView) viewhead.findViewById(R.id.collection_head_image);
            TextView textView1 = (TextView) viewhead.findViewById(R.id.collection_head_text);
            TextView textView2 = (TextView) viewhead.findViewById(R.id.collection_head_text2);
            image.setImageURI(responseCollection.getData().getCollection().getBody().getData().getFeaturedImageUrl());
            textView1.setText(responseCollection.getData().getCollection().getBody().getData().getTitle());
            textView2.setText(responseCollection.getData().getCollection().getBody().getData().getExcerpt());
            collection_listview.addHeaderView(viewhead);
        }
        CollectionAdapter adapter = new CollectionAdapter(this,responseCollection.getData().getCollections().getBody().getData().getArticles());
        collection_listview.setAdapter(adapter);
        collection_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CollectionActivity.this,ArticleDetailActivity.class);
                if (position>=1){
                    intent.putExtra("articleId",responseCollection.getData().getCollections().getBody().getData().getArticles().get(position-1).getId());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void showCollentionFailed() {

    }
}
