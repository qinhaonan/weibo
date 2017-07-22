package com.wenming.weiswift.app.home.weiboitem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.wenming.weiswift.R;

/**
 * Created by wenmingvs on 16/4/27.
 */
public class HomeHeadView extends RelativeLayout {

    public HomeHeadView(Context context) {
        super(context);
        init(context);
    }
    public HomeHeadView(Context context,int i) {
        super(context);
        initHead(context);
    }

    private void initHead(Context context) {
        inflate(context, R.layout.headview_cropfragment, this);
    }

    public HomeHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.headview_homefragment, this);
    }
}
