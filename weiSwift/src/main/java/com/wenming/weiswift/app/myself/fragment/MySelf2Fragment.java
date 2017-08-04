package com.wenming.weiswift.app.myself.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import com.wenming.weiswift.R;

/**
 * Created by qhn on 2017/8/3.
 */

public class MySelf2Fragment extends Fragment{
    public MySelf2Fragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View mView = inflater.inflate(R.layout.layout_myself, null);
        return mView;
    }
}
