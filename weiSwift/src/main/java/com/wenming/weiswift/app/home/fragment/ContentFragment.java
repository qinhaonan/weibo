package com.wenming.weiswift.app.home.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenming.weiswift.R;

/**
 * Created by qhn on 2017/7/15.
 */

public class ContentFragment extends Fragment{
    Context mContext;
    public void ContentFragment(Context context){
        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.fl_content_layout, null);
        return view;
    }
}
