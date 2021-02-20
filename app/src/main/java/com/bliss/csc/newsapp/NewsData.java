package com.bliss.csc.newsapp;

import java.io.Serializable;

public class NewsData implements Serializable {

    private String title;
    private String description;
    private String urlToImage;

    public void setDescription(String description) { this.description = description; }

    public String getDescription() { return description; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

}
