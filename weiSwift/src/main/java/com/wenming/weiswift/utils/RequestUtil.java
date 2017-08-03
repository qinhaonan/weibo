package com.wenming.weiswift.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by qhn on 2017/7/18.
 */

public class RequestUtil {

    public static void requestGet( String url, RequestCallBack callBack) {
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(0);
        httpUtils.send(HttpRequest.HttpMethod.GET,url,null,callBack);
    }
    public static void requestPost( RequestParams params, String url, RequestCallBack callBack) {
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST,url,params,callBack);
    }
}
