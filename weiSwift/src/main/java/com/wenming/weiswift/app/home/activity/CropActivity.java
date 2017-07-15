package com.wenming.weiswift.app.home.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.home.fragment.ContentFragment;
import com.wenming.weiswift.app.home.fragment.LeftFragment;

/**
 * Created by qhn on 2017/7/15.
 */

public class CropActivity extends BaseAppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crops_form_layout);
        initFragment();
    }

    private void initFragment() {
        LeftFragment leftFragment = new LeftFragment();
        ContentFragment contentFragment = new ContentFragment();
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_left, leftFragment);
        fragmentTransaction.add(R.id.fl_content, contentFragment);
        fragmentTransaction.commit();


    }

}
