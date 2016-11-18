package com.example.administrator.yisihqingdan.mvp.model;

import com.alibaba.fastjson.JSON;
import com.example.administrator.yisihqingdan.entity.ResponseArticleComments;
import com.example.administrator.yisihqingdan.entity.ResponseArticleTitle;
import com.example.administrator.yisihqingdan.entity.ResponseRelatedArticles;
import com.example.administrator.yisihqingdan.http.HttpClient;
import com.example.administrator.yisihqingdan.http.HttpUtils;
import com.example.administrator.yisihqingdan.http.Request;
import com.example.administrator.yisihqingdan.http.UrlHandler;
import com.example.administrator.yisihqingdan.utils.Apis;

/**
 * Created by Yishi on 2016/11/3.
 */

public class ArticleDetailModel implements ArticleDetailModel_interf{
    private int articleId;
    private Callback callback;

    @Override
    public void loadDatas(int articleId, Callback callback) {
        this.articleId = articleId;
        this.callback = callback;
        loadArticleTitle();
        loadArticleDetail();
        loadComments();
        loadRelatedArticles();
    }

    @Override
    public void loadArticleTitle() {
        String url = UrlHandler.handlUrl(Apis.URL_ARTICLE_TITLE,articleId);
        Request.Builder builder = new Request.Builder()
                .get()
                .url(url);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseArticleTitle responseArticleTitle
                        = JSON.parseObject(response, ResponseArticleTitle.class);
                callback.loadArticleTitleSuccess(responseArticleTitle);
            }
            @Override
            public void onError() {
                callback.loadFailed();
            }
        });
    }

    @Override
    public void loadArticleDetail() {
        callback.loadArticleDetailSuccess(UrlHandler.handlUrl(Apis.URL_ARTICLE_DETAIL,articleId));
    }

    @Override
    public void loadComments() {
        String url = UrlHandler.handlUrl(Apis.URL_ARTICLE_COMMENTS,articleId);
        Request.Builder builder = new Request.Builder()
                .get()
                .url(url);
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseArticleComments responseArticleComments
                        = JSON.parseObject(response, ResponseArticleComments.class);
                callback.loadCommentsSuccess(responseArticleComments); callback.loadFailed();
            }

            @Override
            public void onError() {
                callback.loadFailed();
            }
        });
    }

    @Override
    public void loadRelatedArticles() {
        String url = UrlHandler.handlUrl(Apis.URL_RELATED_ARTICLES,articleId);
        Request.Builder builder = new Request.Builder()
                .url(url)
                .get();
        HttpClient.excute(builder, new HttpUtils.Callback() {
            @Override
            public void onResponse(String response) {
                ResponseRelatedArticles responseRelatedArticles
                        = JSON.parseObject(response, ResponseRelatedArticles.class);
                callback.loadRelatedArticlesSuccess(responseRelatedArticles.getData().getArticles());
            }
            @Override
            public void onError() {
                callback.loadFailed();
            }
        });
    }
}
