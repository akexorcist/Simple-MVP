package com.akexorcist.mvpsimple.module.writer;

import com.akexorcist.mvpsimple.common.BasePresenter;
import com.akexorcist.mvpsimple.common.BaseView;
import com.akexorcist.mvpsimple.network.model.BlogInfo;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class BlogInfoContractor {
    public interface Presenter extends BasePresenter {
        void loadBlogInfo();

        BlogInfo getBlogInfo();

        void setBlogInfo(BlogInfo blogInfo);
    }

    public interface View extends BaseView<Presenter> {
        void setBlogId(String id);

        void setBlogName(String name);

        void setBlogDescription(String description);

        void setBlogUrl(String url);

        void setBlogPostCount(String postCount);


        void showPostListLoadingFailure();
    }
}
