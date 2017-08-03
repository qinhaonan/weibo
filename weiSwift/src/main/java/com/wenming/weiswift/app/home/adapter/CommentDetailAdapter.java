package com.wenming.weiswift.app.home.adapter;


import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.Comment;
import com.wenming.weiswift.app.common.entity.CommentEntity;
import com.wenming.weiswift.app.common.entity.User;
import com.wenming.weiswift.app.common.FillContent;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.utils.CropCircleTransformation;
import com.wenming.weiswift.utils.RequestUtil;
import com.wenming.weiswift.widget.emojitextview.EmojiTextView;

import java.util.ArrayList;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;


/**
 * 用于显示评论列表的adapter
 * Created by wenmingvs on 16/4/23.
 */
public class CommentDetailAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context mContext;
    private ArrayList<Comment> mDatas;
    private View mView;
    private ArrayList<CommentEntity> mCommentData;


    public CommentDetailAdapter(Context mContext, ArrayList<Comment> datas) {
        this.mContext = mContext;
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.mainfragment_weiboitem_detail_commentbar_comment_item, parent, false);
        CommentViewHolder commentViewHolder = new CommentViewHolder(mView);
        return commentViewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mDatas != null && mCommentData == null) {
            User user = mDatas.get(position).user;
            String content = mDatas.get(position).text;
            FillContent.fillProfileImg(mContext, user, ((CommentViewHolder) holder).profile_img, ((CommentViewHolder) holder).profile_verified);
            FillContent.fillWeiBoContent(content, mContext, ((CommentViewHolder) holder).content);
            FillContent.setWeiBoName(((CommentViewHolder) holder).profile_name, user);
            FillContent.setWeiBoTime(mContext, ((CommentViewHolder) holder).profile_time, mDatas.get(position));
        } else if (mCommentData != null) {
            ((CommentViewHolder) holder).profile_name.setText(mCommentData.get(position).getAuthor_info().getUname());
            ((CommentViewHolder) holder).content.setText(mCommentData.get(position).getContent());
            ((CommentViewHolder) holder).profile_time.setText(mCommentData.get(position).getCtime());
            ((CommentViewHolder) holder).tv_agree_num.setText(mCommentData.get(position).getAgree_count());
            ((CommentViewHolder) holder).tv_disagree_num.setText(mCommentData.get(position).getOppose_count());
            Glide.with(mContext)
                    .load(mCommentData.get(position).getAuthor_info().getAvatar_small())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .crossFade(1000)
                    .into(((CommentViewHolder) holder).profile_img);
            ((CommentViewHolder) holder).ll_disagree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了反对", Toast.LENGTH_SHORT).show();
                    disagreeListener(mCommentData.get(position).getUid(), mCommentData.get(position).getReply_id(), ((CommentViewHolder) holder).tv_disagree_num, mCommentData.get(position).getOppose_count());
                }
            });
            ((CommentViewHolder) holder).ll_agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击了赞同", Toast.LENGTH_SHORT).show();
                    agreeListener(mCommentData.get(position).getUid(), mCommentData.get(position).getReply_id(), ((CommentViewHolder) holder).tv_agree_num, mCommentData.get(position).getAgree_count());
                }
            });
        }
    }

    private void agreeListener(String uid, String reply_id, final TextView tv_agree_num, final String agree_num) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "Weiba");
        params.addBodyParameter("act", "add_agree");
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
        params.addBodyParameter("uid", uid);
        params.addBodyParameter("reply_id", reply_id);
        RequestUtil.requestGet(Constants.ZHONGZHIWULIANG_REQUEST_URL +
                        "app=api&" +
                        "mod=Weiba&" +
                        "act=add_agree&oauth_token=988b491a22040ef7634eb5b8f52e0986&" +
                        "oauth_token_secret=2a3d67f5f7bb03035e619518b364912e&" +
                        "uid=" + uid + "&" +
                        "reply_id=" + reply_id,
                new RequestCallBack() {
                    @Override
                    public void onSuccess(ResponseInfo responseInfo) {
                        if (((String) responseInfo.result).equals("1")) {
                            int num = (Integer.valueOf(agree_num) + 1);
                            tv_agree_num.setText(String.valueOf(num));
                            Toast.makeText(mContext, "赞同成功", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(mContext, "赞同失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.d(TAG, "onFailure: " + s);
                        Toast.makeText(mContext, "赞同失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void disagreeListener(String uid, String reply_id, final TextView tv_disagree_num, final String disagree_num) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "Weiba");
        params.addBodyParameter("act", "add_oppose");
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
        params.addBodyParameter("uid", uid);
        params.addBodyParameter("reply_id", reply_id);
        RequestUtil.requestGet(
                Constants.ZHONGZHIWULIANG_REQUEST_URL +
                        "app=api&" +
                        "mod=Weiba&" +
                        "act=add_oppose&" +
                        "oauth_token=988b491a22040ef7634eb5b8f52e0986&" +
                        "oauth_token_secret=2a3d67f5f7bb03035e619518b364912e&" +
                        "uid=" + uid + "&" +
                        "reply_id=" + reply_id
                , new RequestCallBack() {
                    @Override
                    public void onSuccess(ResponseInfo responseInfo) {
                        Log.d(TAG, "onFailure: " + responseInfo.result);
                        if (((String) responseInfo.result).equals("1")) {
                            int num = (Integer.valueOf(disagree_num) + 1);
                            tv_disagree_num.setText(String.valueOf(num));
                            Toast.makeText(mContext, "反对成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, "反对失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.d(TAG, "onFailure: " + s);
                        Toast.makeText(mContext, "反对失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public int getItemCount() {
        if (mDatas != null && mCommentData == null) {
            return mDatas.size();
        } else if (mCommentData != null) {
            return mCommentData.size();
        } else {
            return 0;
        }
    }

    public void setData(ArrayList<Comment> data) {
        this.mDatas = data;
    }

    public void setCommentData(ArrayList<CommentEntity> commentData) {
        mCommentData = commentData;
    }


    public class CommentViewHolder extends ViewHolder {
        //微博列表的控件
        public ImageView profile_img;
        public ImageView profile_verified;
        public TextView profile_name;
        public TextView profile_time;
        public EmojiTextView content;
        public LinearLayout ll_agree;
        public LinearLayout ll_disagree;
        public TextView tv_agree_num;
        public TextView tv_disagree_num;

        public CommentViewHolder(View v) {
            super(v);
            profile_img = (ImageView) v.findViewById(R.id.profile_img);
            profile_verified = (ImageView) v.findViewById(R.id.profile_verified);
            profile_name = (TextView) v.findViewById(R.id.comment_profile_name);
            profile_time = (TextView) v.findViewById(R.id.comment_profile_time);
            content = (EmojiTextView) v.findViewById(R.id.comment_content);
            ll_disagree = (LinearLayout) v.findViewById(R.id.ll_disagree);
            ll_agree = (LinearLayout) v.findViewById(R.id.ll_agree);
            tv_agree_num = (TextView) v.findViewById(R.id.tv_agree_num);
            tv_disagree_num = (TextView) v.findViewById(R.id.tv_disagree_num);
        }
    }


}
