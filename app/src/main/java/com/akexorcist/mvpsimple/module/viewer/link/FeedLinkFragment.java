package com.akexorcist.mvpsimple.module.viewer.link;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.network.model.PostList;

import org.parceler.Parcels;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedLinkFragment extends Fragment implements FeedLinkContractor.View {
    public static final String KEY_POST_ITEM = "key_post_item";
    private FeedLinkContractor.Presenter presenterFeedLinkContractor;

    private TextView tvPostId;
    private TextView tvPostUrl;

    public static FeedLinkFragment newInstance(PostList.Item postItem) {
        FeedLinkFragment fragment = new FeedLinkFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_POST_ITEM, Parcels.wrap(postItem));
        fragment.setArguments(bundle);
        return fragment;
    }

    public FeedLinkFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feed_link, container, false);
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
        FeedLinkPresenter.createPresenter(this);
    }

    private void bindView(View view) {
        tvPostId = (TextView) view.findViewById(R.id.tv_post_id);
        tvPostUrl = (TextView) view.findViewById(R.id.tv_post_url);
    }

    private void setupView() {

    }

    private void restoreView() {
    }

    private void restoreInstanceState(Bundle savedInstanceState) {
        presenterFeedLinkContractor.setPostItem((PostList.Item) Parcels.unwrap(savedInstanceState.getParcelable(KEY_POST_ITEM)));
    }

    private void restoreArgument(Bundle bundle) {
        presenterFeedLinkContractor.setPostItem((PostList.Item) Parcels.unwrap(bundle.getParcelable(KEY_POST_ITEM)));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_POST_ITEM, Parcels.wrap(presenterFeedLinkContractor.getPostItem()));
    }

    private void initialize() {

    }

    @Override
    public void setPresenter(FeedLinkContractor.Presenter presenter) {
        this.presenterFeedLinkContractor = presenter;
    }

    @Override
    public void setPostId(String id) {
        tvPostId.setText(id);
    }

    @Override
    public void setPostUrl(String url) {
        tvPostUrl.setText(url);
    }
}
