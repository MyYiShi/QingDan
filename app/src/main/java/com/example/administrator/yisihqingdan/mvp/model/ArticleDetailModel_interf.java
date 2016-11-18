package com.example.administrator.yisihqingdan.mvp.model;

import com.example.administrator.yisihqingdan.entity.ResponseArticleComments;
import com.example.administrator.yisihqingdan.entity.ResponseArticleTitle;
import com.example.administrator.yisihqingdan.entity.ResponseRelatedArticles;

import java.util.List;

/**
 * Created by Yishi on 2016/11/3.
 */

public interface ArticleDetailModel_interf {
    void loadDatas(int articleId,Callback callback);
    void loadArticleTitle();
    void loadArticleDetail();
    void loadComments();
    void loadRelatedArticles();
    public interface Callback{
        void loadArticleTitleSuccess(ResponseArticleTitle articleTitle);
        void loadArticleDetailSuccess(String url);
        void loadCommentsSuccess(ResponseArticleComments responseArticleComments);
        void loadRelatedArticlesSuccess(List<ResponseRelatedArticles.DataBean.ArticlesBean> relatedArticles);
        void loadFailed();
    }
}
