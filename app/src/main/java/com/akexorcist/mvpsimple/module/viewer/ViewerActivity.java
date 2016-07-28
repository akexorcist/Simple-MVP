package com.akexorcist.mvpsimple.module.viewer;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.network.model.PostList;

import org.parceler.Parcels;

public class ViewerActivity extends AppCompatActivity implements ViewerContractor.View, View.OnClickListener {
    public static final String KEY_POST_ITEM = "key_post_item";
    private ViewerContractor.Presenter presenterViewerContractor;
    private Button btnNext;
    private Button btnPrevious;
    private ViewPager vpFeedViewer;
    private FeedItemPagerAdapter feedItemPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        bindView();
        setupView();
        createPresenter();

        if (savedInstanceState != null) {
            restoreInstanceState(savedInstanceState);
            restoreView();
        } else {
            restoreArgument(getIntent().getExtras());
            initialize();
        }
    }

    private void initialize() {

    }

    private void restoreArgument(Bundle bundle) {
        presenterViewerContractor.setPostItem((PostList.Item) Parcels.unwrap(bundle.getParcelable(KEY_POST_ITEM)));
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        presenterViewerContractor.setPostItem((PostList.Item) Parcels.unwrap(savedInstanceState.getParcelable(KEY_POST_ITEM)));
    }

    private void restoreView() {
        vpFeedViewer.setAdapter(feedItemPagerAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void createPresenter() {
        ViewerPresenter.createPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterViewerContractor.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenterViewerContractor.stop();
    }

    public void bindView() {
        btnNext = (Button) findViewById(R.id.btn_next);
        btnPrevious = (Button) findViewById(R.id.btn_previous);
        vpFeedViewer = (ViewPager) findViewById(R.id.vp_feed_viewer);
    }

    public void setupView() {
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);

        feedItemPagerAdapter = new FeedItemPagerAdapter(getSupportFragmentManager());
//        vpFeedViewer.setAdapter(feedItemPagerAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == btnNext) {
            presenterViewerContractor.onButtonNextClicked();
        } else if (view == btnPrevious) {
            presenterViewerContractor.onButtonPreviousClicked();
        }
    }

    @Override
    public void setPresenter(ViewerContractor.Presenter presenter) {
        this.presenterViewerContractor = presenter;
    }

    @Override
    public void setFeedItemAdapter(final PostList.Item postItem) {
        feedItemPagerAdapter.setPostItem(postItem);
        vpFeedViewer.setAdapter(feedItemPagerAdapter);
    }
}
