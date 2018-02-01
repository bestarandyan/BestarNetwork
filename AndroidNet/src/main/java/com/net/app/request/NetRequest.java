package com.net.app.request;


import com.net.app.interfaces.EndpointRequest;

import java.util.HashMap;

import okhttp3.Headers;

/**
 * Created by liuxingxing on 2017/11/23.
 */

public class NetRequest implements EndpointRequest {
    @Override
    public Headers getHeaders() {
        Headers.Builder builder = new Headers.Builder();
        builder.add("Accept","*/*");
        Headers headers = builder.build();
        return headers;
    }

    @Override
    public String getResponseCharset() {
        return "UTF-8";
    }

    @Override
    public String getProtocal() {
        return null;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public boolean isShowJSON() {
        return false;
    }

    @Override
    public boolean isShowLoading() {
        return false;
    }

    @Override
    public boolean isJsonData() {
        return false;
    }
}
