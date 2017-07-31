package com.wenming.weiswift.app.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.SearchView;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.common.entity.Crop;
import com.wenming.weiswift.app.common.entity.QuestionEntity;
import com.wenming.weiswift.utils.DensityUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhn on 2017/7/31.
 */

public class SearchActivity extends BaseAppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);
        initView();
    }

    private void initView() {
        searchView = (SearchView) findViewById(R.id.searchView);
        final Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(
        ) {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                search(query);
                intent.putExtra("key",query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void search(String key) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "WeiboStatuses");
        params.addBodyParameter("act", "weibo_search_weibo");
        params.addBodyParameter("key", key);
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("oauth_token_secret","2a3d67f5f7bb03035e619518b364912e");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, "http://192.168.1.176/thinksns_v3.0/index.php?", params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("PPPP", "onSuccess: " + "成" + responseInfo.result);
                JsonParser jsonParser=new JsonParser();
                JsonArray jsonArray=jsonParser.parse((String)responseInfo.result).getAsJsonArray();
                Gson gson=new Gson();
                ArrayList<QuestionEntity> questionList=new ArrayList<>();
                for (JsonElement question:jsonArray){
                    QuestionEntity questionEntity=gson.fromJson(question,QuestionEntity.class);
                    questionList.add(questionEntity);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("PPPP", "onFailure: " + s);
            }
        });
    }
}
