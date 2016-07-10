package com.akexorcist.mvpsimple.network;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.model.PostList;
import com.akexorcist.mvpsimple.network.model.ResultFailureEvent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class NetworkManager {
    private static NetworkManager networkManager;

    public static NetworkManager getInstance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }
        return networkManager;
    }

    private NetworkService networkService;

    private NetworkService getConnection() {
        if (networkService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Url.BASE)
                    .build();
            networkService = retrofit.create(NetworkService.class);
        }
        return networkService;
    }

    public void getPostList() {
        getConnection().getPostList(Key.BLOGGER_ID, Key.BLOGGER_KEY).enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                BusProvider.getProvider().getBus().post(response);
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                ResultFailureEvent event = new ResultFailureEvent(t);
                BusProvider.getProvider().getBus().post(event);
            }
        });
    }
}
