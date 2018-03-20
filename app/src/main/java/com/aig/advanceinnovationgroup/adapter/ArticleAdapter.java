package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.ViewBlogDetailActivity;
import com.aig.advanceinnovationgroup.model.ArticleDetail;

import java.util.List;

/**
 * Created by admin on 3/16/2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {

    private Context context;
    private List<ArticleDetail> articleDetailList;

    public ArticleAdapter(Context context, List<ArticleDetail> articleDetailList){
        this.context = context;
        this.articleDetailList = articleDetailList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title, description;
        LinearLayout ll_blog;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            ll_blog = (LinearLayout) itemView.findViewById(R.id.ll_blog);
        }

    }

    @Override
    public ArticleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View articleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_article_view, parent, false);
        return new MyViewHolder(articleView);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.MyViewHolder holder, int position) {
        final ArticleDetail articleDetail = articleDetailList.get(position);

        holder.title.setText(articleDetail.getArticleTitle());
        holder.description.setText(articleDetail.getArticleDescription()+"......");

        holder.ll_blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewBlogDetailActivity.class);
                intent.putExtra("url", articleDetail.getArticleLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleDetailList.size();
    }
}
