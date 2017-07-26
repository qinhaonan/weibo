package com.wenming.weiswift.app.home.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.sf.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.common.entity.CropChannel;
import com.wenming.weiswift.app.common.entity.CropEntity;
import com.wenming.weiswift.app.common.entity.CropTypeEntity;
import com.wenming.weiswift.app.common.entity.Status;
import com.wenming.weiswift.app.home.adapter.CropAdapter;
import com.wenming.weiswift.app.home.fragment.ContentFragment;
import com.wenming.weiswift.app.home.fragment.LeftFragment;
import com.wenming.weiswift.utils.HttpUtil;


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
    private CropTypeEntity cropTypeEntity1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crops_form_layout);
        mContext = this;
        initData2();
        initView();
    }

    private void initView() {
        Button btn_back_crop= (Button) findViewById(R.id.btn_back_crop);
        btn_back_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < cropTypes.size(); i++) {
//            contentFragment = new ContentFragment(mContext,cropTypes.get(i).getChannel_category_id());
            contentFragmentList.add(contentFragment);
        }
//        leftFragment = new LeftFragment(mContext, cropTypes);
        fragmentTransaction.add(R.id.fl_left, leftFragment);
        fragmentTransaction.add(R.id.fl_content, contentFragmentList.get(0));
        fragmentTransaction.commit();
    }
    private void initFragment2() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < cropTypeEntity1.getCropType().size(); i++) {
            contentFragment = new ContentFragment(mContext,cropTypeEntity1.getCropType().get(i).getId());
//            Log.d(TAG, "initFragment2: 种类id"+cropTypeEntity1.getCropType().get(i).getId());
            contentFragmentList.add(contentFragment);
        }
        leftFragment = new LeftFragment(mContext, cropTypeEntity1.getCropType());
        List<TextView> cropTotalView=leftFragment.getCropTotalView();
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
        httpUtils.send(HttpRequest.HttpMethod.POST, "http://192.168.1.176/thinksns_v3.0/index.php?app=api&mod=Channel&act=get_all_channel&oauth_token=553cb8005c5dff47cca58aabefd74de7&oauth_token_secret=4dfa52f77ffe6d55fb1039fe70c70436",
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
                        initFragment2();
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
    public void initData2(){
        HttpUtils httpUtils=new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET,
                "http://192.168.1.176/thinksns_v3.0/index.php?app=api&mod=Weiba&act=get_all_weiba_cate&oauth_token=553cb8005c5dff47cca58aabefd74de7&oauth_token_secret=4dfa52f77ffe6d55fb1039fe70c70436"
                ,null
                , new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
                Log.d("PostService", "onSuccess:  成功" + "{ CropType:"+(String) responseInfo.result+"}");
                Gson gson=new Gson();
                cropTypeEntity1 = gson.fromJson("{ CropType:"+(String)responseInfo.result+"}",CropTypeEntity.class);
                initFragment2();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("PPP", "onFailure:   失败  " + s);
            }
        });
    }

}
