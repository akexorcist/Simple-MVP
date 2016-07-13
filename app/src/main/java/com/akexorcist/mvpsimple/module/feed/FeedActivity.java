package com.akexorcist.mvpsimple.module.feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.network.model.PostList;
import com.akexorcist.mvpsimple.utility.AnimationManager;

import org.parceler.Parcels;

public class FeedActivity extends AppCompatActivity implements FeedContractor.View, FeedAdapter.OnItemClickListener {
    private static final String KEY_POST_LIST = "key_post_list";
    private FeedContractor.Presenter feedPresenter;

    private RecyclerView rvPostList;
    private FeedAdapter feedAdapter;
    private LinearLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.akexorcist.mvpsimple.R.layout.activity_feed);

        bindView();
        setupView();
        createPresenter();

        if (savedInstanceState != null) {
            restoreView(savedInstanceState);
        } else {
            initialize();
        }
    }

    private void bindView() {
        rvPostList = (RecyclerView) findViewById(R.id.rv_post_list);
        layoutLoading = (LinearLayout) findViewById(R.id.layout_loading);
    }

    private void setupView() {
        feedAdapter = new FeedAdapter();
        feedAdapter.setOnItemClickListener(this);
        rvPostList.setAdapter(feedAdapter);
        rvPostList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void createPresenter() {
        FeedPresenter.createPresenter(this);
    }

    private void restoreView(Bundle savedInstanceState) {
        Log.e("Check", "restoreView");
        feedPresenter.setPostList((PostList) Parcels.unwrap(savedInstanceState.getParcelable(KEY_POST_LIST)), true);
        updatePostList();
        hideLoading(true);
    }

    private void initialize() {
        feedPresenter.loadPostList();
        showLoading(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_POST_LIST, Parcels.wrap(feedPresenter.getPostList()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        feedPresenter.start();
    }

    @Override
    public void updatePostList() {
        feedAdapter.setPostItemList(feedPresenter.getPostItemList());
    }

    @Override
    public void showPostListLoadingFailure() {
        Toast.makeText(this, R.string.service_unavailable, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSelectedPostTitle(String title) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean noAnimation) {
        applyViewFadeIn(layoutLoading, noAnimation);
        applyViewFadeOut(rvPostList, noAnimation);
    }

    @Override
    public void hideLoading(boolean noAnimation) {
        applyViewFadeOut(layoutLoading, noAnimation);
        applyViewFadeIn(rvPostList, noAnimation);
    }

    @Override
    public void setPresenter(FeedContractor.Presenter presenter) {
        this.feedPresenter = presenter;
    }

    private void applyViewFadeIn(View view, boolean noAnimation) {
        long duration = feedPresenter.getAnimationDuration(noAnimation);
        AnimationManager.getInstance().applyViewFadeIn(view, duration);
    }

    private void applyViewFadeOut(final View view, boolean noAnimation) {
        long duration = feedPresenter.getAnimationDuration(noAnimation);
        AnimationManager.getInstance().applyViewFadeOut(view, duration);
    }

    @Override
    public void onPostItemClick(RecyclerView.ViewHolder viewHolder, PostList.Item postItem, int i) {
        feedPresenter.onItemClick(postItem, i);
    }
}
