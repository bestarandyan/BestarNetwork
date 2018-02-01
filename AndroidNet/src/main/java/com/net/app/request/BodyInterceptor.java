package com.net.app.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.net.app.interfaces.EndpointRequest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by liuxingxing on 2017/12/13.
 */

public class BodyInterceptor implements Interceptor {
    ObjectMapper mapper;
    BodyInterceptor(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Object tag = request.tag();
        if (tag!= null && tag instanceof EndpointRequest){
            request = chain.request().newBuilder().post(getBody((EndpointRequest) tag)).build();
        }
        return chain.proceed(request);
    }

    private RequestBody getBody(EndpointRequest request){
        try {
            String body = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request);
            return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),body);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
