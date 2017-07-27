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
import com.wenming.weiswift.app.common.entity.ExpertCategoryEnity;
import com.wenming.weiswift.app.common.entity.ExpertEntity;
import com.wenming.weiswift.app.common.entity.User;
import com.wenming.weiswift.app.home.widget.SpinnerPopWindow;
import com.wenming.weiswift.app.login.AccessTokenKeeper;
import com.wenming.weiswift.app.mvp.presenter.FollowerActivityPresent;
import com.wenming.weiswift.app.mvp.presenter.imp.FollowerActivityPresentImp;
import com.wenming.weiswift.app.mvp.view.FollowActivityView;
import com.wenming.weiswift.app.myself.fans.adapter.FansAdapter;
import com.wenming.weiswift.widget.endlessrecyclerview.EndlessRecyclerOnScrollListener;
import com.wenming.weiswift.widget.endlessrecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.wenming.weiswift.widget.endlessrecyclerview.utils.RecyclerViewStateUtils;
import com.wenming.weiswift.widget.endlessrecyclerview.weight.LoadingFooter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhn on 2017/7/12.
 */

public class ExpertActivity extends BaseSwipeActivity implements FollowActivityView {

    public FansAdapter mAdapter;
    private ArrayList<User> mDatas;
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
    private TextView tvValue;
    private LinearLayout ll_expert;
    private TextView tv_sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expert_layout);
        initData();
        initExpertData();
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
                follwerIcon.setImageResource(R.drawable.bga_refresh_loading02);
                follwerText.setText("");
                if (user.following) {
                    mFollowerActivityPresent.user_destroy(user, mContext, follwerIcon, follwerText);
                } else {
                    mFollowerActivityPresent.user_create(user, mContext, follwerIcon, follwerText);
                }
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
        mDatas = userlist;
        mAdapter.setData(userlist);
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
        tvValue = (TextView) findViewById(R.id.tv_expert_type);
        ll_expert = (LinearLayout) findViewById(R.id.ll_expert);
        tv_sort = (TextView) findViewById(R.id.tv_sort);
        tv_sort.setOnClickListener(clickListener);
        tvValue.setOnClickListener(clickListener);
//        mSpinnerPopWindow2 = new SpinnerPopWindow<String>(this, sortList, itemClickListener2);

//        mSpinnerPopWindow2.setOnDismissListener(dismissListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_expert_type:
                    mSpinnerPopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    mSpinnerPopWindow.showAsDropDown(ll_expert);
//                    setTextImage(R.mipmap.ic_launcher);
                    break;
                case R.id.tv_sort:
//                    mSpinnerPopWindow2.setWidth(tv_sort.getWidth());
//                    mSpinnerPopWindow2.showAsDropDown(tv_sort);
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener itemClickListener2 = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSpinnerPopWindow2.dismiss();
            Toast.makeText(ExpertActivity.this, "点击了 排序:" + list.get(position), Toast.LENGTH_LONG).show();
        }
    };
    private PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
//            setTextImage(R.mipmap.ic_launcher);
        }
    };

    //    private void setTextImage(int resId) {
//        Drawable drawable = getResources().getDrawable(resId);
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(),drawable.getMinimumHeight());// 必须设置图片大小，否则不显示
//        tvValue.setCompoundDrawables(null, null, drawable, null);
//    }
    private void initData() {
//        list = new ArrayList<String>();
//        for (int i = 0; i < 25; i++) {
//            list.add("test:" + i);
//        }
        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "Weiba");
        params.addBodyParameter("act", "get_all_user_cate");
//            params.addBodyParameter("oauth_token", "553cb8005c5dff47cca58aabefd74de7");
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
//            params.addBodyParameter("oauth_token_secret", "4dfa52f77ffe6d55fb1039fe70c70436");
        params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, "http://192.168.2.108/ThinkSNS_V3.0/index.php?", params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("Expert", "onSuccess: ");
                initPopUpWindow();
                Gson gson = new Gson();
                ExpertCategoryEnity expertCategory = gson.fromJson((String) responseInfo.result, ExpertCategoryEnity.class);
                AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mSpinnerPopWindow.dismiss();
                       Toast.makeText(ExpertActivity.this, "点击了 专家:" , Toast.LENGTH_LONG).show();
                    }
                };
                mSpinnerPopWindow = new SpinnerPopWindow<String>(ExpertActivity.this, expertCategory, itemClickListener);
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
    private void initExpertData(){
        RequestParams params=new RequestParams();
        params.addBodyParameter("app","api");
        params.addBodyParameter("mod","Weiba");
        params.addBodyParameter("act","get_all_user");
        params.addBodyParameter("oauth_token","988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("oauth_token_secret","2a3d67f5f7bb03035e619518b364912e");
//        params.addBodyParameter("oauth_token","553cb8005c5dff47cca58aabefd74de7");
//        params.addBodyParameter("oauth_token_secret","4dfa52f77ffe6d55fb1039fe70c70436");
        params.addBodyParameter("cid","1");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, "http://192.168.2.108/thinksns_v3.0/index.php?", params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Gson gson= new Gson();
                ExpertEntity expertEntity=gson.fromJson((String)responseInfo.result,ExpertEntity.class);
                Log.d("tag", "onSuccess: "+expertEntity.getCount());
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }
}
