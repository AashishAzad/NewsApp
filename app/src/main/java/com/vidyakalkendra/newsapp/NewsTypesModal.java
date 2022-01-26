package com.vidyakalkendra.newsapp;

public class NewsTypesModal {

    private String newsTypes;
    private String newsTypesImageUrl;

    public NewsTypesModal(String newsTypes, String newsTypesImageUrl) {
        this.newsTypes = newsTypes;
        this.newsTypesImageUrl = newsTypesImageUrl;
    }

    public String getNewsTypes() {
        return newsTypes;
    }

    public void setNewsTypes(String newsTypes) {
        this.newsTypes = newsTypes;
    }

    public String getNewsTypesImageUrl() {
        return newsTypesImageUrl;
    }

    public void setNewsTypesImageUrl(String newsTypesImageUrl) {
        this.newsTypesImageUrl = newsTypesImageUrl;
    }
}
