package com.akexorcist.mvpsimple.module.feed.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.module.feed.FeedViewHolder;
import com.akexorcist.mvpsimple.network.model.PostList;

import java.util.List;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class FeedListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements FeedListContractor.View {
    private FeedListContractor.Presenter presenterFeedListContractor;
    private OnItemClickListener onItemClickListener;

    public FeedListAdapter() {
        FeedListPresenter.createPresenter(this);
    }

    public void setPostItemList(List<PostList.Item> postItemList) {
        presenterFeedListContractor.setPostItemList(postItemList);
    }

    public void onStart() {
        presenterFeedListContractor.start();
    }

    public void onStop() {
        presenterFeedListContractor.stop();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_post_simple_item, viewGroup, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FeedViewHolder) {
            final FeedViewHolder feedViewHolder = (FeedViewHolder) viewHolder;
            final int position = viewHolder.getAdapterPosition();
            String url = presenterFeedListContractor.getPostItemImageUrlByPosition(position);
            String title = presenterFeedListContractor.getPostItemTitleByPosition(position);
            feedViewHolder.setPostImage(url);
            feedViewHolder.setPostTitle(title);
            feedViewHolder.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        PostList.Item postItem = presenterFeedListContractor.getPostItemByPosition(position);
                        onItemClickListener.onPostItemClick(feedViewHolder, postItem, position);
                    }
                }
            });
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return presenterFeedListContractor.getPostItemCount();
    }

    @Override
    public void setPresenter(FeedListContractor.Presenter presenter) {
        this.presenterFeedListContractor = presenter;
    }

    @Override
    public void updateData() {
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onPostItemClick(RecyclerView.ViewHolder viewHolder, PostList.Item postItem, int i);
    }
}
