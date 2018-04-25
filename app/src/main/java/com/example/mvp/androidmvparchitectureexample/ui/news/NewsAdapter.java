package com.example.mvp.androidmvparchitectureexample.ui.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.local.ArticleEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ali DOUIRI on 25/04/2018.
 * my.alidouiri@gmail.com
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ArticleHolder> {

    private static final String TAG = NewsAdapter.class.getSimpleName();

    private List<ArticleEntity> mItems;
    private Context mContext;

    public NewsAdapter(Context context, List<ArticleEntity> items) {

        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new ArticleHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {

        ArticleEntity mItem = mItems.get(position);

        if (mItem != null) {

            if(mItem.getTitle() != null)
                holder.mTitle.setText(mItem.getTitle());

            if(mItem.getDescription() != null)
                holder.mDescription.setText(mItem.getDescription());

            if(mItem.getPublishedAt() != null)
                holder.mDate.setText(mItem.getPublishedAt());

            if(mItem.getUrlToImage() != null)
                Glide.with(mContext).load(mItem.getUrlToImage()).
                        asBitmap().into(holder.mThumbnail);

        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<ArticleEntity> mItems) {

        if (mItems != null && !mItems.isEmpty()) {

            this.mItems = mItems;
            notifyDataSetChanged();
        }

    }

    static class ArticleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView mThumbnail;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.description)
        TextView mDescription;
        @BindView(R.id.date)
        TextView mDate;

        public ArticleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

