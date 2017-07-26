package com.wenming.weiswift.app.home.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.CropCategoryEntity;
import com.wenming.weiswift.app.common.entity.CropTypeEntity;
import com.wenming.weiswift.app.common.entity.Question;
import com.wenming.weiswift.app.home.widget.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by qhn on 2017/7/17.
 */

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.ContentFragmentViewHolder> {
    Context mContext;
    List<CropCategoryEntity.DataBean> mCropCategoryBean = new ArrayList<CropCategoryEntity.DataBean>();
    int followingTotal = 0;

    public CropAdapter(Context context, List<CropCategoryEntity.DataBean> cropCategoryBean) {
        mCropCategoryBean = cropCategoryBean;
        mContext = context;

    }

    @Override
    public ContentFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CropAdapter.ContentFragmentViewHolder holder = new CropAdapter.ContentFragmentViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_content_fragment, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ContentFragmentViewHolder holder, final int position) {
        holder.tv_crop.setText(mCropCategoryBean.get(position).getWeiba_name());
        if (mCropCategoryBean.get(position).getFollowing() == 1) {
            followingTotal++;
            holder.img_is_following.setVisibility(View.VISIBLE);
        }
        Log.d("total", "onSuccess: 选中1 " + followingTotal);
        if (mCropCategoryBean.get(position).getLogo() != null) {
            Glide.with(mContext)
                    .load(mCropCategoryBean.get(position).getLogo())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.img_crop);
        } else {
            holder.img_crop.setImageResource(R.mipmap.ic_launcher);
        }

        holder.img_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog mDialog = DialogUtils.createLoadingDialog(mContext, "加载中...");
                if (mCropCategoryBean.get(position).getFollowing() == 0) {
                    HttpUtils httpUtils = new HttpUtils();
                    httpUtils.send(HttpRequest.HttpMethod.POST, "http://192.168.1.176/thinksns_v3.0/index.php?" +
                                    "app=api&" +
                                    "mod=Weiba&" +
                                    "act=create&" +
                                    "oauth_token=553cb8005c5dff47cca58aabefd74de7" +
                                    "&oauth_token_secret=4dfa52f77ffe6d55fb1039fe70c70436&" +
                                    "id=" + mCropCategoryBean.get(position).getWeiba_id()
                            , null, new RequestCallBack<Object>() {
                                @Override
                                public void onSuccess(ResponseInfo<Object> responseInfo) {
                                    Log.d(TAG, "onSuccess: 关注成功" + responseInfo);
                                    mCropCategoryBean.get(position).setFollowing(1);
                                    holder.img_is_following.setVisibility(View.VISIBLE);
                                    DialogUtils.closeDialog(mDialog);
                                    followingTotal++;
                                    Log.d("total", "onSuccess: 选中2 " + followingTotal);
                                }

                                @Override
                                public void onFailure(HttpException e, String s) {
                                    Log.d("PPPP", "关注失败: " + s);
                                    DialogUtils.closeDialog(mDialog);
                                }
                            });

                } else {
                    HttpUtils httpUtils = new HttpUtils();
                    httpUtils.send(HttpRequest.HttpMethod.POST, "http://192.168.1.176/thinksns_v3.0/index.php?" +
                                    "app=api&" +
                                    "mod=Weiba&" +
                                    "act=destroy&" +
                                    "oauth_token=553cb8005c5dff47cca58aabefd74de7" +
                                    "&oauth_token_secret=4dfa52f77ffe6d55fb1039fe70c70436&" +
                                    "id=" + mCropCategoryBean.get(position).getWeiba_id()
                            , null, new RequestCallBack<Object>() {
                                @Override
                                public void onSuccess(ResponseInfo<Object> responseInfo) {
                                    DialogUtils.closeDialog(mDialog);
                                    Log.d(TAG, "onSuccess: 取消关注成功" + responseInfo);
                                    mCropCategoryBean.get(position).setFollowing(0);
                                    holder.img_is_following.setVisibility(View.INVISIBLE);
                                    followingTotal--;
                                    Log.d("total", "onSuccess: 选中3 " + followingTotal);
                                }

                                @Override
                                public void onFailure(HttpException e, String s) {
                                    DialogUtils.closeDialog(mDialog);
                                    Log.d("PPPP", "onFailure: 取消关注失败" + s);
                                }
                            });

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mCropCategoryBean.size();
    }

    public void setCropTypeTotal() {

    }

    ;

    class ContentFragmentViewHolder extends RecyclerView.ViewHolder {
        TextView tv_crop;
        ImageView img_crop;
        ImageView img_is_following;

        public ContentFragmentViewHolder(View itemView) {
            super(itemView);
            tv_crop = (TextView) itemView.findViewById(R.id.tv_crop);
            img_crop = (ImageView) itemView.findViewById(R.id.img_crop);
            img_is_following = (ImageView) itemView.findViewById(R.id.img_is_following);
        }
    }
}
