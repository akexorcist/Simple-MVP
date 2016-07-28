package com.akexorcist.mvpsimple.module.viewer.info;

import com.akexorcist.mvpsimple.common.BasePresenter;
import com.akexorcist.mvpsimple.common.BaseView;
import com.akexorcist.mvpsimple.network.model.PostList;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class FeedInfoContractor {

    public interface View extends BaseView<Presenter> {
        void setFeedItemTitle(String title);
    }

    public interface Presenter extends BasePresenter {
        void setPostItem(PostList.Item postItem);

        PostList.Item getPostItem();
    }
}
