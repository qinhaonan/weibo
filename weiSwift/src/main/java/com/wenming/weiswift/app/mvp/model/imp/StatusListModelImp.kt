package com.wenming.weiswift.app.mvp.model.imp

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import com.lidroid.xutils.HttpUtils
import com.lidroid.xutils.exception.HttpException
import com.lidroid.xutils.http.ResponseInfo
import com.lidroid.xutils.http.callback.RequestCallBack
import com.lidroid.xutils.http.client.HttpRequest
import com.sina.weibo.sdk.exception.WeiboException
import com.sina.weibo.sdk.net.RequestListener
import com.wenming.weiswift.app.api.GroupAPI
import com.wenming.weiswift.app.api.StatusesAPI
import com.wenming.weiswift.app.common.NewFeature
import com.wenming.weiswift.app.common.entity.Status
import com.wenming.weiswift.app.common.entity.list.StatusList
import com.wenming.weiswift.app.login.AccessTokenKeeper
import com.wenming.weiswift.app.login.Constants
import com.wenming.weiswift.app.mvp.model.StatusListModel
import com.wenming.weiswift.utils.SDCardUtil
import com.wenming.weiswift.utils.ToastUtil
import com.wenming.weiswift.widget.toast.LoadedToast
import io.reactivex.internal.operators.observable.ObservableCreate
import java.util.*

/**
 * Created by wenmingvs on 16/5/14.
 */
class StatusListModelImp : StatusListModel {
    private var mStatusList = ArrayList<Status>()
    private var mContext: Context? = null
    private var mOnDataFinishedUIListener: StatusListModel.OnDataFinishedListener? = null
    private var mOnDestroyWeiBoUIListener: StatusListModel.OnRequestListener? = null
    private var mTimer: Timer? = null
    private var mTimerTask: TimerTask? = null
    /**
     * 当前的分组位置
     */
    private var mCurrentGroup = Constants.GROUP_TYPE_ALL
    /**
     * 是否全局刷新
     */
    private var mRefrshAll = true


    /**
     * 获取当前登录用户及其所关注用户的最新微博。

     * @param context
     * *
     * @param onDataFinishedListener
     */
    override fun friendsTimeline(context: Context, onDataFinishedListener: StatusListModel.OnDataFinishedListener) {
        val mStatusesAPI = StatusesAPI(context, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(context))
        setRefrshFriendsTimelineTask()
        mContext = context
        mOnDataFinishedUIListener = onDataFinishedListener
        val sinceId = checkout(Constants.GROUP_TYPE_ALL)

        val httpUtils = HttpUtils()
        httpUtils.send(HttpRequest.HttpMethod.GET,
                "http://192.168.1.176/thinksns_v3.0/index.php?app=api&mod=Channel&act=get_channel_feed&oauth_token=553cb8005c5dff47cca58aabefd74de7&oauth_token_secret=4dfa52f77ffe6d55fb1039fe70c70436&category_id=1",
                object : RequestCallBack<String>() {
                    override fun onSuccess(responseInfo: ResponseInfo<String>) {
                        val callback = object : RequestListener{
                            override fun onWeiboException(p0: WeiboException?) {
                                myPullToRefreshListener.onWeiboException(p0!!)
                            }

                            override fun onComplete(p0: String?) {
                                myPullToRefreshListener.onComplete(p0!!, responseInfo.result)
                            }
                        }
                        mStatusesAPI.homeTimeline(sinceId, 0, NewFeature.GET_WEIBO_NUMS, 1, false, 0, false, callback)
                    }
                    override fun onFailure(e: HttpException, s: String) {

                    }
                })
    }


    /**
     * 获取双向关注用户的最新微博。

     * @param context
     * *
     * @param onDataFinishedListener
     */
    override fun bilateralTimeline(context: Context, onDataFinishedListener: StatusListModel.OnDataFinishedListener) {
        val mStatusesAPI = StatusesAPI(context, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(context))
        setRefrshFriendsTimelineTask()
        mContext = context
        mOnDataFinishedUIListener = onDataFinishedListener
        val sinceId = checkout(Constants.GROUP_TYPE_FRIENDS_CIRCLE)
        mStatusesAPI.bilateralTimeline(sinceId, 0, NewFeature.GET_WEIBO_NUMS, 1, false, StatusesAPI.FEATURE_ORIGINAL, false, pullToRefreshListener)
    }

    override fun timeline(newGroupId: Long, context: Context, onDataFinishedListener: StatusListModel.OnDataFinishedListener) {
        val groupAPI = GroupAPI(context, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(context))
        setRefrshFriendsTimelineTask()
        mContext = context
        mOnDataFinishedUIListener = onDataFinishedListener
        val sinceId = checkout(newGroupId)
        groupAPI.timeline(newGroupId, sinceId, 0, NewFeature.GET_WEIBO_NUMS, 1, false, GroupAPI.FEATURE_ALL, pullToRefreshListener)
    }


    /**
     * 删除一条微博

     * @param id
     * *
     * @param context
     * *
     * @param onRequestListener
     */
    override fun weibo_destroy(id: Long, context: Context, onRequestListener: StatusListModel.OnRequestListener) {
        val mStatusesAPI = StatusesAPI(context, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(context))
        mContext = context
        mOnDestroyWeiBoUIListener = onRequestListener
        mStatusesAPI.destroy(id, destroyRequestListener)
    }


    /**
     * 获取指定分组的下一页微博

     * @param groundId
     * *
     * @param context
     * *
     * @param onDataFinishedListener
     */
    override fun timelineNextPage(groundId: Long, context: Context, onDataFinishedListener: StatusListModel.OnDataFinishedListener) {
        val groupAPI = GroupAPI(context, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(context))
        mContext = context
        mOnDataFinishedUIListener = onDataFinishedListener
        setRefrshFriendsTimelineTask()
        val maxId = mStatusList[mStatusList.size - 1].id
        groupAPI.timeline(groundId, 0, java.lang.Long.valueOf(maxId)!!, NewFeature.GET_WEIBO_NUMS, 1, false, GroupAPI.FEATURE_ALL, nextPageListener)
    }

    /**
     * 获取我关注的人的下一页微博

     * @param context
     * *
     * @param onDataFinishedListener
     */
    override fun friendsTimelineNextPage(context: Context, onDataFinishedListener: StatusListModel.OnDataFinishedListener) {
        setRefrshFriendsTimelineTask()
        mContext = context
        mOnDataFinishedUIListener = onDataFinishedListener
        val mStatusesAPI = StatusesAPI(context, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(context))
        val maxId = mStatusList[mStatusList.size - 1].id
        mStatusesAPI.homeTimeline(0, java.lang.Long.valueOf(maxId)!!, NewFeature.LOADMORE_WEIBO_ITEM, 1, false, 0, false, nextPageListener)
    }


    /**
     * 获取相互关注的人的下一页微博

     * @param context
     * *
     * @param onDataFinishedListener
     */
    override fun bilateralTimelineNextPage(context: Context, onDataFinishedListener: StatusListModel.OnDataFinishedListener) {
        setRefrshFriendsTimelineTask()
        mContext = context
        mOnDataFinishedUIListener = onDataFinishedListener
        val mStatusesAPI = StatusesAPI(context, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(context))
        val maxId = mStatusList[mStatusList.size - 1].id
        mStatusesAPI.bilateralTimeline(0, java.lang.Long.valueOf(maxId)!!, NewFeature.LOADMORE_WEIBO_ITEM, 1, false, StatusesAPI.FEATURE_ORIGINAL, false, nextPageListener)
    }

    override fun cacheLoad(groupType: Long, context: Context, onDataFinishedListener: StatusListModel.OnDataFinishedListener): Boolean {
        var response: String? = null
        mCurrentGroup = groupType
        mOnDataFinishedUIListener = onDataFinishedListener
        if (groupType == Constants.GROUP_TYPE_ALL) {
            response = SDCardUtil.get(context, SDCardUtil.getSDCardPath() + "/weiSwift/home", "全部微博" + AccessTokenKeeper.readAccessToken(context).uid + ".txt")
        } else if (groupType == Constants.GROUP_TYPE_FRIENDS_CIRCLE) {
            response = SDCardUtil.get(context, SDCardUtil.getSDCardPath() + "/weiSwift/home", "好友圈" + AccessTokenKeeper.readAccessToken(context).uid + ".txt")
        } else {
            response = SDCardUtil.get(context, SDCardUtil.getSDCardPath() + "/weiSwift/home", groupType.toString() + AccessTokenKeeper.readAccessToken(context).uid + ".txt")
        }
        if (response != null) {
            mStatusList = StatusList.parse(response)!!.statuses
            onDataFinishedListener.onDataFinish(mStatusList)
            return true
        } else {
            mOnDataFinishedUIListener!!.noDataInFirstLoad("还没有缓存的内容")
            return false
        }
    }


    override fun cacheSave(groupType: Long, context: Context, statusList: StatusList) {
        val response = Gson().toJson(statusList)
        if (groupType == Constants.GROUP_TYPE_ALL) {
            SDCardUtil.put(context, SDCardUtil.getSDCardPath() + "/weiSwift/home", "全部微博" + AccessTokenKeeper.readAccessToken(context).uid + ".txt", response)
        } else if (groupType == Constants.GROUP_TYPE_FRIENDS_CIRCLE) {
            SDCardUtil.put(context, SDCardUtil.getSDCardPath() + "/weiSwift/home", "好友圈" + AccessTokenKeeper.readAccessToken(context).uid + ".txt", response)
        } else {
            SDCardUtil.put(context, SDCardUtil.getSDCardPath() + "/weiSwift/home", groupType.toString() + AccessTokenKeeper.readAccessToken(context).uid + ".txt", response)
        }
    }


    override fun setRefrshFriendsTimelineTask() {
        if (mTimerTask == null) {
            mTimerTask = object : TimerTask() {
                override fun run() {
                    mRefrshAll = true
                }
            }
            mTimer = Timer()
            mTimer!!.schedule(mTimerTask, 0, REFRESH_FRIENDS_TIMELINE_TASK.toLong())
        }
    }

    override fun cancelTimer() {
        if (mTimer != null) {
            mTimer!!.cancel()
            mTimer = null
        }
        if (mTimerTask != null) {
            mTimerTask!!.cancel()
            mTimerTask = null
        }
    }

    /**
     * 用于更新sinceId和maxId的值

     * @param newGroupId
     * *
     * @return
     */
    private fun checkout(newGroupId: Long): Long {
        var sinceId: Long = 0
        if (mCurrentGroup != newGroupId) {
            mRefrshAll = true
        }
        if (mStatusList.size > 0 && mCurrentGroup == newGroupId && mRefrshAll == false) {
            sinceId = java.lang.Long.valueOf(mStatusList[0].id)!!
        }
        if (mRefrshAll) {
            sinceId = 0
        }
        mCurrentGroup = newGroupId
        return sinceId
    }


    private val myPullToRefreshListener = object {
        fun onComplete(response: String, self: String) {
            val statusList = StatusList.myParse(response,self)
            val temp = statusList!!.statuses
            if (temp != null && temp.size > 0) {
                //请求回来的数据的maxid与列表中的第一条的id相同，说明是局部刷新，否则是全局刷新
                //LoadedToast.showToast(mContext, temp.size() + "条新微博");
                //如果是全局刷新,需要清空列表中的全部微博
                if (mStatusList.size == 0 || statusList.max_id.toString() != mStatusList[0].id) {
                    mStatusList.clear()
                    mStatusList = temp
                } else {
                    //如果是局部刷新
                    mStatusList.addAll(0, temp)
                    //更新对象并且序列化到本地
                    statusList.statuses = mStatusList
                }
                cacheSave(mCurrentGroup, mContext!!, statusList)
                mOnDataFinishedUIListener!!.getNewWeiBo(temp.size)
                mOnDataFinishedUIListener!!.onDataFinish(mStatusList)
                mRefrshAll = false
            } else {
                if (mRefrshAll == false) {//局部刷新，get不到数据
                    LoadedToast.showToast(mContext, "0条新微博")
                    mOnDataFinishedUIListener!!.noMoreData()
                } else {//全局刷新，get不到数据
                    mOnDataFinishedUIListener!!.noDataInFirstLoad("你还没有为此组增加成员")
                }
            }
            mRefrshAll = false

        }
        fun onWeiboException(e: WeiboException ){
            ToastUtil.showShort(mContext, e.message)
            mOnDataFinishedUIListener!!.onError(e.message)
            cacheLoad(mCurrentGroup, mContext!!, mOnDataFinishedUIListener!!)
        }
    }


    private val pullToRefreshListener = object : RequestListener {
        override fun onComplete(response: String) {
            val statusList = StatusList.parse(response)
            val temp = statusList!!.statuses
            if (temp != null && temp.size > 0) {
                //请求回来的数据的maxid与列表中的第一条的id相同，说明是局部刷新，否则是全局刷新
                //LoadedToast.showToast(mContext, temp.size() + "条新微博");
                //如果是全局刷新,需要清空列表中的全部微博
                if (mStatusList.size == 0 || statusList.max_id.toString() != mStatusList[0].id) {
                    mStatusList.clear()
                    mStatusList = temp
                } else {
                    //如果是局部刷新
                    mStatusList.addAll(0, temp)
                    //更新对象并且序列化到本地
                    statusList.statuses = mStatusList
                }
                cacheSave(mCurrentGroup, mContext!!, statusList)
                mOnDataFinishedUIListener!!.getNewWeiBo(temp.size)
                mOnDataFinishedUIListener!!.onDataFinish(mStatusList)
                mRefrshAll = false
            } else {
                if (mRefrshAll == false) {//局部刷新，get不到数据
                    LoadedToast.showToast(mContext, "0条新微博")
                    mOnDataFinishedUIListener!!.noMoreData()
                } else {//全局刷新，get不到数据
                    mOnDataFinishedUIListener!!.noDataInFirstLoad("你还没有为此组增加成员")
                }
            }
            mRefrshAll = false
        }

        override fun onWeiboException(e: WeiboException) {
            ToastUtil.showShort(mContext, e.message)
            mOnDataFinishedUIListener!!.onError(e.message)
            cacheLoad(mCurrentGroup, mContext!!, mOnDataFinishedUIListener!!)
        }
    }


    private val nextPageListener = object : RequestListener {
        override fun onComplete(response: String) {
            if (!TextUtils.isEmpty(response)) {
                val temp = StatusList.parse(response)!!.statuses as ArrayList<Status>
                if (temp.size == 0 || temp != null && temp.size == 1 && temp[0].id == mStatusList[mStatusList.size - 1].id) {
                    mOnDataFinishedUIListener!!.noMoreData()
                } else if (temp.size > 1) {
                    temp.removeAt(0)
                    mStatusList.addAll(temp)
                    mOnDataFinishedUIListener!!.onDataFinish(mStatusList)
                }
            } else {
                mOnDataFinishedUIListener!!.noMoreData()
            }
        }

        override fun onWeiboException(e: WeiboException) {
            ToastUtil.showShort(mContext, e.message)
            mOnDataFinishedUIListener!!.onError(e.message)
        }
    }


    private val destroyRequestListener = object : RequestListener {
        override fun onComplete(s: String) {
            ToastUtil.showShort(mContext, "微博删除成功")
            NewFeature.refresh_profileLayout = true
            mOnDestroyWeiBoUIListener!!.onSuccess()
        }

        override fun onWeiboException(e: WeiboException) {
            ToastUtil.showShort(mContext, "微博删除失败")
            ToastUtil.showShort(mContext, e.toString())
            mOnDestroyWeiBoUIListener!!.onError(e.toString())
        }
    }

    companion object {
        /**
         * 全局刷新的间隔时间
         */
        private val REFRESH_FRIENDS_TIMELINE_TASK = 15 * 60 * 1000
    }
}
