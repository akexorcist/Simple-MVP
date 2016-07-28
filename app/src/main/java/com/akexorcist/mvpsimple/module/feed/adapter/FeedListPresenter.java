package com.akexorcist.mvpsimple.module.feed.adapter;

import com.akexorcist.mvpsimple.network.model.PostList;

import java.util.List;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class FeedListPresenter implements FeedListContractor.Presenter {
    private FeedListContractor.View viewFeedListContractor;
    private List<PostList.Item> postItemList;

    public static FeedListPresenter createPresenter(FeedListContractor.View viewFeedListContractor) {
        return new FeedListPresenter(viewFeedListContractor);
    }

    public FeedListPresenter(FeedListContractor.View viewFeedListContractor) {
        this.viewFeedListContractor = viewFeedListContractor;
        viewFeedListContractor.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
    }

    @Override
    public void setPostItemList(List<PostList.Item> postItemList) {
        this.postItemList = postItemList;
        viewFeedListContractor.updateData();
    }

    @Override
    public String getPostItemTitleByPosition(int position) {
        return postItemList != null ? postItemList.get(position).getTitle() : null;
    }

    @Override
    public String getPostItemImageUrlByPosition(int position) {
        return postItemList != null ? getPostImageUrl(postItemList.get(position)) : null;
    }

    @Override
    public PostList.Item getPostItemByPosition(int position) {
        return postItemList != null ? postItemList.get(position) : null;
    }

    @Override
    public int getPostItemCount() {
        return (postItemList != null) ? postItemList.size() : 0;
    }

    private String getPostImageUrl(PostList.Item postItem) {
        List<PostList.Image> imageList = postItem.getImageList();
        if (imageList != null && imageList.size() > 0) {
            return imageList.get(0).getUrl();
        }
        return null;
    }
}
