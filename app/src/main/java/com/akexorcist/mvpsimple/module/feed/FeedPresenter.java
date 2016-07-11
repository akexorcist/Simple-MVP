package com.akexorcist.mvpsimple.module.feed;

import android.util.Log;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.NetworkManager;
import com.akexorcist.mvpsimple.network.model.PostList;
import com.akexorcist.mvpsimple.network.model.ResultFailureEvent;
import com.squareup.otto.Subscribe;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class FeedPresenter implements FeedContractor.Presenter {

    private final FeedContractor.View viewFeedContractor;
    private PostList postList;

    public static void createPresenter(FeedContractor.View viewFeedContractor) {
        new FeedPresenter(viewFeedContractor);
    }

    private FeedPresenter(FeedContractor.View viewFeedContractor) {
        this.viewFeedContractor = viewFeedContractor;
        viewFeedContractor.setPresenter(this);
    }

    @Override
    public void loadPostList() {
        NetworkManager.getInstance().getPostList();
        viewFeedContractor.showLoading();
    }

    @Override
    public void start() {
        BusProvider.getProvider().getBus().register(this);
        loadPostList();
    }

    @Override
    public void stop() {
        BusProvider.getProvider().getBus().unregister(this);
    }

    @Override
    public PostList getPostList() {
        return postList;
    }

    @Override
    public void onItemClick(PostList.Item postItem, int position) {
        Log.e("Check", "Title : " + postItem.getTitle());
    }

    @Subscribe
    public void onPostListResult(PostList postList) {
        this.postList = postList;
        viewFeedContractor.updatePostList();
        viewFeedContractor.hideLoading();
    }

    @Subscribe
    public void onResultFailureEvent(ResultFailureEvent event) {
        Log.e("Check", "onResultFailureEvent");
    }
}
