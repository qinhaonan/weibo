package com.wenming.weiswift.app.home.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.Question;
import com.wenming.weiswift.app.home.adapter.CropAdapter;

import org.apache.http.params.HttpParams;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.log.LoggerDefault;

/**
 * Created by qhn on 2017/7/15.
 */

public class ContentFragment extends Fragment{
    Context mContext;
//    private List<String> dataList;
    private String mPostion;
    private RecyclerView rcView;

    public  ContentFragment(Context context,String postion){
        mContext=context;
        mPostion=postion;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        init();
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.fl_content_layout, null);
        rcView = (RecyclerView) view.findViewById(R.id.rc_content);
        rcView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//        rcView.setLayoutManager(new LinearLayoutManager(mContext));

        return view;
    }
    private void init(){
        RequestParams params=new RequestParams();
        params.addBodyParameter("app","api");
        params.addBodyParameter("mod","Channel");
        params.addBodyParameter("act","get_channel_feed");
        params.addBodyParameter("oauth_token","553cb8005c5dff47cca58aabefd74de7");
        params.addBodyParameter("oauth_token_secret","4dfa52f77ffe6d55fb1039fe70c70436");
        params.addBodyParameter("category_id",mPostion);
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST,"http://192.168.1.176/thinksns_v3.0/index.php?" ,params, new RequestCallBack<Object>() {
            @Override
            public void onSuccess(ResponseInfo<Object> responseInfo) {
                Log.d("PPPP", "onSuccess: "+"成"+mPostion+responseInfo.result);
                Gson gson=new Gson();
                Question question[]=gson.fromJson((String)responseInfo.result, Question[].class);
                rcView.setAdapter(new CropAdapter(mContext,question));
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("PPPP", "onFailure: "+s);
            }
        });
//        dataList = new ArrayList<>();
//        for (int i = 0; i < 40; i++) {
//            dataList.add("苹果"+String.valueOf(i));
//        }
    }
}
