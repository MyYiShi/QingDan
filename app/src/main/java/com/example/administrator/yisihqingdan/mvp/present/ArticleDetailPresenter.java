package com.example.administrator.yisihqingdan.mvp.present;

import com.example.administrator.yisihqingdan.entity.ResponseArticleComments;
import com.example.administrator.yisihqingdan.entity.ResponseArticleTitle;
import com.example.administrator.yisihqingdan.entity.ResponseRelatedArticles;
import com.example.administrator.yisihqingdan.mvp.model.ArticleDetailModel;
import com.example.administrator.yisihqingdan.mvp.model.ArticleDetailModel_interf;
import com.example.administrator.yisihqingdan.mvp.view.ArticleDetailView_interf;

import java.util.List;

/**
 * Created by Yishi on 2016/11/3.
 */

public class ArticleDetailPresenter implements ArticleDetailPresenter_interf{
    private ArticleDetailModel_interf model;
    private ArticleDetailView_interf view;

    public ArticleDetailPresenter(ArticleDetailView_interf view) {
        this.view = view;
        model = new ArticleDetailModel();
    }

    @Override
    public void loadDatas(final int articleId) {
        model.loadDatas(articleId, new ArticleDetailModel_interf.Callback() {
            @Override
            public void loadArticleTitleSuccess(ResponseArticleTitle articleTitle) {
                view.showArticleTitle(articleTitle);
                view.showCommentsCount(articleTitle.getData().getCommentCount());
                if (articleTitle.getData().getCommentCount() > 4){
                    view.showMoreCommentsView();
                }
                view.showLikedCount(articleTitle.getData().getLikeCount());
                if (articleTitle.getData().getThingCount() > 0){
                    view.showLookUpGoods();
                }
            }

            @Override
            public void loadArticleDetailSuccess(String url) {
                view.showArticleDetail(url);
            }

            @Override
            public void loadCommentsSuccess(ResponseArticleComments responseArticleComments) {
                if (responseArticleComments.getData().getComments() == null ||
                        responseArticleComments.getData().getComments().size() == 0){
                    view.showNoComments();
                }else{
                    view.showArticleComments(responseArticleComments.getData().getComments());
                }
            }

            @Override
            public void loadRelatedArticlesSuccess(List<ResponseRelatedArticles.DataBean.ArticlesBean> relatedArticles) {
                view.showRelatedArticles(relatedArticles);
            }

            @Override
            public void loadFailed() {
                view.showLoadFailed();
            }
        });
    }
}
