package com.akexorcist.mvpsimple.module.viewer.header;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.network.model.PostList;
import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class FeedHeaderFragment extends Fragment implements FeedHeaderContractor.View{
    public static final String KEY_POST_ITEM = "key_post_item";

    private FeedHeaderContractor.Presenter presenterFeedHeaderContractor;
    private ImageView ivPostHeader;

    public static FeedHeaderFragment newInstance(PostList.Item postItem) {
        FeedHeaderFragment fragment = new FeedHeaderFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_POST_ITEM, Parcels.wrap(postItem));
        fragment.setArguments(bundle);
        return fragment;
    }

    public FeedHeaderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_viewer_image_header, container, false);
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
        FeedHeaderPresenter.createPresenter(this);
    }

    private void bindView(View view) {
        ivPostHeader = (ImageView) view.findViewById(R.id.iv_post_header);
    }

    private void setupView() {

    }

    private void restoreView() {
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        presenterFeedHeaderContractor.setPostItem((PostList.Item) Parcels.unwrap(savedInstanceState.getParcelable(KEY_POST_ITEM)));
    }

    private void restoreArgument(Bundle bundle) {
        presenterFeedHeaderContractor.setPostItem((PostList.Item) Parcels.unwrap(bundle.getParcelable(KEY_POST_ITEM)));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_POST_ITEM, Parcels.wrap(presenterFeedHeaderContractor.getPostItem()));
    }

    private void initialize() {

    }

    @Override
    public void setPresenter(FeedHeaderContractor.Presenter presenter) {
        this.presenterFeedHeaderContractor = presenter;
    }

    @Override
    public void setImageHeader(String url) {
        Glide.with(this).load(url).into(ivPostHeader);
    }
}
