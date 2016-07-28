package com.akexorcist.mvpsimple.module.feed.adapter;

import com.akexorcist.mvpsimple.common.BasePresenter;
import com.akexorcist.mvpsimple.common.BaseView;
import com.akexorcist.mvpsimple.network.model.PostList;

import java.util.List;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class FeedListContractor {
    public interface Presenter extends BasePresenter {
        void setPostItemList(List<PostList.Item> postItemList);

        String getPostItemTitleByPosition(int position);

        String getPostItemImageUrlByPosition(int position);

        PostList.Item getPostItemByPosition(int position);

        int getPostItemCount();
    }

    public interface View extends BaseView<Presenter> {
        void updateData();
    }
}
