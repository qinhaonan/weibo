package com.wenming.weiswift.app.login.fragment.post;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.api.CommentsAPI;
import com.wenming.weiswift.app.api.StatusesAPI;
import com.wenming.weiswift.app.home.activity.MainActivity;
import com.wenming.weiswift.app.login.AccessTokenKeeper;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.app.login.fragment.post.bean.CommentReplyBean;
import com.wenming.weiswift.app.login.fragment.post.bean.WeiBoCommentBean;
import com.wenming.weiswift.app.login.fragment.post.bean.WeiBoCreateBean;
import com.wenming.weiswift.utils.ToastUtil;

import java.io.File;


/**
 * Created by wenmingvs on 16/5/8.
 */
public class PostService extends Service {


    private Context mContext;
    private NotificationManager mSendNotifity;
    private StatusesAPI mStatusesAPI;

    public String mPostType;

    public static final String POST_SERVICE_REPOST_STATUS = "转发微博";
    public static final String POST_SERVICE_CREATE_WEIBO = "发微博";
    public static final String POST_SERVICE_COMMENT_STATUS = "评论微博";
    public static final String POST_SERVICE_REPLY_COMMENT = "回复评论";


    /**
     * 微博发送成功
     */
    private static final int SEND_STATUS_SUCCESS = 1;
    /**
     * 微博发送失败
     */
    private static final int SEND_STATUS_ERROR = 2;
    /**
     * 微博发送中
     */
    private static final int SEND_STATUS_SEND = 3;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mStatusesAPI = new StatusesAPI(this, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(this));
        mPostType = intent.getStringExtra("postType");
        showSendNotifiy();
        switch (mPostType) {
            case POST_SERVICE_CREATE_WEIBO:
                WeiBoCreateBean createWeiBo = intent.getParcelableExtra("weiBoCreateBean");
                if (createWeiBo.selectImgList == null || createWeiBo.selectImgList.size() == 0) {
                    sendTextContent(createWeiBo);
                } else {
                    sendImgTextContent(createWeiBo);
                }
                break;
            case POST_SERVICE_REPOST_STATUS:
                WeiBoCreateBean repostBean = intent.getParcelableExtra("weiBoCreateBean");
                repost(repostBean);
                break;
            case POST_SERVICE_REPLY_COMMENT:
                CommentReplyBean commentReplyBean = intent.getParcelableExtra("commentReplyBean");
                replyComment(commentReplyBean);
                break;
            case POST_SERVICE_COMMENT_STATUS:
                WeiBoCommentBean weiBoCommentBean = intent.getParcelableExtra("weiBoCommentBean");
                commentWeiBo(weiBoCommentBean);
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 发送图文微博
     */
    private void sendImgTextContent(final WeiBoCreateBean weiBoCreateBean) {
        if (weiBoCreateBean.content.isEmpty() && weiBoCreateBean.status == null) {
            weiBoCreateBean.content = "分享图片";
        } else if (weiBoCreateBean.content.isEmpty() && weiBoCreateBean.status != null) {
            weiBoCreateBean.content = "转发微博";
        }

        DisplayImageOptions imageItemOptions;

        if (new File(weiBoCreateBean.selectImgList.get(0).getImageFile().getAbsolutePath()).length() > 5 * 1024 * 1024) {
            imageItemOptions = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    .imageScaleType(ImageScaleType.NONE)
                    .considerExifParams(true)
                    .build();
        } else {
            imageItemOptions = new DisplayImageOptions.Builder()
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .considerExifParams(true)
                    .build();
        }

//        ImageLoader.getInstance().loadImage("file:///" + weiBoCreateBean.selectImgList.get(0).getImageFile().getAbsolutePath(), imageItemOptions, new SimpleImageLoadingListener() {
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                mStatusesAPI.upload(weiBoCreateBean.content, loadedImage, "0", "0", new RequestListener() {
//                    @Override
//                    public void onComplete(String s) {
//                        onRequestComplete();
//                    }
//
//                    @Override
//                    public void onWeiboException(WeiboException e) {
//                        onRequestError(e, "发送失败");
//                    }
//                });
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                ToastUtil.showShort(mContext, "本地找不到此图片");
//                ToastUtil.showShort(mContext, failReason.getCause().getMessage());
//            }
//        });
        upLoadd(weiBoCreateBean );


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
        for (int i = 0; i <weiBoCreateBean.selectImgList.size() ; i++) {
            params.addBodyParameter("file"+i,new File( weiBoCreateBean.selectImgList.get(i).getImageFile().getAbsolutePath()));
        }
//        params.addBodyParameter("file",new File( weiBoCreateBean.selectImgList.get(0).getImageFile().getAbsolutePath()));
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


    /**
     * 发送纯文字的微博
     */
    private void sendTextContent(WeiBoCreateBean weiBoCreateBean) {
//        mStatusesAPI.update(weiBoCreateBean.content.toString(), "0.0", "0.0", new RequestListener() {
//            @Override
//            public void onComplete(String s) {
//                onRequestComplete();
//            }
//
//            @Override
//            public void onWeiboException(WeiboException e) {
//                onRequestError(e, "发送失败");
//            }
//        });
        HttpUtils httpUtils = new HttpUtils();
//        httpUtils.send(HttpRequest.HttpMethod.POST, "http://192.168.1.176/thinksns_v3.0/index.php?app=api&mod=WeiboStatuses&act=update&oauth_token=553cb8005c5dff47cca58aabefd74de7&content="
        httpUtils.send(HttpRequest.HttpMethod.POST,Constants.ZHONGZHIWULIANG_REQUEST_URL+
                        "app=api&" +
                        "mod=WeiboStatuses&" +
                        "act=update&" +
                        "oauth_token=988b491a22040ef7634eb5b8f52e0986&" +
                        "content=" +weiBoCreateBean.content.toString()+
                        "&oauth_token_secret=2a3d67f5f7bb03035e619518b364912e&" +
                        "form=2&appname=public&" +
                        "app_id=0",
                null, new RequestCallBack<Object>() {
                    @Override
                    public void onSuccess(ResponseInfo<Object> responseInfo) {
                        Log.d("PostService", "onSuccess:  成功");
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {

                        Log.d("PPP", "onFailure:   失败  "+s );
                    }
                });
    }

    /**
     * 转发一条微博
     */
    private void repost(WeiBoCreateBean weiBoCreateBean) {
        mStatusesAPI.repost(Long.valueOf(weiBoCreateBean.status.id), weiBoCreateBean.content.toString(), 0, new RequestListener() {
            @Override
            public void onComplete(String s) {
                onRequestComplete();
            }

            @Override
            public void onWeiboException(WeiboException e) {
                onRequestError(e, "转发失败");
            }
        });
    }

    /**
     * 评论一条微博
     *
     * @param weiBoCommentBean
     */
    public void commentWeiBo(WeiBoCommentBean weiBoCommentBean) {
        CommentsAPI commentsAPI = new CommentsAPI(mContext, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(mContext));
        commentsAPI.create(weiBoCommentBean.content, Long.valueOf(weiBoCommentBean.status.id), false, new RequestListener() {
            @Override
            public void onComplete(String s) {
                onRequestComplete();
            }

            @Override
            public void onWeiboException(WeiboException e) {
                onRequestError(e, "评论失败");

            }
        });
    }

    /**
     * 回复一条评论
     *
     * @param commentReplyBean
     */
    public void replyComment(CommentReplyBean commentReplyBean) {
        CommentsAPI commentsAPI = new CommentsAPI(mContext, Constants.APP_KEY, AccessTokenKeeper.readAccessToken(mContext));
        commentsAPI.reply(Long.valueOf(commentReplyBean.comment.id), Long.valueOf(commentReplyBean.comment.status.id), commentReplyBean.content, false, false,
                new RequestListener() {
                    @Override
                    public void onComplete(String s) {
                        onRequestComplete();
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        onRequestError(e, "回复评论失败");
                    }
                });
    }

    public void onRequestComplete() {
        mSendNotifity.cancel(SEND_STATUS_SEND);
        showSuccessNotifiy();
        final Message message = Message.obtain();
        message.what = SEND_STATUS_SEND;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendMessage(message);
            }
        }, 2000);
    }

    public void onRequestError(WeiboException e, String errorRemind) {
        if (e.getMessage().contains("repeat content")) {
            ToastUtil.showShort(PostService.this, errorRemind + "：请不要回复重复的内容");
        } else {
            ToastUtil.showShort(PostService.this, errorRemind);
        }
        mSendNotifity.cancel(SEND_STATUS_SEND);
        showErrorNotifiy();
        final Message message = Message.obtain();
        message.what = SEND_STATUS_ERROR;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendMessage(message);
            }
        }, 2000);
    }


    /**
     * 获取本地的图片,并且根据图片鞋带的信息纠正旋转方向
     *
     * @param absolutePath
     * @return
     */
    private Bitmap getLoacalBitmap(String absolutePath) {
        return null;
    }

    /**
     * 显示发送的notify
     */
    private void showSendNotifiy() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent rightPendIntent = PendingIntent.getActivity(this, SEND_STATUS_SEND, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        String ticker = "您有一条新通知";
        builder.setContentIntent(rightPendIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo));
        builder.setSmallIcon(R.drawable.queue_icon_send);
        builder.setTicker(ticker);
        builder.setContentTitle("WeiSwift");
        builder.setContentText("正在发送");
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setProgress(0, 0, true);
        Notification notification = builder.build();
        mSendNotifity = (NotificationManager) this.getSystemService(Activity.NOTIFICATION_SERVICE);
        mSendNotifity.notify(SEND_STATUS_SEND, notification);
    }

    /**
     * 发送成功的通知
     */
    private void showSuccessNotifiy() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent rightPendIntent = PendingIntent.getActivity(this, SEND_STATUS_SUCCESS, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        String ticker = "您有一条新通知";
        builder.setContentIntent(rightPendIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo));
        builder.setSmallIcon(R.drawable.queue_icon_success);
        builder.setTicker(ticker);
        builder.setContentTitle("WeiSwift");
        builder.setContentText("发送成功");
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        Notification notification = builder.build();
        // 发送该通知
        mSendNotifity = (NotificationManager) this.getSystemService(Activity.NOTIFICATION_SERVICE);
        mSendNotifity.notify(SEND_STATUS_SUCCESS, notification);
    }

    /**
     * 发送失败的通知
     */
    private void showErrorNotifiy() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent rightPendIntent = PendingIntent.getActivity(this, SEND_STATUS_ERROR, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        String ticker = "您有一条新通知";
        builder.setContentIntent(rightPendIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo));
        builder.setSmallIcon(R.drawable.queue_icon_miss);
        builder.setTicker(ticker);
        builder.setContentTitle("WeiSwift");
        builder.setContentText("发送失败");
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        Notification notification = builder.build();
        // 发送该通知
        mSendNotifity = (NotificationManager) this.getSystemService(Activity.NOTIFICATION_SERVICE);
        mSendNotifity.notify(SEND_STATUS_ERROR, notification);
    }

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mSendNotifity.cancelAll();
            stopSelf();

        }
    };

}
