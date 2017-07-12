package com.wenming.weiswift.app.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hackware on 2016/9/10.
 */

public class ExamplePagerAdapter extends PagerAdapter {
    private List<String> mDataList;
    private List<View> mViewList;
    private int i=0;
    private List<View> mPagerList=new ArrayList<View>();

    /**
     * 每一页显示的个数
     */
    private int pageSize = 10;
    private List<GridViewModel> mDatas = new ArrayList<GridViewModel>();
//    public ExamplePagerAdapter(List<String> dataList) {
//        mDataList = dataList;
//    }
    public ExamplePagerAdapter(List<View> viewList) {
        mViewList = viewList;
    }
    @Override
    public int getCount() {
//        return mDataList == null ? 0 : mDataList.size();
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        TextView textView = new TextView(container.getContext());
//        textView.setText(mDataList.get(position));
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.BLACK);
//        textView.setTextSize(24);
//        container.addView(textView);
//        return textView;
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }




    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        TextView textView = (TextView) object;
        String text = textView.getText().toString();
        int index = mDataList.indexOf(text);
        if (index >= 0) {
            return index+1;
        }
        for (int i = 0; i < 3; i++) {
            return  i;
        }
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataList.get(position);
    }
}
