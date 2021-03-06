package com.wenming.weiswift.app.weibodetail.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.FillContent;
import com.wenming.weiswift.app.common.base.BaseSwipeActivity;
import com.wenming.weiswift.app.common.entity.Comment;
import com.wenming.weiswift.app.common.entity.CommentEntity;
import com.wenming.weiswift.app.common.entity.Status;
import com.wenming.weiswift.app.home.adapter.CommentDetailAdapter;
import com.wenming.weiswift.app.home.adapter.MentionDetailAdapter;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.app.mvp.model.imp.StatusDetailModelImp;
import com.wenming.weiswift.app.mvp.presenter.DetailActivityPresent;
import com.wenming.weiswift.app.mvp.presenter.imp.DetailActivityPresentImp;
import com.wenming.weiswift.app.mvp.view.DetailActivityView;
import com.wenming.weiswift.app.weibodetail.headview.OnDetailButtonClickListener;
import com.wenming.weiswift.utils.RequestUtil;
import com.wenming.weiswift.widget.endlessrecyclerview.EndlessRecyclerOnScrollListener;
import com.wenming.weiswift.widget.endlessrecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.wenming.weiswift.widget.endlessrecyclerview.utils.RecyclerViewStateUtils;
import com.wenming.weiswift.widget.endlessrecyclerview.weight.LoadingFooter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by wenmingvs on 16/4/20.
 */
public abstract class BaseDetailSwipeActivity extends BaseSwipeActivity implements DetailActivityView {

    public Status mStatus;
    public ArrayList<Comment> mCommentDatas = new ArrayList<>();
    public ArrayList<Status> mRepostDatas = new ArrayList<>();
    public CommentDetailAdapter mCommentAdapter;
    public MentionDetailAdapter mRepostAdapter;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public RecyclerView mRecyclerView;
    private int mCurrentGroup = StatusDetailModelImp.COMMENT_PAGE;
    public DetailActivityPresent mDetailActivityPresent;
    public boolean mNoMoreData;
    public Context mContext;
    public int mLastestComments;
    public int mLastestReposts;
    public int mLastestAttitudes;
    private HeaderAndFooterRecyclerViewAdapter mRepostFooterAdapter;
    private HeaderAndFooterRecyclerViewAdapter mCommentFooterAdapter;
    private int lastOffset;
    private int lastPosition;
    public LinearLayout bottombar_retweet;
    public LinearLayout bottombar_comment;
    public LinearLayout bottombar_attitude;
    private ArrayList<CommentEntity> commentlist;
    private String post_id;
    public String comments_count;
    private boolean isRefresh=false;
    private Button btn_collect;
    private Button btn_share;
    private Boolean isCollect=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.messagefragment_base_layout);
        post_id = getIntent().getStringExtra("post_id");
        initCommentData();
        initView();
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getWeiBoCount();
                mDetailActivityPresent.pullToRefreshData(mCurrentGroup, mStatus, mContext);
            }
        });
//        FillContent.fillDetailButtonBar(mContext, mStatus, bottombar_retweet, bottombar_comment, bottombar_attitude);
        FillContent.fillButtonBar(mContext,bottombar_comment,post_id);
    }

    private void initView() {
        //        requsetCommentData();
        btn_collect = (Button) findViewById(R.id.img_collect);
        btn_share = (Button) findViewById(R.id.img_share);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.base_swipe_refresh_widget);
        bottombar_retweet = (LinearLayout) findViewById(R.id.bottombar_retweet);
        bottombar_comment = (LinearLayout) findViewById(R.id.bottombar_comment);
        bottombar_attitude = (LinearLayout) findViewById(R.id.bottombar_attitude);
        TextView tv_toolbar= (TextView) findViewById(R.id.toolbar_username);
        mRecyclerView = (RecyclerView) findViewById(R.id.base_RecyclerView);
        mStatus = getIntent().getParcelableExtra("weiboitem");
        comments_count=getIntent().getStringExtra("comments_count");
        tv_toolbar.setText(mStatus.user.name+"的问题");
        mDetailActivityPresent = new DetailActivityPresentImp(this);
        initRefreshLayout();
        initRecyclerView();
        mLastestComments = mStatus.comments_count;
        mLastestReposts = mStatus.reposts_count;
        mLastestAttitudes = mStatus.attitudes_count;
        btn_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            btn_collect.setSelected(true);
//                Log.d("ddd", "onClick: 收藏");
//                收藏帖子或者取消收藏
                if(!isCollect){
                    collect();
                    btn_collect.setSelected(true);
                    isCollect=true;
                }else {
                    cancelCollect();
                    btn_collect.setSelected(false);
                    isCollect=false;
                }

            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void collect(){
        RequestUtil.requestGet(Constants.ZHONGZHIWULIANG_REQUEST_URL+
                "app=api&" +
                "mod=Weiba&" +
                "act=post_favorite&" +
                "oauth_token=988b491a22040ef7634eb5b8f52e0986&" +
                "oauth_token_secret=2a3d67f5f7bb03035e619518b364912e&" +
                "id="+post_id, new RequestCallBack() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
                if (!((String) responseInfo.result).isEmpty()) {
                    Log.d("tag", "onSuccess: ");
                    Toast.makeText(mContext,"收藏成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext,"收藏失败",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mContext,"收藏失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void cancelCollect(){
        RequestUtil.requestGet(Constants.ZHONGZHIWULIANG_REQUEST_URL+
                "app=api&" +
                "mod=Weiba&" +
                "act=post_unfavorite&" +
                "oauth_token=988b491a22040ef7634eb5b8f52e0986&" +
                "oauth_token_secret=2a3d67f5f7bb03035e619518b364912e&" +
                "id="+post_id, new RequestCallBack() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
                if (!((String) responseInfo.result).isEmpty()) {
                    Toast.makeText(mContext,"取消收藏成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext,"取消收藏失败",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mContext,"取消收藏失败",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void requsetCommentData(){
        String url=Constants.ZHONGZHIWULIANG_REQUEST_URL+"app=api&" +
                "mod=Weiba&" +
                "act=comment_list&" +
                "oauth_token=988b491a22040ef7634eb5b8f52e0986&" +
                "oauth_token_secret=2a3d67f5f7bb03035e619518b364912e&" +
                "id="+post_id;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string=response.body().string();
                System.out.println("我是异步线程,线程Id为:" + Thread.currentThread().getId());
                if (string.isEmpty()) {
                    Log.d("tag", "onSuccess: ");
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = jsonParser.parse(string).getAsJsonArray();
                    Gson gson = new Gson();
                    commentlist = new ArrayList<>();
                    for (JsonElement question : jsonArray) {
                        CommentEntity commentEntity = gson.fromJson(question, CommentEntity.class);
                        commentlist.add(commentEntity);
                    }
                    updata();
                    if(isRefresh) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        isRefresh=false;
                    }
                }else {
                    Toast.makeText(mContext,"请求失败",Toast.LENGTH_SHORT).show();
                }

            }
        });
        for (int i = 0; i < 10; i++) {
            System.out.println("我是主线程,线程Id为:" + Thread.currentThread().getId());
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    //请求帖子下的所有评论
    private void initCommentData() {
        RequestUtil.requestGet(Constants.ZHONGZHIWULIANG_REQUEST_URL+
                        "app=api&" +
                        "mod=Weiba&" +
                        "act=comment_list&" +
                        "oauth_token=988b491a22040ef7634eb5b8f52e0986&" +
                        "oauth_token_secret=2a3d67f5f7bb03035e619518b364912e&" +
                        "id="+post_id, new RequestCallBack() {
                    @Override
                    public void onSuccess(ResponseInfo responseInfo) {
                        if (!((String) responseInfo.result).isEmpty()) {
                            Log.d("tag", "onSuccess: ");
                            JsonParser jsonParser = new JsonParser();
                            JsonArray jsonArray = jsonParser.parse((String) responseInfo.result).getAsJsonArray();
                            Gson gson = new Gson();
                            commentlist = new ArrayList<>();
                            for (JsonElement question : jsonArray) {
                                CommentEntity commentEntity = gson.fromJson(question, CommentEntity.class);
                                commentlist.add(commentEntity);
                            }
                            updata();
                            if(isRefresh) {
                                mSwipeRefreshLayout.setRefreshing(false);
                                isRefresh=false;
                            }
                        }else {
                            Toast.makeText(mContext,"请求失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    protected void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                mNoMoreData = false;
//                getWeiBoCount();
//                mDetailActivityPresent.pullToRefreshData(mCurrentGroup, mStatus, mContext);
                initCommentData();
//                requsetCommentData();
                isRefresh=true;
            }
        });
    }


    public void initRecyclerView() {
        mCommentAdapter = new CommentDetailAdapter(mContext, mCommentDatas);
        mCommentFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(mCommentAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mCommentFooterAdapter);
        addHeaderView(mCurrentGroup);
        refreshDetailBar(mLastestComments, mLastestReposts, mLastestAttitudes);
    }

    public void onArrorClick(View view) {
        finish();
    }

    protected abstract void addHeaderView(int type);

    protected abstract int getHeaderViewHeight();

    /**
     * 异步请求评论，转发，赞数
     */
    public void getWeiBoCount() {
//        mSwipeRefreshLayout.setRefreshing(true);
//        StatusesAPI statusesAPI = new StatusesAPI(mContext, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(mContext));
//        statusesAPI.count(new String[]{mStatus.id}, new RequestListener() {
//            @Override
//            public void onComplete(String response) {
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    mLastestComments = jsonArray.getJSONObject(0).optInt("comments");
//                    mLastestReposts = jsonArray.getJSONObject(0).optInt("reposts");
//                    mLastestAttitudes = jsonArray.getJSONObject(0).optInt("attitudes");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onWeiboException(WeiboException e) {
//                ToastUtil.showShort(mContext, e.getMessage());
//            }
//        });
    }


    protected abstract void refreshDetailBar(int comments, int reposts, int attitudes);


    public OnDetailButtonClickListener onDetailButtonClickListener = new OnDetailButtonClickListener() {
        @Override
        public void OnComment() {
            mNoMoreData = false;
            mCurrentGroup = StatusDetailModelImp.COMMENT_PAGE;
            getWeiBoCount();
            mDetailActivityPresent.pullToRefreshData(mCurrentGroup, mStatus, mContext);
        }

        @Override
        public void OnRetweet() {
            mNoMoreData = false;
            mCurrentGroup = StatusDetailModelImp.REPOST_PAGE;
            getWeiBoCount();
            mDetailActivityPresent.pullToRefreshData(mCurrentGroup, mStatus, mContext);
        }
    };


    @Override
    public void updateRepostListView(ArrayList<Status> mentionlist, boolean resetAdapter) {
//        if (resetAdapter) {
//            mNoMoreData = false;
//            mRepostAdapter = new MentionDetailAdapter(mContext, mentionlist);
//            mRepostFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(mRepostAdapter);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//            mRecyclerView.setLayoutManager(layoutManager);
//            mRecyclerView.setAdapter(mRepostFooterAdapter);
//            addHeaderView(mCurrentGroup);
//            //纠正微博的转发数
//            if (mentionlist.size() > mLastestReposts) {
//                mLastestReposts = mentionlist.size();
//            }
//            refreshDetailBar(mLastestComments, mLastestReposts, mLastestAttitudes);
//        }
//        mRecyclerView.clearOnScrollListeners();
//        mRecyclerView.addOnScrollListener(mScrollListener);
//        mRepostDatas = mentionlist;
//        mRepostAdapter.setData(mentionlist);
//        mRepostFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(mRepostAdapter);
//        mRepostFooterAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateCommentListView(ArrayList<Comment> commentlist, boolean resetAdapter) {
//        if (resetAdapter) {
//            mNoMoreData = false;
//            mCommentAdapter = new CommentDetailAdapter(mContext, commentlist);
//            mCommentFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(mCommentAdapter);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//            mRecyclerView.setLayoutManager(layoutManager);
//            mRecyclerView.setAdapter(mCommentFooterAdapter);
//            addHeaderView(mCurrentGroup);
//            if (commentlist.size() > mLastestComments) {
//                mLastestComments = commentlist.size();
//            }
//            refreshDetailBar(mLastestComments, mLastestReposts, mLastestAttitudes);
//        }
//        mRecyclerView.clearOnScrollListeners();
//        mRecyclerView.addOnScrollListener(mScrollListener);
//        mCommentDatas = commentlist;
//        mCommentAdapter.setData(commentlist);
//        mCommentFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(mCommentAdapter);
//        mCommentFooterAdapter.notifyDataSetChanged();
    }
    public void updata(){
        mCommentAdapter.setCommentData(commentlist);
        mCommentFooterAdapter.notifyDataSetChanged();
    }
//    public void refreshData(){
//        mCommentAdapter.setCommentData(commentlist,mSwipeRefreshLayout);
//        mCommentFooterAdapter.notifyDataSetChanged();
//    }


    public void updateEmptyRepostHeadView() {
//        mNoMoreData = true;
//        mRepostAdapter = new MentionDetailAdapter(mContext, mRepostDatas);
//        mRepostFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(mRepostAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.setAdapter(mRepostFooterAdapter);
//        mRecyclerView.clearOnScrollListeners();
//        addHeaderView(mCurrentGroup);
//        mRepostFooterAdapter.notifyDataSetChanged();
//        refreshDetailBar(mLastestComments, 0, mLastestAttitudes);
    }

    public void updateEmptyCommentHeadView() {
//        mNoMoreData = true;
//        mCommentAdapter = new CommentDetailAdapter(mContext, mCommentDatas);
//        mCommentFooterAdapter = new HeaderAndFooterRecyclerViewAdapter(mCommentAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.setAdapter(mCommentFooterAdapter);
//        mRecyclerView.clearOnScrollListeners();
//        addHeaderView(mCurrentGroup);
//        mCommentFooterAdapter.notifyDataSetChanged();
//        refreshDetailBar(0, mLastestReposts, mLastestAttitudes);
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
    public void showLoadFooterView(int currentGroup) {
        if (currentGroup == StatusDetailModelImp.REPOST_PAGE) {
            RecyclerViewStateUtils.setFooterViewState(BaseDetailSwipeActivity.this, mRecyclerView, mRepostAdapter.getItemCount(), LoadingFooter.State.Loading, null);
        } else if (currentGroup == StatusDetailModelImp.COMMENT_PAGE) {
            RecyclerViewStateUtils.setFooterViewState(BaseDetailSwipeActivity.this, mRecyclerView, mCommentAdapter.getItemCount(), LoadingFooter.State.Loading, null);
        }
    }

    @Override
    public void hideFooterView() {
        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
    }

    @Override
    public void showEndFooterView() {
        mNoMoreData = true;
        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Normal);
    }

    @Override
    public void showErrorFooterView() {
        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.NetWorkError);
    }

    @Override
    public void restoreScrollOffset(boolean refreshData) {
        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(lastPosition, lastOffset);
        if (refreshData) {
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mDetailActivityPresent.pullToRefreshData(mCurrentGroup, mStatus, mContext);
                }
            });
        }
    }


    public EndlessRecyclerOnScrollListener mScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            switch (mCurrentGroup) {
                case StatusDetailModelImp.COMMENT_PAGE:
                    if (!mNoMoreData && mCommentDatas != null && mCommentDatas.size() > 0) {
                        showLoadFooterView(mCurrentGroup);
                        mDetailActivityPresent.requestMoreData(mCurrentGroup, mStatus, mContext);
                    }
                    break;
                case StatusDetailModelImp.REPOST_PAGE:
                    if (!mNoMoreData && mRepostDatas != null && mRepostDatas.size() > 0) {
                        showLoadFooterView(mCurrentGroup);
                        mDetailActivityPresent.requestMoreData(mCurrentGroup, mStatus, mContext);
                    }
                    break;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            View topView = recyclerView.getLayoutManager().getChildAt(0);          //获取可视的第一个view
            if (topView != null) {
                lastOffset = topView.getTop();                                         //获取与该view的顶部的偏移量
                lastPosition = recyclerView.getLayoutManager().getPosition(topView);  //得到该View的数组位置
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (recyclerView != null) {
                View topView = recyclerView.getLayoutManager().getChildAt(0);         //获取可视的第一个view
                if (topView != null) {
                    lastOffset = topView.getTop();                                        //获取与该view的顶部的偏移量
                    lastPosition = recyclerView.getLayoutManager().getPosition(topView);  //得到该View的数组位置
                }
            }


        }
    };
}
