package com.akexorcist.mvpsimple.module.feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

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

        if(savedInstanceState != null) {
            restoreView(savedInstanceState);
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
        rvPostList.setVisibility(View.GONE);
        layoutLoading.setVisibility(View.VISIBLE);
    }

    private void createPresenter() {
        FeedPresenter.createPresenter(this);
    }

    private void restoreView(Bundle savedInstanceState) {
        feedPresenter.setPostList((PostList) Parcels.unwrap(savedInstanceState.getParcelable(KEY_POST_LIST)));
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
        feedAdapter.setPostItemList(feedPresenter.getPostList().getItemList());
    }

    @Override
    public void showLoading() {
        applyViewFadeIn(layoutLoading);
        applyViewFadeOut(rvPostList);
    }

    @Override
    public void hideLoading() {
        applyViewFadeOut(layoutLoading);
        applyViewFadeIn(rvPostList);
    }

    @Override
    public void setPresenter(FeedContractor.Presenter presenter) {
        this.feedPresenter = presenter;
    }

    private void applyViewFadeIn(View view) {
        AnimationManager.getInstance().applyViewFadeIn(view);
    }

    private void applyViewFadeOut(final View view) {
        AnimationManager.getInstance().applyViewFadeOut(view);
    }

    @Override
    public void onPostItemClick(RecyclerView.ViewHolder viewHolder, PostList.Item postItem, int i) {
        feedPresenter.onItemClick(postItem, i);
    }
}
