package com.wenming.weiswift.app.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.FillContent;
import com.wenming.weiswift.app.common.entity.QuestionByIdEntity;
import com.wenming.weiswift.app.common.entity.QuestionEntity;
import com.wenming.weiswift.app.common.entity.Status;
import com.wenming.weiswift.app.home.activity.CommentDetailSwipeActivity;
import com.wenming.weiswift.app.home.weiboitem.NewPauseOnScrollListener;
import com.wenming.weiswift.app.mvp.presenter.WeiBoArrowPresent;
import com.wenming.weiswift.app.mvp.presenter.imp.WeiBoArrowPresenterImp;
import com.wenming.weiswift.app.weibodetail.activity.OriginPicTextCommentDetailSwipeActivity;
import com.wenming.weiswift.app.weibodetail.activity.RetweetPicTextCommentDetailSwipeActivity;
import com.wenming.weiswift.utils.CropCircleTransformation;
import com.wenming.weiswift.utils.CutOutUtil;
import com.wenming.weiswift.widget.emojitextview.EmojiTextView;

import java.util.ArrayList;

/**
 * Created by wenmingvs on 2015/12/29.
 */
public abstract class WeiboAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int TYPE_ORINGIN_ITEM = 0;
    private static final int TYPE_RETWEET_ITEM = 3;
    private ArrayList<Status> mDatas;
    private ArrayList<QuestionByIdEntity> mCateDatas;
    private Context mContext;
    private View mView;
    private Status status;
    private ArrayList<QuestionEntity> mQuestionData;

    public WeiboAdapter(ArrayList<Status> datas, Context context) {
        this.mDatas = datas;
        this.mContext = context;
    }

    /**
     * 设置图片间距，注意要保证addItemDecoration只被调用一次，多次调用间距会累加
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ORINGIN_ITEM) {
            mView = LayoutInflater.from(mContext).inflate(R.layout.home_weiboitem_original_pictext, parent, false);
            OriginViewHolder originViewHolder = new OriginViewHolder(mView);
            originViewHolder.imageList.addOnScrollListener(new NewPauseOnScrollListener(ImageLoader.getInstance().getInstance(), true, true));
            return originViewHolder;
        } else if (viewType == TYPE_RETWEET_ITEM) {
            mView = LayoutInflater.from(mContext).inflate(R.layout.mainfragment_weiboitem_retweet_pictext, parent, false);
            RetweetViewHolder retweetViewHolder = new RetweetViewHolder(mView);
            retweetViewHolder.retweet_imageList.addOnScrollListener(new NewPauseOnScrollListener(ImageLoader.getInstance().getInstance(), true, true));
            return retweetViewHolder;
        }
        return null;
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof OriginViewHolder) {
            //如果这条原创微博没有被删除
            if (mDatas != null&&mCateDatas==null&&mQuestionData==null) {
                status = mDatas.get(position);
                ((OriginViewHolder) holder).titlebar_layout.setVisibility(View.VISIBLE);
                ((OriginViewHolder) holder).bottombar_layout.setVisibility(View.VISIBLE);
                ((OriginViewHolder) holder).splitLine.setVisibility(View.GONE);
                ((OriginViewHolder) holder).favoritedelete.setVisibility(View.GONE);
                FillContent.fillTitleBar(mContext, mDatas.get(position), ((OriginViewHolder) holder).profile_img, ((OriginViewHolder) holder).profile_verified, ((OriginViewHolder) holder).profile_name, ((OriginViewHolder) holder).profile_time, ((OriginViewHolder) holder).weibo_comefrom);
                FillContent.fillWeiBoContent(mDatas.get(position).text, mContext, ((OriginViewHolder) holder).weibo_content);
                FillContent.fillButtonBar(mContext, mDatas.get(position), ((OriginViewHolder) holder).bottombar_retweet, ((OriginViewHolder) holder).bottombar_comment, ((OriginViewHolder) holder).bottombar_attitude, ((OriginViewHolder) holder).comment, ((OriginViewHolder) holder).redirect, ((OriginViewHolder) holder).feedlike);
                FillContent.fillComment_num(mDatas.get(position), ((OriginViewHolder) holder).tv_comment);
                FillContent.fillWeiBoImgList(mDatas.get(position), mContext, ((OriginViewHolder) holder).imageList);
                ((OriginViewHolder) holder).weibo_comefrom.setText(mDatas.get(position).user.location);
                ((OriginViewHolder) holder).tv_weibaCategory.setText(mDatas.get(position).source);
                ((OriginViewHolder) holder).bottombar_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                ((OriginViewHolder) holder).popover_arrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((OriginViewHolder) holder).origin_weibo_layout.setDrawingCacheEnabled(true);
                        ((OriginViewHolder) holder).origin_weibo_layout.buildDrawingCache(true);
                        arrowClick(mDatas.get(position), position, ((OriginViewHolder) holder).origin_weibo_layout.getDrawingCache());
                    }
                });

                //微博背景的点击事件
                ((OriginViewHolder) holder).origin_weibo_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, OriginPicTextCommentDetailSwipeActivity.class);
                        intent.putExtra("weiboitem", mDatas.get(position));
                        intent.putExtra("post_id", mDatas.get(position).id);
                        mContext.startActivity(intent);
                    }
                });
//                解答的点击事件
//                ((OriginViewHolder) holder).ll_solution.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (status.retweeted_status == null) {
//                            Intent intent = new Intent(mContext, OriginPicTextCommentDetailSwipeActivity.class);
//                            intent.putExtra("weiboitem", status);
//                            ((Activity) mContext).startActivityForResult(intent, 101);
//                        } else {
//                            Intent intent = new Intent(mContext, RetweetPicTextCommentDetailSwipeActivity.class);
//                            intent.putExtra("weiboitem", status);
//                            ((Activity) mContext).startActivityForResult(intent, 101);
//                        }
//                    }
//                });
                ((OriginViewHolder) holder).ll_solution.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, CommentDetailSwipeActivity.class);
                        intent.putExtra("weiboitem", status);
                        ((Activity) mContext).startActivityForResult(intent, 101);

                    }
                });

            }else if(mCateDatas!=null){
//                FillContent.fillTitleBar(mContext, mDatas.get(position), ((OriginViewHolder) holder).profile_img, ((OriginViewHolder) holder).profile_verified, ((OriginViewHolder) holder).profile_name, ((OriginViewHolder) holder).profile_time, ((OriginViewHolder) holder).weibo_comefrom);
                FillContent.fillWeiBoContent(CutOutUtil.getText(mCateDatas.get(position).getContent()), mContext, ((OriginViewHolder) holder).weibo_content);
//                FillContent.fillButtonBar(mContext, mDatas.get(position), ((OriginViewHolder) holder).bottombar_retweet, ((OriginViewHolder) holder).bottombar_comment, ((OriginViewHolder) holder).bottombar_attitude, ((OriginViewHolder) holder).comment, ((OriginViewHolder) holder).redirect, ((OriginViewHolder) holder).feedlike);
                FillContent.fillComment_num(mCateDatas.get(position).getReply_count(), ((OriginViewHolder) holder).tv_comment);
                ArrayList<String> img_urlList=(ArrayList<String>) CutOutUtil.getImgSrc(mCateDatas.get(position).getContent());
                FillContent.fillWeiBoImgList(img_urlList, mContext, ((OriginViewHolder) holder).imageList);
                ((OriginViewHolder) holder).tv_weibaCategory.setText(mCateDatas.get(position).getTitle());
                ((OriginViewHolder) holder).weibo_comefrom.setText(mCateDatas.get(position).getAuthor_info().getLocation());
                ((OriginViewHolder) holder).profile_name.setText(mCateDatas.get(position).getAuthor_info().getUname());
                Glide.with(mContext)
                        .load(mCateDatas.get(position).getAuthor_info().getAvatar_small())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.loading)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .bitmapTransform(new CropCircleTransformation(mContext))
                        .crossFade(1000)
                        .into(((OriginViewHolder) holder).profile_img);
            }else if(mQuestionData!=null){
                FillContent.fillWeiBoContent(CutOutUtil.getText(mQuestionData.get(position).getApi_source().getContent()), mContext, ((OriginViewHolder) holder).weibo_content);
                FillContent.fillComment_num(mQuestionData.get(position).getComment_all_count(), ((OriginViewHolder) holder).tv_comment);
                ArrayList<String> img_urlList=(ArrayList<String>) CutOutUtil.getImgSrc(mQuestionData.get(position).getApi_source().getContent());
                FillContent.fillWeiBoImgList(img_urlList, mContext, ((OriginViewHolder) holder).imageList);
                ((OriginViewHolder) holder).tv_weibaCategory.setText(mQuestionData.get(position).getApi_source().getTitle());
                ((OriginViewHolder) holder).weibo_comefrom.setText(mQuestionData.get(position).getApi_source().getSource_user_info().getLocation());
                ((OriginViewHolder) holder).profile_name.setText(mQuestionData.get(position).getUname());
                Glide.with(mContext)
                        .load(mQuestionData.get(position).getAvatar_small())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.loading)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .bitmapTransform(new CropCircleTransformation(mContext))
                        .crossFade(1000)
                        .into(((OriginViewHolder) holder).profile_img);
            }
            //如果这条原创微博被删除
            else {
                ((OriginViewHolder) holder).titlebar_layout.setVisibility(View.GONE);
                ((OriginViewHolder) holder).bottombar_layout.setVisibility(View.GONE);
                ((OriginViewHolder) holder).imageList.setVisibility(View.GONE);
                ((OriginViewHolder) holder).splitLine.setVisibility(View.VISIBLE);
                ((OriginViewHolder) holder).favoritedelete.setVisibility(View.VISIBLE);
                FillContent.fillWeiBoContent(mDatas.get(position).text, mContext, ((OriginViewHolder) holder).weibo_content);
                ((OriginViewHolder) holder).favoritedelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WeiBoArrowPresent weiBoArrowPresent = new WeiBoArrowPresenterImp(WeiboAdapter.this);
                        weiBoArrowPresent.cancalFavorite(position, mDatas.get(position), mContext, true);
                    }
                });
            }
        } else if (holder instanceof RetweetViewHolder) {
            FillContent.fillTitleBar(mContext, mDatas.get(position), ((RetweetViewHolder) holder).profile_img, ((RetweetViewHolder) holder).profile_verified, ((RetweetViewHolder) holder).profile_name, ((RetweetViewHolder) holder).profile_time, ((RetweetViewHolder) holder).weibo_comefrom);
            FillContent.fillRetweetContent(mDatas.get(position), mContext, ((RetweetViewHolder) holder).origin_nameAndcontent);
            FillContent.fillWeiBoContent(mDatas.get(position).text, mContext, ((RetweetViewHolder) holder).retweet_content);
            FillContent.fillButtonBar(mContext, mDatas.get(position), ((RetweetViewHolder) holder).bottombar_retweet, ((RetweetViewHolder) holder).bottombar_comment, ((RetweetViewHolder) holder).bottombar_attitude, ((RetweetViewHolder) holder).comment, ((RetweetViewHolder) holder).redirect, ((RetweetViewHolder) holder).feedlike);
            FillContent.fillWeiBoImgList(mDatas.get(position).retweeted_status, mContext, ((RetweetViewHolder) holder).retweet_imageList);

            //点击转发的内容
            ((RetweetViewHolder) holder).retweetStatus_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDatas.get(position).retweeted_status.user != null) {
                        Intent intent = new Intent(mContext, OriginPicTextCommentDetailSwipeActivity.class);
                        intent.putExtra("weiboitem", mDatas.get(position).retweeted_status);
                        mContext.startActivity(intent);
                    }
                }
            });

            ((RetweetViewHolder) holder).popover_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((RetweetViewHolder) holder).retweet_weibo_layout.setDrawingCacheEnabled(true);
                    ((RetweetViewHolder) holder).retweet_weibo_layout.buildDrawingCache(true);
                    arrowClick(mDatas.get(position), position, ((RetweetViewHolder) holder).retweet_weibo_layout.getDrawingCache());
                }
            });

            //微博背景的点击事件
            ((RetweetViewHolder) holder).retweet_weibo_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, RetweetPicTextCommentDetailSwipeActivity.class);
                    intent.putExtra("weiboitem", mDatas.get(position));
                    mContext.startActivity(intent);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        if (mDatas != null&&mCateDatas==null&&mQuestionData==null) {
            return mDatas.size();
        } else if(mCateDatas!=null){
            return mCateDatas.size();
        }else if(mQuestionData!=null){
            return mQuestionData.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ORINGIN_ITEM;
//        if (mDatas.get(position).retweeted_status != null) {
//            return TYPE_RETWEET_ITEM;
//        } else {
//            return TYPE_ORINGIN_ITEM;
//        }
    }

    public void setData(ArrayList<Status> data) {
        this.mDatas = data;
    }
    public void setCateGoryData(ArrayList<QuestionByIdEntity> data) {
        this.mCateDatas = data;
    }
    public void setSearchData(ArrayList<QuestionEntity> questionList){
        mQuestionData = questionList;
    }

    public abstract void arrowClick(Status status, int position, Bitmap bitmap);

    public void removeDataItem(int position) {
        mDatas.remove(position);
    }

    private static class OriginViewHolder extends ViewHolder {
        public LinearLayout origin_weibo_layout;
        public LinearLayout titlebar_layout;
        public ImageView profile_img;
        public ImageView profile_verified;
        public ImageView popover_arrow;
        public TextView profile_name;
        public TextView profile_time;
        public TextView weibo_comefrom;
        public EmojiTextView weibo_content;
        public TextView redirect;
        public TextView comment;
        public TextView feedlike;
        public RecyclerView imageList;
        public TextView favoritedelete;
        public ImageView splitLine;
        public LinearLayout bottombar_layout;
        public LinearLayout bottombar_retweet;
        public LinearLayout bottombar_comment;
        public LinearLayout bottombar_attitude;
        public LinearLayout ll_solution;
        public TextView tv_comment;
        public TextView tv_weibaCategory;
        public OriginViewHolder(View v) {
            super(v);
            origin_weibo_layout = (LinearLayout) v.findViewById(R.id.origin_weibo_layout);
            ll_solution = (LinearLayout) v.findViewById(R.id.ll_solution);
            tv_comment = (TextView) v.findViewById(R.id.tv_comment);
            titlebar_layout = (LinearLayout) v.findViewById(R.id.titlebar_layout);
            profile_img = (ImageView) v.findViewById(R.id.profile_img);
            profile_verified = (ImageView) v.findViewById(R.id.profile_verified);
            popover_arrow = (ImageView) v.findViewById(R.id.popover_arrow);
            profile_name = (TextView) v.findViewById(R.id.profile_name);
            profile_time = (TextView) v.findViewById(R.id.profile_time);
            weibo_content = (EmojiTextView) v.findViewById(R.id.weibo_Content);
            weibo_comefrom = (TextView) v.findViewById(R.id.weiboComeFrom);
            redirect = (TextView) v.findViewById(R.id.redirect);
            comment = (TextView) v.findViewById(R.id.comment);
            feedlike = (TextView) v.findViewById(R.id.feedlike);
            splitLine = (ImageView) v.findViewById(R.id.splitLine);
            imageList = (RecyclerView) v.findViewById(R.id.weibo_image);
            favoritedelete = (TextView) v.findViewById(R.id.favorities_delete);
            bottombar_layout = (LinearLayout) v.findViewById(R.id.bottombar_layout);
            bottombar_retweet = (LinearLayout) v.findViewById(R.id.bottombar_retweet);
            bottombar_comment = (LinearLayout) v.findViewById(R.id.bottombar_comment);
            bottombar_attitude = (LinearLayout) v.findViewById(R.id.bottombar_attitude);
            tv_weibaCategory=(TextView)v.findViewById(R.id.weibaCategory);
        }
    }

    private static class RetweetViewHolder extends ViewHolder {
        public LinearLayout retweet_weibo_layout;
        public ImageView profile_img;
        public ImageView profile_verified;
        public ImageView popover_arrow;
        public TextView profile_name;
        public TextView profile_time;
        public TextView weibo_comefrom;
        public EmojiTextView retweet_content;
        public TextView redirect;
        public TextView comment;
        public TextView feedlike;
        public EmojiTextView origin_nameAndcontent;
        public RecyclerView retweet_imageList;
        public LinearLayout bottombar_layout;
        public LinearLayout bottombar_retweet;
        public LinearLayout bottombar_comment;
        public LinearLayout bottombar_attitude;
        public LinearLayout retweetStatus_layout;



        public RetweetViewHolder(View v) {
            super(v);
            retweet_weibo_layout = (LinearLayout) v.findViewById(R.id.retweet_weibo_layout);
            profile_img = (ImageView) v.findViewById(R.id.profile_img);
            profile_verified = (ImageView) v.findViewById(R.id.profile_verified);
            popover_arrow = (ImageView) v.findViewById(R.id.popover_arrow);
            profile_name = (TextView) v.findViewById(R.id.profile_name);
            profile_time = (TextView) v.findViewById(R.id.profile_time);
            retweet_content = (EmojiTextView) v.findViewById(R.id.retweet_content);
            weibo_comefrom = (TextView) v.findViewById(R.id.weiboComeFrom);
            redirect = (TextView) v.findViewById(R.id.redirect);
            comment = (TextView) v.findViewById(R.id.comment);

            feedlike = (TextView) v.findViewById(R.id.feedlike);
            origin_nameAndcontent = (EmojiTextView) v.findViewById(R.id.origin_nameAndcontent);
            retweet_imageList = (RecyclerView) v.findViewById(R.id.origin_imageList);
            bottombar_layout = (LinearLayout) v.findViewById(R.id.bottombar_layout);
            bottombar_retweet = (LinearLayout) v.findViewById(R.id.bottombar_retweet);
            bottombar_comment = (LinearLayout) v.findViewById(R.id.bottombar_comment);
            bottombar_attitude = (LinearLayout) v.findViewById(R.id.bottombar_attitude);
            retweetStatus_layout = (LinearLayout) v.findViewById(R.id.retweetStatus_layout);
        }
    }


}
