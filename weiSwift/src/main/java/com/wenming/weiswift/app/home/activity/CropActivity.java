package com.wenming.weiswift.app.home.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.home.fragment.ContentFragment;
import com.wenming.weiswift.app.home.fragment.LeftFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhn on 2017/7/15.
 */

public class CropActivity extends BaseAppCompatActivity {

    private static final String TAG = "CropActivity";
    private Context mContext;
    private ArrayList<String> dataList;
    private LeftFragment leftFragment;
    private List<ContentFragment> contentFragmentList = new ArrayList<ContentFragment>();
    private ContentFragment contentFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crops_form_layout);
        mContext = this;
        initData();
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < dataList.size(); i++) {
            contentFragment = new ContentFragment(mContext);
            contentFragmentList.add(contentFragment);
            fragmentTransaction.add(R.id.fl_content, contentFragment);
        }
        leftFragment = new LeftFragment(mContext, dataList);
        fragmentTransaction.add(R.id.fl_left, leftFragment);
        fragmentTransaction.commit();
    }

    public void changeFragment(int i) {
        fragmentTransaction.replace(R.id.fl_content, contentFragmentList.get(i));

    }

    public void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add("苹果" + String.valueOf(i));
        }
    }
}
