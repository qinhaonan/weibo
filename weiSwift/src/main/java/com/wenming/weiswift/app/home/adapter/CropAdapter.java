package com.wenming.weiswift.app.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lidroid.xutils.BitmapUtils;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.Question;

import java.util.List;

/**
 * Created by qhn on 2017/7/17.
 */

public class CropAdapter extends RecyclerView.Adapter<CropAdapter.ContentFragmentViewHolder> {
    Context mContext;
    Question[] mDataList;

    public CropAdapter(Context context, Question[] questions) {
        mDataList = questions;
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
    public void onBindViewHolder(ContentFragmentViewHolder holder, int position) {
        holder.tv_crop.setText(mDataList[position].getFeed_content());
//        holder.img_crop.setImageResource(R.mipmap.ic_launcher);
        if ( mDataList[position].getAttach() != null&&mDataList[position].getAttach().get(0) != null) {
            Glide.with(mContext)
                    .load(mDataList[position].getAttach().get(0).getAttach_middle())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.img_crop);
        } else {
            holder.img_crop.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.length;
    }

    class ContentFragmentViewHolder extends RecyclerView.ViewHolder {
        TextView tv_crop;
        ImageView img_crop;

        public ContentFragmentViewHolder(View itemView) {
            super(itemView);
            tv_crop = (TextView) itemView.findViewById(R.id.tv_crop);
            img_crop = (ImageView) itemView.findViewById(R.id.img_crop);
        }
    }
}
