package com.wenming.weiswift.app.home.activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.home.fragment.CropCategoryFragment;
import com.wenming.weiswift.app.home.fragment.HomeFragment;


/**
 * Created by qhn on 2017/7/22.
 */

public class CropCategoryActivity extends BaseAppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_crop_category);
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        CropCategoryFragment cropCategoryFragment=CropCategoryFragment.newInstance(false);
        ft.add(R.id.fr_crop_category, cropCategoryFragment,"home");
        ft.commit();
    }
}
