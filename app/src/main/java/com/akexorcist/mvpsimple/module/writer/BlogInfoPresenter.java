package com.akexorcist.mvpsimple.module.writer;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.NetworkManager;
import com.akexorcist.mvpsimple.network.model.BlogInfo;
import com.akexorcist.mvpsimple.network.model.ResultFailureEvent;
import com.squareup.otto.Subscribe;

/**
 * Created by Akexorcist on 7/29/16 AD.
 */
public class BlogInfoPresenter implements BlogInfoContractor.Presenter {
    private BlogInfoContractor.View viewWriterInfoContractor;
    private BlogInfo blogInfo;

    public static BlogInfoPresenter createPresenter(BlogInfoContractor.View viewWriterInfoContractor) {
        return new BlogInfoPresenter(viewWriterInfoContractor);
    }

    public BlogInfoPresenter(BlogInfoContractor.View viewWriterInfoContractor) {
        this.viewWriterInfoContractor = viewWriterInfoContractor;
        viewWriterInfoContractor.setPresenter(this);
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
    public void loadBlogInfo() {
        NetworkManager.getInstance().getBlogInfo();
        viewWriterInfoContractor.showLoading(true);
    }

    @Override
    public BlogInfo getBlogInfo() {
        return blogInfo;
    }

    @Override
    public void setBlogInfo(BlogInfo blogInfo, boolean noAnimation) {
        this.blogInfo = blogInfo;
        if (blogInfo != null) {
            String id = blogInfo.getId();
            String name = blogInfo.getName();
            String description = blogInfo.getDescription();
            String url = blogInfo.getUrl();
            String postCount = blogInfo.getPost() != null ? blogInfo.getPost().getTotalItemList() + "" : "Unknown";
            viewWriterInfoContractor.setBlogId(id);
            viewWriterInfoContractor.setBlogName(name);
            viewWriterInfoContractor.setBlogDescription(description);
            viewWriterInfoContractor.setBlogUrl(url);
            viewWriterInfoContractor.setBlogPostCount(postCount);
            viewWriterInfoContractor.hideLoading(noAnimation);
        } else {
            viewWriterInfoContractor.showLoading(noAnimation);
        }
    }

    @Override
    public long getAnimationDuration(boolean noAnimation) {
        return noAnimation ? 0 : -1;
    }

    @Subscribe
    public void onBlogInfoResult(BlogInfo blogInfo) {
        setBlogInfo(blogInfo, false);
    }

    @Subscribe
    public void onResultFailureEvent(ResultFailureEvent event) {
        viewWriterInfoContractor.showPostListLoadingFailure();
    }
}
