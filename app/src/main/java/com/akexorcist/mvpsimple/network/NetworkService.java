package com.akexorcist.mvpsimple.network;

import com.akexorcist.mvpsimple.network.model.BlogInfo;
import com.akexorcist.mvpsimple.network.model.PostList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public interface NetworkService {

    @GET(Url.GET_POST_LIST)
    Call<PostList> getPostList(@Path("blogId") String blogId,
                               @Query("key") String key,
                               @Query("fetchBodies") boolean isFetchBodies,
                               @Query("fetchImages") boolean isFetchImages);

    @GET(Url.GET_BLOG_INFO)
    Call<BlogInfo> getBlogInfo(@Path("blogId") String blogId,
                               @Query("key") String key);
}
