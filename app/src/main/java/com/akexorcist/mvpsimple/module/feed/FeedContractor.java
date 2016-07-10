package com.akexorcist.mvpsimple.module.feed;

import com.akexorcist.mvpsimple.network.model.PostList;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class FeedContractor {

    public interface Presenter {
        void loadPostList();
    }

    public interface View {
        void updatePostList(PostList postList);

        void showLoading();

        void hideLoading();
    }

}
