package com.aig.advanceinnovationgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 3/16/2018.
 */

public class GetBlogDta {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("article_detail")
    @Expose
    private List<ArticleDetail> articleDetail = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ArticleDetail> getArticleDetail() {
        return articleDetail;
    }

    public void setArticleDetail(List<ArticleDetail> articleDetail) {
        this.articleDetail = articleDetail;
    }
}
