package com.net.app.interfaces;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;

import okhttp3.Headers;

public interface EndpointRequest {
    @JsonIgnore
    public Headers getHeaders();

    @JsonIgnore
    public String getResponseCharset();

    @JsonIgnore
    public String getProtocal();

    @JsonIgnore
    public String getHost();

    @JsonIgnore
    public int getPort();

    @JsonIgnore
    public String getPath(); // {"/PointsSOA"} ,没有配置路径的直接返回空就行

    @JsonIgnore
    public boolean isShowJSON();

    @JsonIgnore
    public boolean isShowLoading();

    @JsonIgnore
    public boolean isJsonData();
}
