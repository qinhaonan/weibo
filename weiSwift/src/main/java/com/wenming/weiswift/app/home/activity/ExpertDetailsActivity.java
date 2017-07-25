package com.wenming.weiswift.app.home.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.home.adapter.ExpertDetailsAdapter;

/**
 * Created by qhn on 2017/7/25.
 */

public class ExpertDetailsActivity extends BaseAppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_expert_details);
        RecyclerView rcv_expertDetails= (RecyclerView) findViewById(R.id.rcv_expert_details);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rcv_expertDetails.setLayoutManager(manager);
        rcv_expertDetails.setAdapter(new ExpertDetailsAdapter(this));
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

}
