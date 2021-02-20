package com.bliss.csc.newsapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<NewsData> mDataset;
    public MyAdapter(List<NewsData> myDataset, Context context) {

        mDataset = myDataset;
        Fresco.initialize(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView TextView_title;
        public TextView TextView_content;
        public SimpleDraweeView ImageView_news;

        public MyViewHolder(View v) {
            super(v);

            TextView_title = v.findViewById(R.id.TextView_title);
            TextView_content = v.findViewById(R.id.TextView_content);
            ImageView_news  = v.findViewById(R.id.ImageView_title);
        }
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        NewsData news = mDataset.get(position);

        holder.TextView_title.setText(news.getTitle());

//        holder.TextView_content.setText(news.getContent());
        String content = news.getContent();
        if(content != null && content.length() > 0) {
            holder.TextView_content.setText(content);
        }
        else {
            holder.TextView_content.setText("본문 없음.");
        }

        Uri uri = Uri.parse(news.getUrlToImage());
        holder.ImageView_news.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }
}
