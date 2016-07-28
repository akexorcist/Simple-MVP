package com.akexorcist.mvpsimple.module.viewer;

import com.akexorcist.mvpsimple.common.BasePresenter;
import com.akexorcist.mvpsimple.common.BaseView;
import com.akexorcist.mvpsimple.network.model.PostList;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class ViewerContractor {

    public interface View extends BaseView<Presenter> {
        void setFeedItemAdapter(PostList.Item postItem);
    }

    public interface Presenter extends BasePresenter {
        void setPostItem(PostList.Item postItem);

        void onButtonAddClicked();
    }

}
