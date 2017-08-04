package com.wenming.weiswift.app.myself.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.common.entity.User;
import com.wenming.weiswift.app.common.entity.WatchListEntity;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.app.myself.fans.adapter.FansAdapter;
import com.wenming.weiswift.widget.endlessrecyclerview.HeaderAndFooterRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by qinhaonan on 2017/8/3.
 */

public class MyWatchListActivity extends BaseAppCompatActivity{
    public SwipeRefreshLayout mSwipeRefreshLayout;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    public RecyclerView mRecyclerView;
    public FansAdapter mAdapter;
    private ArrayList<WatchListEntity> watchList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_watch_list);
        mContext = this;
        requestWatchList();
        initRefreshLayout();
        initRecyclerView();

    }

    private void requestWatchList() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Constants.ZHONGZHIWULIANG_REQUEST_URL+
                "app=api" +
                "&mod=User" +
                "&act=user_following" +
                "&oauth_token=988b491a22040ef7634eb5b8f52e0986" +
                "&oauth_token_secret=2a3d67f5f7bb03035e619518b364912e" ,
                null, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("ssss", "onSuccess: ");
                JsonParser jsonParser=new JsonParser();
                JsonArray jsonArray=jsonParser.parse((String)responseInfo.result).getAsJsonArray();
                Gson gson=new Gson();
                for (JsonElement wathcListArray:jsonArray){
                    WatchListEntity wathcListEntity=gson.fromJson(wathcListArray,WatchListEntity.class);
                    watchList.add(wathcListEntity);
                }
                Log.d("dddsf", "onSuccess: ");
                update();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("ssss", "onFailure: ");
            }
        });
    }

    protected void initRefreshLayout() {

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.exert_swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mFollowerActivityPresent.pullToRefreshData(Long.valueOf(AccessTokenKeeper.readAccessToken(mContext).getUid()), mContext);
//            }
//        });
    }
    public void update(){
        mAdapter.setmWatchListDatas(watchList);
        mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
    }
    public void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.exert_RecyclerView);
        mAdapter = new FansAdapter(watchList, mContext) {
            @Override
            public void followerLayoutClick(User user, int position, ImageView follwerIcon, TextView follwerText) {
            }
        };
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
    }
}
