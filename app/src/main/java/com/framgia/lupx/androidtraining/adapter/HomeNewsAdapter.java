package com.framgia.lupx.androidtraining.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.lupx.androidtraining.R;
import com.framgia.lupx.androidtraining.asynctasks.LoadRemoteImageAsyncTask;
import com.framgia.lupx.androidtraining.models.guardian.Article;

import java.util.List;

/**
 * Created by FRAMGIA\pham.xuan.lu on 20/07/2015.
 */
public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.NewsViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private List<Article> data;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener itemClickListener;

    public HomeNewsAdapter(Context context, List<Article> data, OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_article_row, parent, false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        Article article = data.get(position);
        if (holder.task != null && !holder.task.isCancelled()) {
            holder.task.cancel(true);
        }
        holder.task = new LoadRemoteImageAsyncTask(context, holder.imgThumb);
        holder.task.execute(article.fields.thumbnail);
        holder.txtTitle.setText(article.webTitle);
        holder.txtPubDate.setText(article.webPublicationDate);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgThumb;
        public TextView txtTitle;
        public TextView txtPubDate;
        public LoadRemoteImageAsyncTask task;

        public NewsViewHolder(View view) {
            super(view);
            imgThumb = (ImageView) view.findViewById(R.id.imgThumb);
            txtTitle = (TextView) view.findViewById(R.id.txtTitle);
            txtPubDate = (TextView) view.findViewById(R.id.txtPubDate);
        }
    }
}
