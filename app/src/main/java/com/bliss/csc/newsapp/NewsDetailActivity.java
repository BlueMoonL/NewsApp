package com.bliss.csc.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class NewsDetailActivity extends Activity {

    private NewsData news;
    private TextView TextView_title, TextView_description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);

        setComp();
        getNewsDetail();
        setNews();
    }

    public void setComp() {
        TextView_title = findViewById(R.id.TextView_title);
        TextView_description = findViewById(R.id.TextView_description);
    }

    public void getNewsDetail() {
        Intent intent = getIntent();
        if(intent != null) {
            Bundle bld = intent.getExtras();

            Object obj = bld.get("news");
            if(obj != null && obj instanceof NewsData) {
                this.news = (NewsData) obj;
            }
        }
    }

    public void setNews() {
        if(this.news != null) {
            String title = this.news.getTitle();
            if(title != null) {
                TextView_title.setText(title);
            }
            String description = this.news.getDescription();
            if(description != null) {
                TextView_description.setText(description);
            }
        }
    }
}
