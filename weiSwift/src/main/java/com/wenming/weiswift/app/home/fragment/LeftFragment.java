package com.wenming.weiswift.app.home.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.CropChannel;
import com.wenming.weiswift.app.common.entity.CropEntity;
import com.wenming.weiswift.app.common.entity.CropTypeEntity;
import com.wenming.weiswift.app.home.adapter.CropTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhn on 2017/7/15.
 */

public class LeftFragment extends Fragment {
    Context mContext;
    private List<CropTypeEntity.CategoryBean> mDataList;
    private List<TextView> textViewsList;

    public LeftFragment(){

    }
    public  LeftFragment(Context context,List<CropTypeEntity.CategoryBean> dataList){
        mContext=context;
        mDataList=dataList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.fl_left_fragmet, null);
        RecyclerView rcView= (RecyclerView) view.findViewById(R.id.rc_type_leftfragment);
        rcView.setLayoutManager(new LinearLayoutManager(mContext));
        CropTypeAdapter cropAdapter = new CropTypeAdapter(mContext, mDataList,getActivity());
        textViewsList = cropAdapter.getCropTotalView();
        rcView.setAdapter(cropAdapter);
        return view;
    }
    public List<TextView> getCropTotalView(){
        return textViewsList;
    }
}
