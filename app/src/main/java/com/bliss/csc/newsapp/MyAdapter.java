package com.bliss.csc.newsapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<NewsData> mDataset;
    public MyAdapter(List<NewsData> myDataset, Context context) {

        mDataset = myDataset;
        Fresco.initialize(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView TextView_title;
        public SimpleDraweeView ImageView_news;

        public MyViewHolder(View itemView) {
            super(itemView);

            TextView_title = itemView.findViewById(R.id.TextView_title);
            ImageView_news  = itemView.findViewById(R.id.ImageView_news);
        }
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        androidx.constraintlayout.widget.ConstraintLayout v = (androidx.constraintlayout.widget.ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_items, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        NewsData news = mDataset.get(position);
        holder.TextView_title.setText(news.getTitle());

        Uri uri = Uri.parse(news.getUrlToImage());
        holder.ImageView_news.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}
