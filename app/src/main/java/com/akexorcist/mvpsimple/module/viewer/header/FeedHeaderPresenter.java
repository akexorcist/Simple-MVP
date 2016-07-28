package com.akexorcist.mvpsimple.module.viewer.header;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.model.PostList;

import java.util.List;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class FeedHeaderPresenter implements FeedHeaderContractor.Presenter {
    private FeedHeaderContractor.View viewFeedHeaderContractor;
    private PostList.Item postItem;

    public static FeedHeaderPresenter createPresenter(FeedHeaderContractor.View viewFeedHeaderContractor) {
        return new FeedHeaderPresenter(viewFeedHeaderContractor);
    }

    public FeedHeaderPresenter(FeedHeaderContractor.View viewFeedHeaderContractor) {
        this.viewFeedHeaderContractor = viewFeedHeaderContractor;
        viewFeedHeaderContractor.setPresenter(this);
    }

    @Override
    public void start() {
        try {
            BusProvider.getProvider().getBus().register(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        BusProvider.getProvider().getBus().unregister(this);
    }

    @Override
    public void setPostItem(PostList.Item postItem) {
        this.postItem = postItem;
        viewFeedHeaderContractor.setImageHeader(getPostImageUrl(postItem));
    }

    @Override
    public PostList.Item getPostItem() {
        return postItem;
    }

    private String getPostImageUrl(PostList.Item postItem) {
        List<PostList.Image> imageList = postItem.getImageList();
        if (imageList != null && imageList.size() > 0) {
            return imageList.get(0).getUrl();
        }
        return null;
    }
}
