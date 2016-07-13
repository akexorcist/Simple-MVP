package com.akexorcist.mvpsimple.network;

import android.os.Handler;

import com.akexorcist.mvpsimple.bus.BusProvider;
import com.akexorcist.mvpsimple.network.model.PostList;
import com.akexorcist.mvpsimple.network.model.ResultFailureEvent;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);  // <-- this is the important line!

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Url.BASE)
                    .client(httpClient.build())
                    .build();
            networkService = retrofit.create(NetworkService.class);
        }
        return networkService;
    }

    public void getPostList() {
        getConnection().getPostList(Key.BLOGGER_ID, Key.BLOGGER_KEY, false, true).enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, final Response<PostList> response) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (response.code() == 200) {
                            BusProvider.getProvider().getBus().post(response.body());
                        } else {
                            BusProvider.getProvider().getBus().post(response.raw());
                        }
                    }
                }, 2000);
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                ResultFailureEvent event = new ResultFailureEvent(t);
                BusProvider.getProvider().getBus().post(event);
            }
        });
    }
}
