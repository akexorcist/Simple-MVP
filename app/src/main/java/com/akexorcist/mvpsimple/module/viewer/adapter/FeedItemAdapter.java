package com.akexorcist.mvpsimple.module.viewer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.akexorcist.mvpsimple.module.viewer.header.FeedHeaderFragment;
import com.akexorcist.mvpsimple.module.viewer.info.FeedInfoFragment;
import com.akexorcist.mvpsimple.module.viewer.link.FeedLinkFragment;
import com.akexorcist.mvpsimple.module.viewer.loading.FeedLoadingFragment;
import com.akexorcist.mvpsimple.network.model.PostList;

/**
 * Created by Akexorcist on 7/28/16 AD.
 */
public class FeedItemAdapter extends FragmentStatePagerAdapter implements FeedItemContractor.View {
    private FeedItemContractor.Presenter presenterFeedItemContractor;
    private PostList.Item postItem;

    public FeedItemAdapter(FragmentManager fm) {
        super(fm);
        createPresenter();
    }

    public void createPresenter() {
        FeedItemPresenter.createPresenter(this);
    }

    public void setPostItem(PostList.Item postItem) {
        this.postItem = postItem;
    }

    public void onStart() {
        presenterFeedItemContractor.start();
    }

    public void onStop() {
        presenterFeedItemContractor.stop();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return FeedInfoFragment.newInstance(postItem);
        } else if (position == 1) {
            return FeedHeaderFragment.newInstance(postItem);
        } else if (position == 2) {
            return FeedLoadingFragment.newInstance();
        } else if (position == 3) {
            return FeedLoadingFragment.newInstance();
        } else if (position == 4) {
            return FeedLinkFragment.newInstance(postItem);
        }
        return null;
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public void setPresenter(FeedItemContractor.Presenter presenter) {
        this.presenterFeedItemContractor = presenter;
    }
}
