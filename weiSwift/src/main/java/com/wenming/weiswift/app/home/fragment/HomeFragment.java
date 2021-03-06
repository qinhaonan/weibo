package com.wenming.weiswift.app.home.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.wenming.weiswift.app.common.BarManager;
import com.wenming.weiswift.app.common.entity.Crop;
import com.wenming.weiswift.app.common.entity.PublicWeiBoEntity;
import com.wenming.weiswift.app.common.entity.Question;
import com.wenming.weiswift.app.common.entity.Status;
import com.wenming.weiswift.app.common.entity.User;
import com.wenming.weiswift.app.common.entity.list.QuestionList;
import com.wenming.weiswift.app.home.activity.SearchActivity;
import com.wenming.weiswift.app.home.adapter.GridPagerAdapter;
import com.wenming.weiswift.app.home.adapter.GridViewAdatpter;
import com.wenming.weiswift.app.home.adapter.ScaleCircleNavigator;
import com.wenming.weiswift.app.home.adapter.WeiboAdapter;
import com.wenming.weiswift.app.home.weiboitem.HomeHeadView;
import com.wenming.weiswift.app.home.weiboitem.TimelineArrowWindow;
import com.wenming.weiswift.app.home.widget.GroupPopWindow;
import com.wenming.weiswift.app.home.widget.IGroupItemClick;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.app.login.fragment.post.bean.WeiBoCreateBean;
import com.wenming.weiswift.app.mvp.presenter.HomeFragmentPresent;
import com.wenming.weiswift.app.mvp.presenter.imp.HomeFragmentPresentImp;
import com.wenming.weiswift.app.mvp.view.HomeFragmentView;
import com.wenming.weiswift.utils.DensityUtil;
import com.wenming.weiswift.utils.DesBase64Tool;
import com.wenming.weiswift.utils.ScreenUtil;
import com.wenming.weiswift.widget.endlessrecyclerview.EndlessRecyclerOnScrollListener;
import com.wenming.weiswift.widget.endlessrecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.wenming.weiswift.widget.endlessrecyclerview.RecyclerViewUtils;
import com.wenming.weiswift.widget.endlessrecyclerview.utils.RecyclerViewStateUtils;
import com.wenming.weiswift.widget.endlessrecyclerview.weight.LoadingFooter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wenmingvs on 16/4/27.
 */
public class HomeFragment extends Fragment implements HomeFragmentView {

    private static final String TAG = "HomeFragment";
    private ArrayList<Status> mDatas;
    public Context mContext;
    public Activity mActivity;
    public View mView;
    public View vp_View;
    private LinearLayout mGroup;
    public RecyclerView mRecyclerView;
    public TextView mUserNameTextView;
    public TextView mErrorMessage;
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public WeiboAdapter mAdapter;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private HomeFragmentPresent mHomePresent;
    private long mCurrentGroup = Constants.GROUP_TYPE_ALL;
    private LinearLayout mEmptyLayout;
    private GroupPopWindow mPopWindow;
    private User mCurrentUser;
    private boolean mComeFromAccoutActivity;
    private String mUserName;
    private Question question[];
    private QuestionList questionList;
    /**
     * 顶部导航栏
     */
    private LinearLayout mTopBar;

    /**
     * 手指滑动距离多少个像素点的距离，才隐藏bar
     */
    private static int sHideThreshold;
    /**
     * 记录手指滑动的距离
     */
    private int mScrolledDistance = 0;
    /**
     * 记录bar是否显示或者隐藏
     */
    private boolean mControlsVisible = true;
    private TextView mToastTv;
    private RelativeLayout mToastBg;
    private onButtonBarListener mOnBottonBarListener;

    ViewPager viewPager;
    //    private ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(mDataList);
    private GridPagerAdapter mGridPagerAdapter;
    private List<View> viewPagerList;
    private HomeHeadView homeHeadView;
    private List<Crop> cropList;
    private RelativeLayout rl_gridview;
    private List<PublicWeiBoEntity.WeiBo> weiBoList;
    private Button btn_search;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        token();
        questionList = new QuestionList();
        mActivity = getActivity();
        mContext = getContext();
        mHomePresent = new HomeFragmentPresentImp(this);
        mComeFromAccoutActivity = getArguments().getBoolean("comeFromAccoutActivity");
        sHideThreshold = DensityUtil.dp2px(mContext, 20);
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.weiboRecyclerView);
        mTopBar = (LinearLayout) mView.findViewById(R.id.toolbar_home);
        mGroup = (LinearLayout) mView.findViewById(R.id.group);
        mUserNameTextView = (TextView) mView.findViewById(R.id.name);
        mEmptyLayout = (LinearLayout) mView.findViewById(R.id.emptydeault_layout);
        mErrorMessage = (TextView) mView.findViewById(R.id.errorMessage);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh_widget);
        mToastTv = (TextView) mView.findViewById(R.id.toast_msg);
        mToastBg = (RelativeLayout) mView.findViewById(R.id.toast_bg);
        homeHeadView = new HomeHeadView(mContext);
//        LinearLayout linearLayout= (LinearLayout) homeHeadView.findViewById(R.id.ll_header);
//        vp_View = inflater.inflate(R.layout.headview_homefragment, container, false);
//        rl_gridview = (RelativeLayout) vp_View.findViewById(R.id.rl_gridview);
        viewPager = (ViewPager) homeHeadView.findViewById(R.id.vp_channel);
        btn_search= (Button) homeHeadView.findViewById(R.id.btn_search);
//        initData2();
        initView();
        //屏蔽tittle的点击事件。
        //  initGroupWindows();
        return mView;
    }

    private void initView() {
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 搜索");
                mActivity.startActivity(new Intent(mContext, SearchActivity.class));
            }
        });
        initRecyclerView();
        initRefreshLayout();
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mHomePresent.refreshUserName(mContext);
                if (mComeFromAccoutActivity) {
                    mHomePresent.firstLoadData(mContext, true);
                } else {
                    mHomePresent.firstLoadData(mContext, mActivity.getIntent().getBooleanExtra("fisrtstart", false));
                }
            }
        });

    }
//未登录获取微吧列表
    private void initData() {
        RequestParams params = new RequestParams();
            params.addBodyParameter("app", "api");
            params.addBodyParameter("mod", "Weiba");
            params.addBodyParameter("act", "get_weibas");
//            params.addBodyParameter("oauth_token", "553cb8005c5dff47cca58aabefd74de7");
            params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
//            params.addBodyParameter("oauth_token_secret", "4dfa52f77ffe6d55fb1039fe70c70436");
            params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpRequest.HttpMethod.POST, Constants.ZHONGZHIWULIANG_REQUEST_URL, params, new RequestCallBack<Object>() {
                @Override
                public void onSuccess(ResponseInfo<Object> responseInfo) {
                    Log.d("PPPP", "onSuccess: " + "成" + responseInfo.result);
                    Type type = new TypeToken<List<Crop>>() {
                    }.getType();
                    Gson gson = new Gson();
                    cropList = gson.fromJson((String) responseInfo.result, type);
                    int count = 0;
                    if (cropList.size()<5){
                        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
                        params.height=DensityUtil.dp2px(mContext,100);
                        viewPager.setLayoutParams(params);
                    }else {
                        RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
                        params.height=DensityUtil.dp2px(mContext,170);
                        viewPager.setLayoutParams(params);
                    }
                    if (cropList.size() % 9 == 0) {
                        count = cropList.size() / 9;
                    } else {
                        count = cropList.size() / 9 +1;
                    }
                    initGridView(count);
                    viewPager.setAdapter(mGridPagerAdapter);
                    initMagicIndicator1(count);
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Log.d("PPPP", "onFailure: " + s);
                }
            });


    }
    private void upLoadd(WeiBoCreateBean weiBoCreateBean) {
        RequestParams params = new RequestParams();
        HttpUtils httpUtils = new HttpUtils();
        params.addBodyParameter("app","api");
        params.addBodyParameter("mod","WeiboStatuses");
        params.addBodyParameter("act","upload");
        params.addBodyParameter("oauth_token","52ee71b27bd89de1f558802a5b0378d6");
//        params.addBodyParameter("oauth_token","988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("content",weiBoCreateBean.content);
        params.addBodyParameter("oauth_token_secret","210ba54bf78f700c36653088fa00e705");
//        params.addBodyParameter("oauth_token_secret","2a3d67f5f7bb03035e619518b364912e");
        params.addBodyParameter("form","2");
        params.addBodyParameter("file",new File( weiBoCreateBean.selectImgList.get(0).getImageFile().getAbsolutePath()));
        params.addBodyParameter("XDEBUG_SESSION_START","17481");
        httpUtils.send(HttpRequest.HttpMethod.POST,Constants.ZHONGZHIWULIANG_REQUEST_URL ,params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("PPPP", "onSuccess: "+"成功了"+responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("PPPP", "onFailure: "+s);
            }
        });
    }

    //如果登录,获取关注的微吧
    private void initData2(){
        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "Tom");
        params.addBodyParameter("act", "get_follow_weiba");
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, Constants.ZHONGZHIWULIANG_REQUEST_URL, params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("PPPP", "onSuccess: " + "成" + responseInfo.result);
                if (responseInfo.result.equals("[]")) {
                    Toast.makeText(mContext,"请求关注作物列表失败",Toast.LENGTH_SHORT).show();
                } else {
                    Type type = new TypeToken<List<Crop>>() {
                    }.getType();
                    Gson gson = new Gson();
                    cropList = gson.fromJson((String) responseInfo.result, type);
                    int count = 0;
                    if (cropList.size() < 5) {
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
                        params.height = DensityUtil.dp2px(mContext, 100);
                        viewPager.setLayoutParams(params);
                    } else {
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
                        params.height = DensityUtil.dp2px(mContext, 170);
                        viewPager.setLayoutParams(params);
                    }
                    if (cropList.size() % 9 == 0) {
                        count = cropList.size() / 9;
                    } else {
                        count = cropList.size() / 9 + 1;
                    }
                    initGridView(count);
                    viewPager.setAdapter(mGridPagerAdapter);
                    initMagicIndicator1(count);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("PPPP", "onFailure: " + s);
            }
        });
    }
    private void token() {
        DesBase64Tool desBase64Tool = new DesBase64Tool();

        Log.d(TAG, "token: 名字" + desBase64Tool.desEncrypt("admin@admin.com", desBase64Tool.paddingkey("THINKSNS")));
//        Log.d(TAG, "token: 密码" + desBase64Tool.desEncrypt(desBase64Tool.md5("123123"), desBase64Tool.paddingkey("THINKSNS")));
        Log.d(TAG, "token: 密码" + desBase64Tool.desEncrypt(desBase64Tool.md5("123456"), desBase64Tool.paddingkey("THINKSNS")));
    }

    private void initGridView(int j) {
        viewPagerList = new ArrayList<View>();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        int k=0;
        boolean isLastPager=false;
        for (int i = j ; 0 < i; i--) {
            if(i==1){
                isLastPager=true;
            }
            GridView gridView = (GridView) layoutInflater.inflate(R.layout.gridview, null);
            gridView.setAdapter(new GridViewAdatpter(mContext,k, cropList,isLastPager));
            viewPagerList.add(gridView);
            k++;
        }
        mGridPagerAdapter = new GridPagerAdapter(viewPagerList);

    }


    @Override
    public void onDestroyView() {
        mHomePresent.cancelTimer();
        if (mPopWindow != null) {
            mPopWindow.onDestory();
        }
        super.onDestroyView();
    }

    public HomeFragment() {
    }

    /**
     * 静态工厂方法需要一个int型的值来初始化fragment的参数，
     * 然后返回新的fragment到调用者
     */
    public static HomeFragment newInstance(boolean comeFromAccoutActivity) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putBoolean("comeFromAccoutActivity", comeFromAccoutActivity);
        homeFragment.setArguments(args);
        return homeFragment;
    }


    public void initRecyclerView() {
        mDatas = new ArrayList<>();
        mAdapter = new WeiboAdapter(mDatas, mContext) {
            @Override
            public void arrowClick(Status status, int position, Bitmap bitmap) {
                TimelineArrowWindow arrowDialog = new TimelineArrowWindow(mContext, mDatas.get(position), mAdapter, position, mUserNameTextView.getText().toString(), bitmap);
                arrowDialog.show();
                int width = ScreenUtil.getScreenWidth(mContext) - DensityUtil.dp2px(mContext, 80);
                arrowDialog.getWindow().setLayout(width, (ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        };
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        RecyclerViewUtils.setHeaderView(mRecyclerView, homeHeadView);

    }


    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHomePresent.pullToRefreshData(mCurrentGroup, mContext);
            }
        });
        mSwipeRefreshLayout.setProgressViewOffset(false, DensityUtil.dp2px(mContext, 10), DensityUtil.dp2px(mContext, 10 + 65));
    }

    private void initGroupWindows() {
        mGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect rect = new Rect();
                getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int statusBarHeight = rect.top;
                mPopWindow = GroupPopWindow.getInstance(mContext, ScreenUtil.getScreenWidth(mContext) * 3 / 5, ScreenUtil.getScreenHeight(mContext) * 2 / 3);
                mPopWindow.showAtLocation(mUserNameTextView, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, mUserNameTextView.getHeight() + statusBarHeight + DensityUtil.dp2px(mContext, 8));
                mPopWindow.setOnGroupItemClickListener(new IGroupItemClick() {
                    @Override
                    public void onGroupItemClick(int position, long groupId, String groupName) {
                        mCurrentGroup = groupId;
                        if (groupId != Constants.GROUP_TYPE_ALL) {
                            setGroupName(groupName);
                        } else {
                            setGroupName(mUserName);
                        }
                        mPopWindow.dismiss();
                        mHomePresent.pullToRefreshData(groupId, mContext);
                    }
                });
                if (mPopWindow.isShowing()) {
                    mPopWindow.scrollToSelectIndex();
                }
            }
        });
    }

    /**
     * 把列表滑动到顶部，refreshDrata为true的话，会同时获取更新的数据
     *
     * @param refreshData
     */
    @Override
    public void scrollToTop(boolean refreshData) {
        mRecyclerView.scrollToPosition(0);
        if (refreshData) {
            mRecyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mHomePresent.pullToRefreshData(mCurrentGroup, mContext);
                }
            });
        }
    }

    @Override
    public void setCurrentUser(User user) {
        mCurrentUser = user;
    }

    @Override
    public void showRecyclerView() {
        if (mSwipeRefreshLayout.getVisibility() != View.VISIBLE) {
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideRecyclerView() {
        if (mSwipeRefreshLayout.getVisibility() != View.GONE) {
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showEmptyBackground(String text) {
        if (mEmptyLayout.getVisibility() != View.VISIBLE) {
            mEmptyLayout.setVisibility(View.VISIBLE);
            mErrorMessage.setText(text);
        }
    }

    @Override
    public void hideEmptyBackground() {
        if (mEmptyLayout.getVisibility() != View.GONE) {
            mEmptyLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void popWindowsDestory() {
        if (mPopWindow != null) {
            mPopWindow.onDestory();
        }
    }

    @Override
    public void showOrangeToast(final int num) {
        Animation animation = new AlphaAnimation(0.5f, 1.0f);
        animation.setDuration(2000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mToastTv.setVisibility(View.VISIBLE);
                mToastTv.setText(num + "条新微博");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mToastTv.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mToastBg.startAnimation(animation);
    }

    @Override
    public void hideOrangeToast() {
        mToastTv.setVisibility(View.GONE);
    }


    @Override
    public void updateListView(ArrayList<Status> statuselist) {
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mDatas = statuselist;
        mAdapter.setData(statuselist);
        mHeaderAndFooterRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingIcon() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideLoadingIcon() {

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void showLoadFooterView() {
        RecyclerViewStateUtils.setFooterViewState(mActivity, mRecyclerView, mDatas.size(), LoadingFooter.State.Loading, null);
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

    //屏蔽首页tittle
    @Override
    public void setGroupName(String userName) {
//        mUserNameTextView.setText(userName);
//        if (mGroup.getVisibility() != View.VISIBLE) {
//            mGroup.setVisibility(View.VISIBLE);
//        }
    }

    /**
     * 设置顶部导航栏的用户名
     *
     * @param userName
     */
    @Override
    public void setUserName(String userName) {
        mUserName = userName;
    }


    private static final int SHOW_THRESHOLD = 80;

    public EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            if (mDatas != null && mDatas.size() > 0) {
                showLoadFooterView();
                mHomePresent.requestMoreData(mCurrentGroup, mContext);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //手指向上滑动
            if (mScrolledDistance > sHideThreshold && mControlsVisible) {
                if (mOnBottonBarListener != null) {
                    hideTopBar();
                    mOnBottonBarListener.hideButtonBar();
                }
                mControlsVisible = false;
                mScrolledDistance = 0;
            }
            //手指向下滑动
            else if (mScrolledDistance < -SHOW_THRESHOLD && !mControlsVisible) {
                if (mOnBottonBarListener != null) {
                    showTopBar();
                    mOnBottonBarListener.showButtonBar();
                }
                mControlsVisible = true;
                mScrolledDistance = 0;
            }
            if ((mControlsVisible && dy > 0) || (!mControlsVisible && dy < 0)) {
                mScrolledDistance += dy;
            }
        }
    };

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            showTopBar();
            mOnBottonBarListener.showButtonBar();
            mControlsVisible = true;
        }
    }

    /**
     * 隐藏底部导航栏
     */
    public void hideTopBar() {
        BarManager barManager = new BarManager();
//        barManager.hideTopBar(mTopBar,mContext);
    }


    /**
     * 显示顶部导航栏
     */
    public void showTopBar() {
        BarManager barManager = new BarManager();
        barManager.showTopBar(mTopBar);
    }

    /**
     * 设置实现
     *
     * @param onBarListener
     */
    public void setOnBarListener(onButtonBarListener onBarListener) {
        this.mOnBottonBarListener = onBarListener;
    }


    /**
     * 因为ButotnBar的布局并不在fragment中，而是在MainActivity中，所有隐藏和显示底部导航栏的工作要交给MainActivity去做
     */
    public interface onButtonBarListener {
        void showButtonBar();

        void hideButtonBar();
    }

    public User getCurrentUser() {
        return mCurrentUser;
    }

    //指示圆点
    private void initMagicIndicator1(int count) {
        MagicIndicator magicIndicator = (MagicIndicator) homeHeadView.findViewById(R.id.magic_indicator1);
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(mContext);
        scaleCircleNavigator.setCircleCount(count);
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.DKGRAY);
        scaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                viewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }
    public void initWeiBoData() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "WeiboStatuses");
        params.addBodyParameter("act", "public_timeline");
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
//        params.addBodyParameter("oauth_token", "553cb8005c5dff47cca58aabefd74de7");
        params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
//        params.addBodyParameter("oauth_token_secret", "4dfa52f77ffe6d55fb1039fe70c70436");
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, Constants.ZHONGZHIWULIANG_REQUEST_URL,
                null, new RequestCallBack<Object>() {
                    @Override
                    public void onSuccess(ResponseInfo<Object> responseInfo) {
                        Log.d("PostService", "onSuccess:  成功" + responseInfo.result);

                        PublicWeiBoEntity publicWeiBoEntity = new PublicWeiBoEntity();
                        Gson gson=new Gson();
                        Type weiBoType = new TypeToken<HashMap<String, PublicWeiBoEntity.WeiBo>>() {}.getType();
                        publicWeiBoEntity.weiBoMap = gson.fromJson((String) responseInfo.result, weiBoType);
                        weiBoList = new ArrayList<PublicWeiBoEntity.WeiBo>();
                        for (PublicWeiBoEntity.WeiBo v : publicWeiBoEntity.weiBoMap.values()){
                            weiBoList.add(v);
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.d("PPP", "onFailure:   失败  " + s);
                    }
                });
//        RequestUtil.request(httpUtils,"http://192.168.1.176/thinksns_v3.0/index.php?app=api&mod=Channel&act=get_all_channel&oauth_token=553cb8005c5dff47cca58aabefd74de7&oauth_token_secret=4dfa52f77ffe6d55fb1039fe70c70436"

//        dataList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            dataList.add("苹果" + String.valueOf(i));
//        }
    }


}
