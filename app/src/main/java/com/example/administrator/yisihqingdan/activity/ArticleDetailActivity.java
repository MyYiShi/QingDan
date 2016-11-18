package com.example.administrator.yisihqingdan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.administrator.yisihqingdan.R;
import com.example.administrator.yisihqingdan.adapter.GridViewAdapter;
import com.example.administrator.yisihqingdan.entity.ResponseArticleComments;
import com.example.administrator.yisihqingdan.entity.ResponseArticleTitle;
import com.example.administrator.yisihqingdan.entity.ResponseRelatedArticles;
import com.example.administrator.yisihqingdan.mvp.present.ArticleDetailPresenter;
import com.example.administrator.yisihqingdan.mvp.view.ArticleDetailView_interf;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yishi on 2016/11/3.
 */
public class ArticleDetailActivity extends BaseActivity implements ArticleDetailView_interf {

    @BindView(R.id.textview_title_subview_title)
    TextView textviewTitleSubviewTitle;
    @BindView(R.id.imageView_back_subview_title)
    ImageView imageViewBackSubviewTitle;
    @BindView(R.id.textView_title_subview_article_title)
    TextView textViewTitleSubviewArticleTitle;
    @BindView(R.id.imageView_author_subview_article_title)
    SimpleDraweeView imageViewAuthorSubviewArticleTitle;
    @BindView(R.id.textView_author_nickname_subview_article_title)
    TextView textViewAuthorNicknameSubviewArticleTitle;
    @BindView(R.id.textView_publish_time_subview_article_title)
    TextView textViewPublishTimeSubviewArticleTitle;
    @BindView(R.id.textView_author_tag_subview_article_title)
    TextView textViewAuthorTagSubviewArticleTitle;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.textView_write_comment)
    TextView textViewWriteComment;
    @BindView(R.id.layout_container_subiew_comments)
    LinearLayout layoutContainerSubiewComments;
    @BindView(R.id.refreshLayout)
    MaterialRefreshLayout refreshLayout;
    @BindView(R.id.textView_like_count)
    TextView textViewLikeCount;
    @BindView(R.id.layout_like_count)
    LinearLayout layoutLikeCount;
    @BindView(R.id.textView_comments_count)
    TextView textViewCommentsCount;
    @BindView(R.id.layout_comments_count)
    LinearLayout layoutCommentsCount;
    @BindView(R.id.layout_shared)
    LinearLayout layoutShared;
    @BindView(R.id.textView_lookup_goods)
    TextView textViewLookupGoods;
    @BindView(R.id.imageView_big_subview_article_title)
    SimpleDraweeView imageViewBigSubviewArticleTitle;
    @BindView(R.id.textView_tag_subview_article_title)
    TextView textViewTagSubviewArticleTitle;
    @BindView(R.id.rela_no_comments)
    RelativeLayout relaNoComments;
    @BindView(R.id.layout_comments)
    LinearLayout layoutComments;
    @BindView(R.id.gridview_articles)
    GridView gridviewArticles;

    private int articleId;
    private ArticleDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentResId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        webview.getSettings().setJavaScriptEnabled(true);

        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                presenter.loadDatas(articleId);
            }
        });

        articleId = getIntent().getIntExtra("articleId", 0);
        presenter = new ArticleDetailPresenter(this);

        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                refreshLayout.autoRefresh();
            }
        }.sendEmptyMessageDelayed(0, 500);
    }

    @Override
    public void showArticleTitle(ResponseArticleTitle articleTitle) {
        refreshLayout.finishRefresh();
        imageViewBigSubviewArticleTitle.setImageURI(articleTitle.getData().getFeaturedImageUrl());
        textViewTitleSubviewArticleTitle.setText(articleTitle.getData().getTitle());
        imageViewAuthorSubviewArticleTitle.setImageURI(articleTitle.getData().getAuthor().getAvatarUrl());
        textViewAuthorNicknameSubviewArticleTitle.setText(articleTitle.getData().getAuthor().getNickname());
        textViewAuthorTagSubviewArticleTitle.setText(articleTitle.getData().getAuthor().getTagline());
        textViewPublishTimeSubviewArticleTitle.setText(articleTitle.getData().getPublishedAtDiffForHumans());
        if (articleTitle.getData().getCategories() != null && articleTitle.getData().getCategories().size() != 0) {
            textViewTagSubviewArticleTitle.setText(articleTitle.getData().getCategories().get(0).getName());
        }
    }

    @Override
    public void showArticleDetail(String url) {
        webview.loadUrl(url);
    }

    @Override
    public void showArticleComments(List<ResponseArticleComments.DataBean.CommentsBean> comments) {
        LayoutInflater inflater = LayoutInflater.from(this);
        layoutComments.setVisibility(View.VISIBLE);
        layoutComments.removeAllViews();//不加这一行在刷新的时候 评论条数会一直增加
        for (int i = 0; i < comments.size(); i++) {
            View view = inflater.inflate(R.layout.list_item_comment, layoutComments, false);
            CommmentItemViewHolder holder = new CommmentItemViewHolder(view);
            ResponseArticleComments.DataBean.CommentsBean comment = comments.get(i);
            holder.imageViewAuthorAvatar.setImageURI(comment.getUser().getAvatarUrl());
            holder.textViewAuthorName.setText(comment.getUser().getNickname());
            holder.textViewCommentTimeTag.setText(comment.getCreatedAtDiffForHumans());

            if (comment.getReplyToUser() == null) {
                holder.textViewComments.setText(comment.getBody());

            } else {
                String replyUserNickName = comment.getReplyToUser().getNickname();
                holder.textViewComments.setText("回复 " + replyUserNickName + "：" + comment.getBody());
            }
            holder.textViewCommentLikeCount.setText(comment.getUpvoteCount() + "");
            layoutComments.addView(view);
        }
    }

    @Override
    public void showRelatedArticles(final List<ResponseRelatedArticles.DataBean.ArticlesBean> articles) {
        LayoutInflater inflater = LayoutInflater.from(this);
        GridViewAdapter adapter = new GridViewAdapter(this,articles);
        gridviewArticles.setAdapter(adapter);
        gridviewArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ArticleDetailActivity.this,ArticleDetailActivity.class);
                intent.putExtra("articleId",articles.get(position).getId());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void showLookUpGoods() {
        textViewLookupGoods.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLikedCount(int likedCount) {
        textViewLikeCount.setText("喜欢(" + likedCount + ")");
    }

    @Override
    public void showCommentsCount(int commentsCount) {
        textViewCommentsCount.setText("评论(" + commentsCount + ")");
    }

    @Override
    public void showNoComments() {
        relaNoComments.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMoreCommentsView() {

    }

    @Override
    public void showLoadFailed() {
        refreshLayout.finishRefresh();
    }

    @OnClick({R.id.imageView_back_subview_title, R.id.layout_like_count, R.id.layout_comments_count, R.id.layout_shared, R.id.textView_lookup_goods})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_back_subview_title:
                finish();
                break;
            case R.id.layout_like_count:
                break;
            case R.id.layout_comments_count:
                break;
            case R.id.layout_shared:
                break;
            case R.id.textView_lookup_goods:
                break;
        }
    }

    static class CommmentItemViewHolder {
        @BindView(R.id.imageView_author_avatar)
        SimpleDraweeView imageViewAuthorAvatar;
        @BindView(R.id.textView_author_name)
        TextView textViewAuthorName;
        @BindView(R.id.textView_comment_time_tag)
        TextView textViewCommentTimeTag;
        @BindView(R.id.textView_comment_like_count)
        TextView textViewCommentLikeCount;
        @BindView(R.id.imageView_comment_like)
        ImageView imageViewCommentLike;
        @BindView(R.id.textView_comments)
        TextView textViewComments;

        CommmentItemViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
