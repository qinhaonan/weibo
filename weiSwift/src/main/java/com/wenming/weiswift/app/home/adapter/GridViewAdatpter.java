package com.wenming.weiswift.app.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.Crop;
import com.wenming.weiswift.app.home.activity.CropActivity;
import com.wenming.weiswift.app.home.activity.CropCategoryActivity;
import com.wenming.weiswift.app.home.activity.ExpertDetailsActivity;

import java.util.List;

/**
 * Created by qhn on 2017/7/12.
 */

public class GridViewAdatpter extends BaseAdapter {
    private final boolean mIsLastPager;
    Context mContext;
    int mPosition;
    private final List<Crop> mCropList;
    private ViewHolder holder;

    public GridViewAdatpter(Context context, int i, List<Crop> cropList, boolean isLastPager) {
        mContext = context;
        mPosition = i;
        mCropList = cropList;
        mIsLastPager = isLastPager;
    }

    @Override
    public int getCount() {
        if (mIsLastPager) {
            return mCropList.size() % 9 + 1;
        }
        return 10;


    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview_layout, parent, false);
            holder = new GridViewAdatpter.ViewHolder();
            holder.img_gridView = (ImageView) convertView.findViewById(R.id.img_gridItem);
            holder.tv_gridView = (TextView) convertView.findViewById(R.id.tv_gridItem);
            convertView.setTag(holder);
        }

        if (mIsLastPager) {
            if (position != mCropList.size() % 9) {
                Glide.with(mContext)
                        .load(mCropList.get(position + mPosition * 9).getLogo_url())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.loading)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.img_gridView);
                holder.tv_gridView.setText(mCropList.get(position + mPosition * 9).getWeiba_name());
                holder.img_gridView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(mContext, CropCategoryActivity.class);
                        intent.putExtra("CropName",mCropList.get(position + mPosition * 9).getWeiba_name());
                        mContext.startActivity(intent);
                    }
                });
            } else {
                holder.img_gridView.setImageResource(R.drawable.add_crop);
                holder.tv_gridView.setText("添加作物");
                holder.img_gridView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, CropActivity.class));
                    }
                });
            }
        } else if (position != 9) {
            Glide.with(mContext)
                    .load(mCropList.get(position + mPosition * 9).getLogo_url())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.loading)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.img_gridView);
            holder.tv_gridView.setText(mCropList.get(position + mPosition * 9).getWeiba_name());
            holder.img_gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, CropCategoryActivity.class);
                    intent.putExtra("CropName",mCropList.get(position + mPosition * 9).getWeiba_name());
                    mContext.startActivity(intent);
                }
            });
        } else {
            holder.img_gridView.setImageResource(R.drawable.add_crop);
            holder.tv_gridView.setText("添加作物");
            holder.img_gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mContext.startActivity(new Intent(mContext, CropActivity.class));
                    mContext.startActivity(new Intent(mContext, ExpertDetailsActivity.class));

                }
            });
        }



        return convertView;
    }

    class ViewHolder {
        TextView tv_gridView;
        ImageView img_gridView;
    }
}
