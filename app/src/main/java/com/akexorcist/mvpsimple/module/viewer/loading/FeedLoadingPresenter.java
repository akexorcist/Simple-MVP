package com.akexorcist.mvpsimple.module.viewer.loading;

import com.akexorcist.mvpsimple.bus.BusProvider;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class FeedLoadingPresenter implements FeedLoadingContractor.Presenter {
    private FeedLoadingContractor.View viewFeedLoadingContractor;

    public static FeedLoadingPresenter createPresenter(FeedLoadingContractor.View viewFeedLoadingContractor) {
        return new FeedLoadingPresenter(viewFeedLoadingContractor);
    }

    public FeedLoadingPresenter(FeedLoadingContractor.View viewFeedLoadingContractor) {
        this.viewFeedLoadingContractor = viewFeedLoadingContractor;
        viewFeedLoadingContractor.setPresenter(this);
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
