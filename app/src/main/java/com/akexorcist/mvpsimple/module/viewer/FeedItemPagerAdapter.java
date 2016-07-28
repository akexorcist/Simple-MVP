package com.akexorcist.mvpsimple.module.viewer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.akexorcist.mvpsimple.module.viewer.header.FeedImageHeaderFragment;
import com.akexorcist.mvpsimple.module.viewer.info.FeedInfoFragment;
import com.akexorcist.mvpsimple.network.model.PostList;

/**
 * Created by Akexorcist on 7/28/16 AD.
 */
public class FeedItemPagerAdapter extends FragmentStatePagerAdapter {
    private PostList.Item postItem;

    public FeedItemPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setPostItem(PostList.Item postItem) {
        this.postItem = postItem;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return FeedInfoFragment.newInstance(postItem);
        } else if (position == 1) {
            return FeedImageHeaderFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
