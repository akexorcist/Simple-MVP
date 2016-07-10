package com.akexorcist.mvpsimple.module.feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akexorcist.mvpsimple.R;
import com.akexorcist.mvpsimple.network.model.PostList;

import java.util.List;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PostList.Item> postItemList;

    public FeedAdapter() {
    }

    public void setPostItemList(List<PostList.Item> postItemList) {
        this.postItemList = postItemList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_post_simple_item, viewGroup, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FeedViewHolder) {
            FeedViewHolder feedViewHolder = (FeedViewHolder) viewHolder;
            PostList.Item postItem = postItemList.get(i);
            String url = getPostImage(postItem);
            String title = postItem.getTitle();
            feedViewHolder.setPostImage(url);
            feedViewHolder.setPostTitle(title);
        }
    }

    private String getPostImage(PostList.Item postItem) {
        List<PostList.Image> imageList = postItem.getImageList();
        if (imageList.size() > 0) {
            return imageList.get(0).getUrl();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return (postItemList != null) ? postItemList.size() : 0;
    }
}
