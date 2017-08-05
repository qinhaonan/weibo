package com.wenming.weiswift.app.myself.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.myself.activity.MyWatchListActivity;

/**
 * Created by qhn on 2017/8/3.
 */

public class MySelf2Fragment extends Fragment{

    private View mView;
    private TextView tv_follow;
    private TextView tv_answers;
    private TextView tv_collect;
    private Activity activity;
    private Context context;

    public MySelf2Fragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_myself, null);
        initView();
        activity = getActivity();
        context = getContext();
        return mView;
    }

    private void initView() {
        tv_follow = (TextView) mView.findViewById(R.id.tv_follow);
        tv_answers = (TextView) mView.findViewById( R.id.tv_answers);
        tv_collect = (TextView) mView.findViewById( R.id.tv_collect);
        tv_follow.setOnClickListener(clickListener);
        tv_answers.setOnClickListener(clickListener);
        tv_collect.setOnClickListener(clickListener);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_follow:
                    context.startActivity(new Intent(context, MyWatchListActivity.class));
                    break;
                case R.id.tv_collect:
                    break;
                case R.id.tv_answers:
                    break;
            }
        }
    };
}
