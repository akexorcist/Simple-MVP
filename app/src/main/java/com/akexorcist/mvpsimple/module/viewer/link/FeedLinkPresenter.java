package com.akexorcist.mvpsimple.module.viewer.link;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.model.PostList;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class FeedLinkPresenter implements FeedLinkContractor.Presenter {
    private FeedLinkContractor.View viewFeedLinkContractor;
    private PostList.Item postItem;

    public static FeedLinkPresenter createPresenter(FeedLinkContractor.View viewFeedLinkContractor) {
        return new FeedLinkPresenter(viewFeedLinkContractor);
    }

    public FeedLinkPresenter(FeedLinkContractor.View viewFeedLinkContractor) {
        this.viewFeedLinkContractor = viewFeedLinkContractor;
        viewFeedLinkContractor.setPresenter(this);
    }

    @Override
    public void setPostItem(PostList.Item postItem) {
        this.postItem = postItem;
        if (postItem != null) {
            viewFeedLinkContractor.setPostId(postItem.getId());
            viewFeedLinkContractor.setPostUrl(postItem.getUrl());
        }
    }

    @Override
    public PostList.Item getPostItem() {
        return postItem;
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
}
