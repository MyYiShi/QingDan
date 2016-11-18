package com.example.administrator.yisihqingdan.mvp.view;


import com.example.administrator.yisihqingdan.entity.ResponseArticleComments;
import com.example.administrator.yisihqingdan.entity.ResponseArticleTitle;
import com.example.administrator.yisihqingdan.entity.ResponseRelatedArticles;

import java.util.List;

/**
 * Created by LG on 2016/10/31.
 * Tips:
 */

public interface ArticleDetailView_interf {
    /**显示文章标题区域**/
    void showArticleTitle(ResponseArticleTitle articleTitle);
    void showArticleDetail(String url);
    void showArticleComments(List<ResponseArticleComments.DataBean.CommentsBean> comments);
    void showRelatedArticles(List<ResponseRelatedArticles.DataBean.ArticlesBean> articles);
    void showLookUpGoods();
    void showLikedCount(int likedCount);
    void showCommentsCount(int commentsCount);
    void showNoComments();

    void showMoreCommentsView();

    void showLoadFailed();
}
