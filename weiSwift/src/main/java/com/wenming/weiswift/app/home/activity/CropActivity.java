package com.wenming.weiswift.app.home.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.common.entity.CropChannel;
import com.wenming.weiswift.app.common.entity.Status;
import com.wenming.weiswift.app.home.adapter.CropAdapter;
import com.wenming.weiswift.app.home.fragment.ContentFragment;
import com.wenming.weiswift.app.home.fragment.LeftFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by qhn on 2017/7/15.
 */

public class CropActivity extends BaseAppCompatActivity {

    private static final String TAG = "CropActivity";
    private Context mContext;
//    private ArrayList<String> dataList;
    private LeftFragment leftFragment;
    private List<ContentFragment> contentFragmentList = new ArrayList<ContentFragment>();
    private ContentFragment contentFragment;
    private FragmentTransaction fragmentTransaction;
    private List<CropChannel.CropType> cropTypes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crops_form_layout);
        mContext = this;
        initData();

    }

    private void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < cropTypes.size(); i++) {
            contentFragment = new ContentFragment(mContext,cropTypes.get(i).getChannel_category_id());
            contentFragmentList.add(contentFragment);

        }
        leftFragment = new LeftFragment(mContext, cropTypes);
        fragmentTransaction.add(R.id.fl_left, leftFragment);
        fragmentTransaction.add(R.id.fl_content, contentFragmentList.get(0));
        fragmentTransaction.commit();
    }

    public void changeFragment(int i,int beforProsition) {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        if (!contentFragmentList.get(i).isAdded()) {
            transaction.add(R.id.fl_content, contentFragmentList.get(i));
        }else if (!contentFragmentList.get(beforProsition).isAdded()){
            transaction.add(R.id.fl_content, contentFragmentList.get(beforProsition));
        }
        transaction.hide(contentFragmentList.get(beforProsition));
        transaction.show(contentFragmentList.get(i));
        transaction.commit();
    }

    public void initData() {
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, "http://192.168.1.176/thinksns_v3.0/index.php?app=api&mod=Channel&act=get_all_channel&oauth_token=553cb8005c5dff47cca58aabefd74de7&oauth_token_secret=4dfa52f77ffe6d55fb1039fe70c70436",
                null, new RequestCallBack<Object>() {
                    @Override
                    public void onSuccess(ResponseInfo<Object> responseInfo) {
                        Log.d("PostService", "onSuccess:  成功" + responseInfo.result);

                        CropChannel cropChannel = new CropChannel();
                        Gson gson=new Gson();
                        Type CropType = new TypeToken<HashMap<String, CropChannel.CropType>>() {}.getType();
                        cropChannel.cropTypeMap = gson.fromJson((String) responseInfo.result, CropType);
                        cropTypes = new ArrayList<CropChannel.CropType>();
                        for (CropChannel.CropType v : cropChannel.cropTypeMap.values()){
                            cropTypes.add(v);
                        }
                        initFragment();
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        Log.d("PPP", "onFailure:   失败  " + s);
                    }
                });
//        RequestUtil.request(httpUtils,"http://192.168.1.176/thinksns_v3.0/index.php?app=api&mod=Channel&act=get_all_channel&oauth_token=553cb8005c5dff47cca58aabefd74de7&oauth_token_secret=4dfa52f77ffe6d55fb1039fe70c70436"

//        dataList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            dataList.add("苹果" + String.valueOf(i));
//        }
    }

}
