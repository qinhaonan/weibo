package com.wenming.weiswift.app.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.CropChannel;
import com.wenming.weiswift.app.common.entity.CropTypeEntity;
import com.wenming.weiswift.app.home.activity.CropActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhn on 2017/7/17.
 */

public class CropTypeAdapter extends RecyclerView.Adapter<CropTypeAdapter.LeftFragmentViewHolder> {
    List<CropTypeEntity.CropTypeBean> mDataList;
    Context mContext;
    int mBeforePosition = 0;
    boolean isFirst = true;
    TextView tvTemp;
    int i = 0;
    private final Activity mActivity;
    private List<TextView> tv_item_typeList=new ArrayList<TextView>();
    public CropTypeAdapter(Context context, List<CropTypeEntity.CropTypeBean> dataList, Activity activity) {
        mDataList = dataList;
        mContext = context;
        mActivity = activity;
    }

    @Override
    public LeftFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LeftFragmentViewHolder holder = new LeftFragmentViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_leftfragment, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final LeftFragmentViewHolder holder, final int position) {
        if (position == 0) {
            tvTemp = holder.tv_item_mark;
            holder.tv_item_mark.setVisibility(View.VISIBLE);
        }
        holder.tv_item_type.setText(mDataList.get(position).getName() + "(" + i + ")");
        holder.tv_item_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.tv_item_mark.setVisibility(View.VISIBLE);
                if (!tvTemp.equals(holder.tv_item_mark)) {
                    tvTemp.setVisibility(View.INVISIBLE);
                }
                tvTemp = holder.tv_item_mark;
                tv_item_typeList.add(holder.tv_item_type);
                ((CropActivity) mActivity).changeFragment(position, mBeforePosition);
                mBeforePosition = position;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public  List<TextView> getCropTotalView() {
        return tv_item_typeList;
    }

    class LeftFragmentViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_type;
        TextView tv_item_mark;

        public LeftFragmentViewHolder(View itemView) {
            super(itemView);
            tv_item_type = (TextView) itemView.findViewById(R.id.tv_item_type);
            tv_item_mark = (TextView) itemView.findViewById(R.id.tv_mark);

        }
    }

}
