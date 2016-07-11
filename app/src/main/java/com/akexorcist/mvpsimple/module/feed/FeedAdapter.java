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
    private OnItemClickListener onItemClickListener;

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
            final FeedViewHolder feedViewHolder = (FeedViewHolder) viewHolder;
            final PostList.Item postItem = postItemList.get(i);
            final int position = viewHolder.getAdapterPosition();
            String url = getPostImageUrl(postItem);
            String title = postItem.getTitle();
            feedViewHolder.setPostImage(url);
            feedViewHolder.setPostTitle(title);
            feedViewHolder.setOnItemClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onPostItemClick(feedViewHolder, postItem, position);
                    }
                }
            });
        }
    }

    private String getPostImageUrl(PostList.Item postItem) {
        List<PostList.Image> imageList = postItem.getImageList();
        if (imageList != null && imageList.size() > 0) {
            return imageList.get(0).getUrl();
        }
        return null;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return (postItemList != null) ? postItemList.size() : 0;
    }

    public interface OnItemClickListener {
        void onPostItemClick(RecyclerView.ViewHolder viewHolder, PostList.Item postItem, int i);
    }
}
