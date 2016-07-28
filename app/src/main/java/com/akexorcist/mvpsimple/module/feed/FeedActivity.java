package com.akexorcist.mvpsimple.module.feed;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.module.viewer.ViewerActivity;
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
            restoreInstanceState(savedInstanceState);
            restoreView();
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
        int columnCount = getResources().getInteger(R.integer.post_list_column_count);
        rvPostList.setLayoutManager(new StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL));
    }

    private void createPresenter() {
        FeedPresenter.createPresenter(this);
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        feedPresenter.setPostList((PostList) Parcels.unwrap(savedInstanceState.getParcelable(KEY_POST_LIST)), true);
    }

    private void restoreView() {
        updatePostList();
    }

    private void initialize() {
        feedPresenter.loadPostList();
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
    protected void onPause() {
        super.onPause();

        feedPresenter.stop();
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
    public void goToFeedViewerActivity(PostList.Item postItem) {
        Intent intent = new Intent(this, ViewerActivity.class);
        intent.putExtra(ViewerActivity.KEY_POST_ITEM, Parcels.wrap(postItem));
        startActivity(intent);
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
