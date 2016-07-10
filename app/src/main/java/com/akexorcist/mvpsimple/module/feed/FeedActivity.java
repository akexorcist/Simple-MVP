package com.akexorcist.mvpsimple.module.feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akexorcist.mvpsimple.network.model.PostList;

public class FeedActivity extends AppCompatActivity implements FeedContractor.View {

    private FeedContractor.Presenter feedPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.akexorcist.mvpsimple.R.layout.activity_feed);

        createPresenter();
    }

    private void createPresenter() {
        FeedPresenter feedPresenter = new FeedPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        feedPresenter.start();

    }

    @Override
    public void updatePostList(PostList postList) {

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
        Log.e("CHeck", "Set Presenter");
    }
}
