package com.akexorcist.mvpsimple.network.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */

@Parcel
public class BlogInfo {
    String kind;
    String id;
    String name;
    String description;
    String published;
    String updated;
    String url;
    String selfLink;
    @SerializedName("posts")
    Post post;
    @SerializedName("pages")
    Page page;
    Locale locale;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Parcel
    public static class Post {
        @SerializedName("totalItems")
        int totalItemList;
        String selfLink;

        public int getTotalItemList() {
            return totalItemList;
        }

        public void setTotalItemList(int totalItemList) {
            this.totalItemList = totalItemList;
        }

        public String getSelfLink() {
            return selfLink;
        }

        public void setSelfLink(String selfLink) {
            this.selfLink = selfLink;
        }
    }

    @Parcel
    public static class Page {
        @SerializedName("totalItems")
        int totalItemList;
        String selfLink;

        public Page() {
        }

        public int getTotalItemList() {
            return totalItemList;
        }

        public void setTotalItemList(int totalItemList) {
            this.totalItemList = totalItemList;
        }

        public String getSelfLink() {
            return selfLink;
        }

        public void setSelfLink(String selfLink) {
            this.selfLink = selfLink;
        }
    }

    @Parcel
    public static class Locale {
        String language;
        String country;
        String variant;

        public Locale() {
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getVariant() {
            return variant;
        }

        public void setVariant(String variant) {
            this.variant = variant;
        }
    }
}
