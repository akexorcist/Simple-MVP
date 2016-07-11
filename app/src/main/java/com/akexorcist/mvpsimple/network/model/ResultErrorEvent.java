package com.akexorcist.mvpsimple.network.model;

import okhttp3.Response;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class ResultErrorEvent {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
