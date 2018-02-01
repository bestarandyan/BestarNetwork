package com.net.app.interfaces;

import okhttp3.Response;

/**
 * Created by liuxingxing on 2017/12/5.
 */

public interface RequestInterface<T> {
    void onReceivedData(T response);
    void onErrorData(ErrorResponse response);
}
