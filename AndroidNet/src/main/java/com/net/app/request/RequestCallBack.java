package com.net.app.request;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.net.app.interfaces.ErrorResponse;
import com.net.app.interfaces.RequestInterface;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liuxingxing on 2017/12/13.
 */

public class RequestCallBack<T> implements Callback {
    RequestInterface requestInterface;
    Class<T> clazz;

    public RequestCallBack(RequestInterface requestInterface,Class<T> clazz){
        this.requestInterface = requestInterface;
        this.clazz = clazz;
    }
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Boolean isSuccess = response.isSuccessful();
        if (isSuccess) {
            String json = response.body().string();
            Log.e("requestTag", json + "请求结果");
            try {
                JSONObject data = new JSONObject(json.trim());
                Log.e("dataMessage ====== ",data.get("message").toString());


                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                T object = objectMapper.readValue(json, clazz);

                requestInterface.onReceivedData(object);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("request  error", e.getMessage());
            }

        } else {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.errorCode = 1;
            errorResponse.msg = "请求失败";
            requestInterface.onErrorData(errorResponse);
        }
    }
}
