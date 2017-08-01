package com.wenming.weiswift.app.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.utils.RequestUtil;

/**
 * Created by qhn on 2017/8/1.
 */

public class CommentActivity extends BaseAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comment);
        initView();
        submitComment();
    }

    private void initView() {

    }

    private void submitComment() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "Weiba");
        params.addBodyParameter("act", "comment_post");
        params.addBodyParameter("id", "8");
        params.addBodyParameter("content", );
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
        RequestUtil.requestPost(HttpRequest.HttpMethod.POST, params, Constants.ZHONGZHIWULIANG_REQUEST_URL, new RequestCallBack() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
            if (((String)responseInfo.result).equals("1")){
                Log.d("qwer", "onSuccess: ");
            }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("qwer", "onFailure: ");
            }
        });
    }
}
