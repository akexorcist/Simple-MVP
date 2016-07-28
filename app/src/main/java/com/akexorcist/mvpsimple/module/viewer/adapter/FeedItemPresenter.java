package com.akexorcist.mvpsimple.module.viewer.adapter;

import com.akexorcist.mvpsimple.bus.BusProvider;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class FeedItemPresenter implements FeedItemContractor.Presenter {
    private FeedItemContractor.View viewFeedItemContractor;

    public static FeedItemPresenter createPresenter(FeedItemContractor.View viewFeedItemContractor) {
        return new FeedItemPresenter(viewFeedItemContractor);
    }

    public FeedItemPresenter(FeedItemContractor.View viewFeedItemContractor) {
        this.viewFeedItemContractor = viewFeedItemContractor;
        viewFeedItemContractor.setPresenter(this);
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
