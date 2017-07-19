package com.wenming.weiswift.utils;

import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by qhn on 2017/7/18.
 */

public class RequestUtil {

    public static String request(HttpUtils httpUtils,String url) {
        final String[] result = new String[1];
        httpUtils.send(HttpRequest.HttpMethod.GET, url,
                null, new RequestCallBack<Object>() {
                    @Override
                    public void onSuccess(ResponseInfo<Object> responseInfo) {
                        Log.d("PostService", "onSuccess:  成功"+responseInfo.result);
                        result[0] = (String) responseInfo.result;
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                        Log.d("PPP", "onFailure:   失败  " + s);
                    }
                });

        return result[0];
    }
}
