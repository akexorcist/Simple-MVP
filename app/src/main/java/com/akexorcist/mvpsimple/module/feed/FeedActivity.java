package com.akexorcist.mvpsimple.module.feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.akexorcist.mvpsimple.R;

public class FeedActivity extends AppCompatActivity implements FeedContractor.View {

    private FeedContractor.Presenter feedPresenter;

    private RecyclerView rvPostList;
    private FeedAdapter feedAdapter;

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
    }

    private void setupView() {
        feedAdapter = new FeedAdapter();
        rvPostList.setAdapter(feedAdapter);
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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setPresenter(FeedContractor.Presenter presenter) {
        this.feedPresenter = presenter;
    }
}
