package com.wenming.weiswift.app.home.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.home.adapter.CropAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhn on 2017/7/15.
 */

public class ContentFragment extends Fragment{
    Context mContext;
    private List<String> dataList;
    public  ContentFragment(Context context){
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        init();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.fl_content_layout, null);
        RecyclerView rcView= (RecyclerView) view.findViewById(R.id.rc_content);
        rcView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//        rcView.setLayoutManager(new LinearLayoutManager(mContext));
        rcView.setAdapter(new CropAdapter(mContext,dataList));
        return view;
    }
    private void init(){
        dataList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            dataList.add("苹果"+String.valueOf(i));
        }
    }
}
