package com.akexorcist.mvpsimple.module.feed;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class FeedPresenter implements FeedContractor.Presenter {

    private final FeedContractor.View viewFeedContractor;

    public FeedPresenter(FeedContractor.View viewFeedContractor) {
        this.viewFeedContractor = viewFeedContractor;
        viewFeedContractor.setPresenter(this);
    }

    @Override
    public void loadPostList() {

    }

    @Override
    public void start() {

    }
}
