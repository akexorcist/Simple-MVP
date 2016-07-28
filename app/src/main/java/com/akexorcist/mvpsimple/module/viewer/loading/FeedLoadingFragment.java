package com.akexorcist.mvpsimple.module.viewer.loading;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.mvpsimple.R;

public class FeedLoadingFragment extends Fragment implements FeedLoadingContractor.View {
    private FeedLoadingContractor.Presenter presenterFeedLoadingContractor;

    public static FeedLoadingFragment newInstance() {
        FeedLoadingFragment fragment = new FeedLoadingFragment();
        return fragment;
    }

    public FeedLoadingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed_loading, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupView();
        createPresenter();

        if (savedInstanceState == null) {
            restoreArgument(getArguments());
            initialize();
        } else {
            restoreInstanceState(savedInstanceState);
            restoreView();
        }
    }

    private void createPresenter() {
        FeedLoadingPresenter.createPresenter(this);
    }

    private void bindView(View view) {
    }

    private void setupView() {

    }

    private void restoreView() {
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
    }

    private void restoreArgument(Bundle bundle) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initialize() {

    }

    @Override
    public void setPresenter(FeedLoadingContractor.Presenter presenter) {
        this.presenterFeedLoadingContractor = presenter;
    }
}
