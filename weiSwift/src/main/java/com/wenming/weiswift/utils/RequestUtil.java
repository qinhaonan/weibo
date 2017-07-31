package com.wenming.weiswift.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by qhn on 2017/7/18.
 */

public class RequestUtil {

    public static void requestGet(HttpRequest.HttpMethod method, String url, RequestCallBack callBack) {
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(method,url,null,callBack);
    }
    public static void requestPost(HttpRequest.HttpMethod method, RequestParams params, String url, RequestCallBack callBack) {
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(method,url,params,callBack);
    }
}
