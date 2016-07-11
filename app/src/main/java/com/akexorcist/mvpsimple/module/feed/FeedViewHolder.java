package com.akexorcist.mvpsimple.module.feed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akexorcist.mvpsimple.R;
import com.bumptech.glide.Glide;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class FeedViewHolder extends RecyclerView.ViewHolder {
    private TextView tvPostTitle;
    private ImageView ivPostImage;
    private LinearLayout layoutPostItem;

    public FeedViewHolder(View itemView) {
        super(itemView);

        tvPostTitle = (TextView) itemView.findViewById(R.id.tv_post_title);
        ivPostImage = (ImageView) itemView.findViewById(R.id.iv_post_image);
        layoutPostItem = (LinearLayout) itemView.findViewById(R.id.layout_post_item);
    }

    public void setPostTitle(String title) {
        tvPostTitle.setText(title);
    }

    public void setPostImage(String url) {
        if (url != null && !url.isEmpty()) {
            Glide.with(ivPostImage.getContext()).load(url).into(ivPostImage);
        } else {
            ivPostImage.setImageResource(0);
        }
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        layoutPostItem.setOnClickListener(listener);
    }
}
