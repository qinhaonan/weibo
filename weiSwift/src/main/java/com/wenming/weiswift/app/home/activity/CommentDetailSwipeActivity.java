package com.wenming.weiswift.app.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.app.login.fragment.post.PostService;
import com.wenming.weiswift.app.login.fragment.post.bean.WeiBoCreateBean;
import com.wenming.weiswift.app.login.fragment.post.idea.imagelist.ImgListAdapter;
import com.wenming.weiswift.app.login.fragment.post.picselect.activity.AlbumSwipeActivity;
import com.wenming.weiswift.app.login.fragment.post.picselect.bean.ImageInfo;

import java.util.ArrayList;

import static com.wenming.weiswift.R.id.edt_question;

/**
 * Created by qhn on 2017/7/18.
 */

public class CommentDetailSwipeActivity extends BaseAppCompatActivity implements ImgListAdapter.OnFooterViewClickListener{

    private static final String TAG = "CommentDetailSwipeActivity";
    private EditText edt_text;
    private Button btn_commit;
    private TextView btn_back;
    private ArrayList<ImageInfo> mSelectImgList = new ArrayList<ImageInfo>();
    Boolean isEmptySolution;
    private ImageView img_solution;
    private TextView tv_solution_num;
    private RecyclerView rc_comment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);
        btn_back = (TextView) findViewById(R.id.btn_back_solution);
        btn_commit = (Button) findViewById(R.id.btn_commit_commit_layout);
        edt_text = (EditText) findViewById(R.id.edt_solution);
        img_solution = (ImageView) findViewById(R.id.img_solution);
        tv_solution_num = (TextView) findViewById(R.id.tv_solution_num);
        rc_comment = (RecyclerView) findViewById(R.id.rc_comment);
        initView();
    }

    private void initView() {
        img_solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentDetailSwipeActivity.this, AlbumSwipeActivity.class);
                intent.putParcelableArrayListExtra("selectedImglist", mSelectImgList);
                startActivityForResult(intent, 0);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 提交");
                if (isEmptySolution) {
                    Toast.makeText(mContext,"文字不能为空",Toast.LENGTH_LONG).show();
                }else if(mSelectImgList!=null){
                    Intent intent = new Intent(mContext, PostService.class);
                    Bundle bundle = new Bundle();
                    WeiBoCreateBean weiboBean = new WeiBoCreateBean(edt_text.getText().toString(), mSelectImgList);
                    intent.putExtra("postType", PostService.POST_SERVICE_CREATE_WEIBO);
                    bundle.putParcelable("weiBoCreateBean", weiboBean);
                    intent.putExtras(bundle);
                    startService(intent);
                }
            }
        });
        edt_text.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(140)});
        edt_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0) {
                    isEmptySolution=true;
                }else {
                    isEmptySolution=false;
                }
                tv_solution_num.setText(String.valueOf(s.length())+"/"+ Constants.NUMBER_OF_WORDS);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    img_solution.setVisibility(View.INVISIBLE);
                    mSelectImgList = data.getParcelableArrayListExtra("selectImgList");
                    initImgList();
                }else {
                    img_solution.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
    public void initImgList() {
        rc_comment.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        ImgListAdapter imgListAdapter = new ImgListAdapter(mSelectImgList, mContext,img_solution,rc_comment);
        imgListAdapter.setOnFooterViewClickListener(this);
        rc_comment.setLayoutManager(gridLayoutManager);
        rc_comment.setAdapter(imgListAdapter);
    }

    @Override
    public void OnFooterViewClick() {
        Intent intent = new Intent(CommentDetailSwipeActivity.this, AlbumSwipeActivity.class);
        intent.putParcelableArrayListExtra("selectedImglist", mSelectImgList);
        startActivityForResult(intent, 0);
    }
}
