package com.akexorcist.mvpsimple.module.viewer;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.model.PostList;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class ViewerPresenter implements ViewerContractor.Presenter {
    private ViewerContractor.View viewViewerContractor;

    public static ViewerPresenter createPresenter(ViewerContractor.View viewViewerContractor) {
        return new ViewerPresenter(viewViewerContractor);
    }

    public ViewerPresenter(ViewerContractor.View viewViewerContractor) {
        this.viewViewerContractor = viewViewerContractor;
        viewViewerContractor.setPresenter(this);
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
        viewViewerContractor.setFeedItemAdapter(postItem);
    }

    @Override
    public void onButtonAddClicked() {

    }
}
