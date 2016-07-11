package com.akexorcist.mvpsimple.module.feed;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.network.model.PostList;

public class FeedActivity extends AppCompatActivity implements FeedContractor.View, FeedAdapter.OnItemClickListener {

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
//        layoutLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        applyViewFadeOut(layoutLoading);
        applyViewFadeIn(rvPostList);
//        layoutLoading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPresenter(FeedContractor.Presenter presenter) {
        this.feedPresenter = presenter;
    }

    private void applyViewFadeIn(View view) {
        Animator fadeInAnimator = AnimatorInflater.loadAnimator(this, R.animator.animator_fade_in);
        fadeInAnimator.setTarget(view);
        fadeInAnimator.start();
        view.setVisibility(View.VISIBLE);
    }

    private void applyViewFadeOut(final View view) {
        Animator fadeInAnimator = AnimatorInflater.loadAnimator(this, R.animator.animator_fade_out);
        fadeInAnimator.setTarget(view);
        fadeInAnimator.start();
        fadeInAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    @Override
    public void onPostItemClick(RecyclerView.ViewHolder viewHolder, PostList.Item postItem, int i) {
        feedPresenter.onItemClick(postItem, i);
    }
}
