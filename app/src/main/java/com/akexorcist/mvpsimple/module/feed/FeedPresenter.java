package com.akexorcist.mvpsimple.module.feed;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.NetworkManager;
import com.akexorcist.mvpsimple.network.model.PostList;
import com.akexorcist.mvpsimple.network.model.ResultFailureEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class FeedPresenter implements FeedContractor.Presenter {

    private final FeedContractor.View viewFeedContractor;
    private PostList postList;

    public static FeedPresenter createPresenter(FeedContractor.View viewFeedContractor) {
        return new FeedPresenter(viewFeedContractor);
    }

    private FeedPresenter(FeedContractor.View viewFeedContractor) {
        this.viewFeedContractor = viewFeedContractor;
        viewFeedContractor.setPresenter(this);
    }

    @Override
    public void loadPostList() {
        NetworkManager.getInstance().getPostList();
        viewFeedContractor.showLoading(true);
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
    public List<PostList.Item> getPostItemList() {
        return postList != null ? postList.getItemList() : null;
    }

    @Override
    public PostList getPostList() {
        return postList;
    }

    @Override
    public void setPostList(PostList postList, boolean noAnimation) {
        if (postList != null) {
            this.postList = postList;
            viewFeedContractor.hideLoading(noAnimation);
        } else {
            viewFeedContractor.showLoading(noAnimation);
        }
    }

    @Override
    public void onItemClick(PostList.Item postItem, int position) {
        String title = postItem.getTitle();
        viewFeedContractor.showSelectedPostTitle(title);
    }

    @Override
    public long getAnimationDuration(boolean noAnimation) {
        return noAnimation ? 0 : -1;
    }

    @Subscribe
    public void onPostListResult(PostList postList) {
        setPostList(postList, false);
        viewFeedContractor.updatePostList();
    }

    @Subscribe
    public void onResultFailureEvent(ResultFailureEvent event) {
        viewFeedContractor.showPostListLoadingFailure();
    }
}
