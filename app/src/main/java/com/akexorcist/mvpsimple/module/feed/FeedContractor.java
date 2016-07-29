package com.akexorcist.mvpsimple.module.feed;

import com.akexorcist.mvpsimple.common.BasePresenter;
import com.akexorcist.mvpsimple.common.BaseView;
import com.akexorcist.mvpsimple.network.model.PostList;

import java.util.List;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class FeedContractor {

    public interface Presenter extends BasePresenter {
        void loadPostList();

        PostList getPostList();

        void setPostList(PostList postList, boolean noAnimation);

        void onItemClick(PostList.Item postItem, int position);

        long getAnimationDuration(boolean noAnimation);
    }

    public interface View extends BaseView<Presenter> {
        void updatePostItemList(List<PostList.Item> postItemList);

        void showPostListLoadingFailure();

        void showLoading(boolean noAnimation);

        void hideLoading(boolean noAnimation);

        void goToFeedViewerActivity(PostList.Item postItem);
    }

}
