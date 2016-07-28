package com.akexorcist.mvpsimple.module.viewer.info;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.model.PostList;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class FeedInfoPresenter implements FeedInfoContractor.Presenter {
    private FeedInfoContractor.View viewFeedInfoContractor;
    private PostList.Item postItem;

    public static FeedInfoPresenter createPresenter(FeedInfoContractor.View viewFeedInfoContractor) {
        return new FeedInfoPresenter(viewFeedInfoContractor);
    }

    public FeedInfoPresenter(FeedInfoContractor.View viewFeedInfoContractor) {
        this.viewFeedInfoContractor = viewFeedInfoContractor;
        viewFeedInfoContractor.setPresenter(this);
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
        viewFeedInfoContractor.setFeedItemTitle(postItem.getTitle());
    }

    @Override
    public PostList.Item getPostItem() {
        return postItem;
    }
}
