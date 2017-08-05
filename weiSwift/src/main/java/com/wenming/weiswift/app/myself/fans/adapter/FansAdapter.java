package com.wenming.weiswift.app.myself.fans.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.Expert;
import com.wenming.weiswift.app.common.entity.User;
import com.wenming.weiswift.app.common.entity.WatchListEntity;
import com.wenming.weiswift.app.home.activity.ExpertDetailsActivity;
import com.wenming.weiswift.utils.CropCircleTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenmingvs on 16/5/1.
 */
public abstract class FansAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Expert.ExpertBean> mDatas = new ArrayList<Expert.ExpertBean>();
    private ArrayList<WatchListEntity> mWatchListDatas ;
    private Context mContext;
    private View mView;

    private String mStrExpertType;


    public FansAdapter(List<Expert.ExpertBean> datas, Context context) {
        this.mDatas = datas;
        this.mContext = context;
    }
    public FansAdapter(ArrayList<WatchListEntity> datas, Context context) {
        this.mWatchListDatas = datas;
        this.mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.profilefragment_follower_item, parent, false);
        FollowerViewHolder viewHolder = new FollowerViewHolder(mView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

//        FillContent.fillProfileImg(mContext, mDatas.get(position), ((FollowerViewHolder) holder).followerImg, ((FollowerViewHolder) holder).followerVerf);
//        FillContent.setWeiBoName(((FollowerViewHolder) holder).followerName, mDatas.get(position));
//        FillContent.fillFollowerDescription(mDatas.get(position), ((FollowerViewHolder) holder).follower_firstcontent);
//        FillContent.setFollowerComeFrom(((FollowerViewHolder) holder).profile_comefrom, mDatas.get(position).status);
//        FillContent.updateRealtionShip(mContext,mDatas.get(position), ((FollowerViewHolder) holder).follwerIcon, ((FollowerViewHolder) holder).follwerText);


        //设置点击事件
//        ((FollowerViewHolder) holder).follower_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                followerLayoutClick(mDatas.get(position), position, ((FollowerViewHolder) holder).follwerIcon, ((FollowerViewHolder) holder).follwerText);
//            }
//        });
        //专家列表
        if(mDatas!=null&&mWatchListDatas==null){
             String skillField="";
            ((FollowerViewHolder) holder).followerName.setText(mDatas.get(position).getUname());
            if (mDatas.get(position).getTag()!=null) {
                for (int i = 0; i < mDatas.get(position).getTag().size(); i++) {
                    skillField = skillField + mDatas.get(position).getTag().get(i) + ",";
                }
            }
            ((FollowerViewHolder) holder).tv_skilled_field.setText(skillField);
            if(mStrExpertType!=null){
                ((FollowerViewHolder) holder).tv_expert_type_follower.setText(mStrExpertType);
            }
            Glide.with(mContext)
                    .load(mDatas.get(position).getAvatar_middle())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .crossFade(1000)
                    .into(((FollowerViewHolder) holder).followerImg);
            //我的关注列表
        }else if (mWatchListDatas!=null){
            ((FollowerViewHolder) holder).followerName.setText(mWatchListDatas.get(position).getUname());
            Glide.with(mContext)
                    .load(mWatchListDatas.get(position).getAvatar_middle())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .crossFade(1000)
                    .into(((FollowerViewHolder) holder).followerImg);
            ((FollowerViewHolder) holder).ll_expert_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(mContext, ExpertDetailsActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }



    }

    public abstract void followerLayoutClick(User user, int position, ImageView follwerIcon, TextView follwerText);

    @Override
    public int getItemCount() {
        if (mDatas != null&&mWatchListDatas==null) {
            return mDatas.size();
        } else if(mWatchListDatas!=null){
            return mWatchListDatas.size();
        }
        return 0;
    }


    //    public void setData(ArrayList<User> data) {
//        this.mDatas = data;
//    }
    public void setExpertData(List<Expert.ExpertBean> data,String strExpertType) {
        mDatas = data;
        mStrExpertType =strExpertType;
    }
    public void setmWatchListDatas(ArrayList<WatchListEntity> data) {
        mWatchListDatas = data;
    }

    protected class FollowerViewHolder extends ViewHolder {
        public TextView tv_skilled_field;
        public ImageView followerImg;
        public ImageView followerVerf;
        public TextView followerName;
        public TextView follwerText;
        public RelativeLayout follower_layout;
        public LinearLayout ll_expert_profile;
        public TextView tv_expert_type_follower;

        public FollowerViewHolder(View view) {
            super(view);
            followerImg = (ImageView) view.findViewById(R.id.follower_img);
            followerVerf = (ImageView) view.findViewById(R.id.follower_verified);
            followerName = (TextView) view.findViewById(R.id.follower_name);
            follwerText = (TextView) view.findViewById(R.id.follwer_relation_text);
            follower_layout = (RelativeLayout) view.findViewById(R.id.follow_layout);
            ll_expert_profile=(LinearLayout)view.findViewById(R.id.ll_expert_profile);
            tv_expert_type_follower = (TextView) view.findViewById(R.id.tv_expert_type_follower);
            tv_skilled_field = (TextView) view.findViewById(R.id.tv_skilled_field);
        }
    }
}
