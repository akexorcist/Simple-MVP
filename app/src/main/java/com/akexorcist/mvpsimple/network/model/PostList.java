package com.akexorcist.mvpsimple.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */


public class PostList {
    String kind;
    String nextPageToken;
    String etag;
    @SerializedName("items")
    List<Item> itemList;

    public PostList() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public class Item {
        String kind;
        String id;
        Blog blog;
        String published;
        String updated;
        String etag;
        String url;
        String selfLink;
        String title;
        @SerializedName("images")
        List<Image> imageList;
        Author author;
        @SerializedName("replies")
        Reply reply;
        @SerializedName("labels")
        List<String> labelList;

        public Item() {
        }

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

        public Blog getBlog() {
            return blog;
        }

        public void setBlog(Blog blog) {
            this.blog = blog;
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

        public String getEtag() {
            return etag;
        }

        public void setEtag(String etag) {
            this.etag = etag;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Image> getImageList() {
            return imageList;
        }

        public void setImageList(List<Image> imageList) {
            this.imageList = imageList;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }

        public Reply getReply() {
            return reply;
        }

        public void setReply(Reply reply) {
            this.reply = reply;
        }

        public List<String> getLabelList() {
            return labelList;
        }

        public void setLabelList(List<String> labelList) {
            this.labelList = labelList;
        }
    }

    public class Blog {
        String id;

        public Blog() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public class Image {
        String url;

        public Image() {
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Author {
        String id;
        String displayName;
        String url;
        @SerializedName("images")
        List<Image> imageList;

        public Author() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<Image> getImageList() {
            return imageList;
        }

        public void setImageList(List<Image> imageList) {
            this.imageList = imageList;
        }
    }

    public class Reply {
        String totalItems;
        String selfLink;

        public Reply() {
        }

        public String getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(String totalItems) {
            this.totalItems = totalItems;
        }

        public String getSelfLink() {
            return selfLink;
        }

        public void setSelfLink(String selfLink) {
            this.selfLink = selfLink;
        }
    }
}
