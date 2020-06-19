package com.example.ex7t3hhomework.model;

import com.google.gson.annotations.SerializedName;

public class News  {
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String desc;
    @SerializedName("url")
    private String url;
    @SerializedName("urlToImage")
    private String image;
    @SerializedName("publishedAt")
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getPubDate() {
        return pubDate;
    }
}
