package com.wenming.weiswift.app.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sina.weibo.sdk.api.share.Base;
import com.wenming.weiswift.R;

/**
 * Created by qhn on 2017/7/12.
 */

public class GridViewAdatpter extends BaseAdapter {
    Context mContext;
    public GridViewAdatpter(Context context){
        mContext=context;
    }
    @Override
    public int getCount() {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.textlayout, parent, false);
            holder=new GridViewAdatpter.ViewHolder();
            holder.img_gridView = (ImageView) convertView.findViewById(R.id.img_gridItem);
            holder.tv_gridView = (TextView) convertView.findViewById(R.id.tv_gridItem);
        }
//        holder.img_gridView.setImageResource(R.drawable.);

        return convertView;
    }
    class ViewHolder {
        TextView tv_gridView;
        ImageView img_gridView;
    }
}
