package com.wenming.weiswift.app.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.api.StatusesAPI;
import com.wenming.weiswift.app.common.FillContent;
import com.wenming.weiswift.app.common.base.BaseSwipeActivity;
import com.wenming.weiswift.app.common.entity.Expert;
import com.wenming.weiswift.app.common.entity.ExpertCategoryEnity;
import com.wenming.weiswift.app.common.entity.User;
import com.wenming.weiswift.app.home.widget.SpinnerPopWindow;
import com.wenming.weiswift.app.login.AccessTokenKeeper;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.app.mvp.presenter.FollowerActivityPresent;
import com.wenming.weiswift.app.mvp.presenter.imp.FollowerActivityPresentImp;
import com.wenming.weiswift.app.mvp.view.FollowActivityView;
import com.wenming.weiswift.app.myself.fans.adapter.FansAdapter;
import com.wenming.weiswift.widget.endlessrecyclerview.EndlessRecyclerOnScrollListener;
import com.wenming.weiswift.widget.endlessrecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.wenming.weiswift.widget.endlessrecyclerview.utils.RecyclerViewStateUtils;
import com.wenming.weiswift.widget.endlessrecyclerview.weight.LoadingFooter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by qhn on 2017/7/12.
 */

public class ExpertActivity extends BaseSwipeActivity implements FollowActivityView {

    private static final String TAG = "ExpertActivity";
    public FansAdapter mAdapter;
    private ArrayList<Expert.ExpertBean> mDatas;
    public Context mContext;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public RecyclerView mRecyclerView;
    public StatusesAPI mStatusesAPI;
    public boolean mRefrshAllData;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private FollowerActivityPresent mFollowerActivityPresent;
    private SpinnerPopWindow<String> mSpinnerPopWindow;
    private SpinnerPopWindow<String> mSpinnerPopWindow2;
    private List<String> list;
    private List<String> sortList;
    private TextView tv_expert_type;
    private LinearLayout ll_expert;
    private TextView tv_sort;
    private List<Expert.ExpertBean> expertBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_layout);
        initData();

        mContext = this;
        mFollowerActivityPresent = new FollowerActivityPresentImp(this);
        initRefreshLayout();
        initRecyclerView();
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mFollowerActivityPresent.pullToRefreshData(Long.valueOf(AccessTokenKeeper.readAccessToken(mContext).getUid()), mContext);
            }
        });

    }


    protected void initRefreshLayout() {
        mRefrshAllData = true;
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.exert_swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mFollowerActivityPresent.pullToRefreshData(Long.valueOf(AccessTokenKeeper.readAccessToken(mContext).getUid()), mContext);
            }
        });
    }


    public void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.exert_RecyclerView);
        mAdapter = new FansAdapter(mDatas, mContext) {
            @Override
            public void followerLayoutClick(User user, int position, ImageView follwerIcon, TextView follwerText) {
//                follwerIcon.setImageResource(R.drawable.bga_refresh_loading02);
//                follwerText.setText("");
//                if (user.following) {
//                    mFollowerActivityPresent.user_destroy(user, mContext, follwerIcon, follwerText);
//                } else {
//                    mFollowerActivityPresent.user_create(user, mContext, follwerIcon, follwerText);
//                }
            }
        };
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
    }


    public EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            if (mDatas != null && mDatas.size() > 0) {
                showLoadFooterView();
                mFollowerActivityPresent.requestMoreData(Long.valueOf(AccessTokenKeeper.readAccessToken(mContext).getUid()), mContext);
            }
        }
    };

    public void onArrorClick(View view) {
        finish();
    }

    @Override
    public void updateListView(ArrayList<User> userlist) {
        mRecyclerView.addOnScrollListener(mOnScrollListener);
//        mDatas = userlist;
//        mAdapter.setData(userlist);
//        mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
    }
    public void update(List<Expert.ExpertBean> expertBeen){
        expertBeanList=expertBeen;
        mAdapter.setExpertData(expertBeen);
        mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingIcon() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingIcon() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFooterView() {
        RecyclerViewStateUtils.setFooterViewState(ExpertActivity.this, mRecyclerView, mDatas.size(), LoadingFooter.State.Loading, null);
    }

    @Override
    public void hideFooterView() {
        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
    }

    @Override
    public void showEndFooterView() {
        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.TheEnd);
    }

    @Override
    public void showErrorFooterView() {
        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.NetWorkError);
    }

    @Override
    public void updateRealtionShip(Context context, User user, ImageView icon, TextView text) {
        FillContent.updateRealtionShip(context, user, icon, text);
    }

    //    专家类型 和智能排序
    private void initPopUpWindow() {

        initData2();
        tv_expert_type = (TextView) findViewById(R.id.tv_expert_type);
        ll_expert = (LinearLayout) findViewById(R.id.ll_expert);
        tv_sort = (TextView) findViewById(R.id.tv_sort);
        tv_sort.setOnClickListener(clickListener);
        tv_expert_type.setOnClickListener(clickListener);
        mSpinnerPopWindow2 = new SpinnerPopWindow<String>(this, sortList, itemClickListener2,true);

        mSpinnerPopWindow2.setOnDismissListener(dismissListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_expert_type:
                    mSpinnerPopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    mSpinnerPopWindow.showAsDropDown(ll_expert);
                    break;
                case R.id.tv_sort:
                    mSpinnerPopWindow2.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    mSpinnerPopWindow2.showAsDropDown(ll_expert);
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener itemClickListener2 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinnerPopWindow2.dismiss();
            tv_sort.setText(sortList.get(position));
//            Toast.makeText(ExpertActivity.this, "点击了 排序:"+sortList.get(position) , Toast.LENGTH_LONG).show();
        }
    };

    private PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
//            setTextImage(R.mipmap.ic_launcher);
        }
    };


    private void initData() {

        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "Weiba");
        params.addBodyParameter("act", "get_all_user_cate");
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, Constants.ZHONGZHIWULIANG_REQUEST_URL, params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("Expert", "onSuccess: ");
                initPopUpWindow();
                Gson gson = new Gson();
                final ExpertCategoryEnity expertCategory = gson.fromJson((String) responseInfo.result, ExpertCategoryEnity.class);

                final List<String> list=new ArrayList<String>();
                for (int i = 0; i < expertCategory.getCategory().size(); i++) {
                    list.add( expertCategory.getCategory().get(i).getTitle());
                }
                AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mSpinnerPopWindow.dismiss();
                        tv_expert_type.setText(list.get(position));
//                        Toast.makeText(ExpertActivity.this, "点击了"+list.get(position)+"专家:" , Toast.LENGTH_LONG).show();
                        initExpertData(expertCategory.getCategory().get(position).getUser_official_category_id());
                    }
                };
                mSpinnerPopWindow = new SpinnerPopWindow<String>(ExpertActivity.this, list, itemClickListener,false);
                mSpinnerPopWindow.setOnDismissListener(dismissListener);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("Expert", "onFailure: ");
            }
        });
    }

    private void initData2() {
        sortList = new ArrayList<String>();
        sortList.add("距离");
        sortList.add("采纳数");
        sortList.add("智能排序");
    }
    private void initExpertData(String cid){

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, Constants.ZHONGZHIWULIANG_REQUEST_URL+
                "app=api" +
                "&mod=Weiba" +
                "&act=get_all_user" +
                "&oauth_token=988b491a22040ef7634eb5b8f52e0986" +
                "&oauth_token_secret=2a3d67f5f7bb03035e619518b364912e" +
                "&cid="+cid, null, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d(TAG, "onSuccess: 获取专家");
                Gson gson= new Gson();
//                ExpertEntity expertEntity=gson.fromJson((String)responseInfo.result,ExpertEntity.class);
                Expert expert = new Expert();
                Type expertType = new TypeToken<HashMap<String, Expert.ExpertBean>>() {}.getType();
                expert.expertMap = gson.fromJson((String) responseInfo.result, expertType);
                expertBeanList = new ArrayList<Expert.ExpertBean>();
                for (Expert.ExpertBean v : expert.expertMap.values()){
                    expertBeanList.add(v);
                }
                Log.d(TAG, "onSuccess: "+expertBeanList.get(0));
                update(expertBeanList);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d(TAG, "onFailure: 获取专家");
            }
        });
    }
}
