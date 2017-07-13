package com.wenming.weiswift.app.home.activity;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.common.base.BaseSwipeActivity;
import com.wenming.weiswift.app.login.fragment.post.PostService;
import com.wenming.weiswift.app.login.fragment.post.idea.IdeaSwipeActivity;
import com.wenming.weiswift.app.login.fragment.post.idea.imagelist.ImgListAdapter;
import com.wenming.weiswift.app.login.fragment.post.picselect.activity.AlbumSwipeActivity;
import com.wenming.weiswift.app.login.fragment.post.picselect.bean.ImageInfo;
import com.wenming.weiswift.widget.mdprogressbar.CircleProgressBar;

import java.util.ArrayList;

/**
 * Created by qhn on 2017/7/12.
 */

public class QuestionActivity extends BaseSwipeActivity implements ImgListAdapter.OnFooterViewClickListener{

    private ImageView imageView;
    private ArrayList<ImageInfo> mSelectImgList = new ArrayList<ImageInfo>();
    public static final String TAG = "QuestionActivity";
    private RecyclerView mRecyclerView;
    private Context mContext;
    private Button btn_commit;
    private EditText edt_question;
    private EditText edt_name;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);
        mContext=this;
        Button btn_back= (Button) findViewById(R.id.btn_back_question);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_question = (EditText) findViewById(R.id.edt_question);
        btn_commit = (Button) findViewById(R.id.btn_commit);
        imageView = (ImageView) findViewById(R.id.ig_question);
        mRecyclerView= (RecyclerView) findViewById(R.id.ImgList);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, AlbumSwipeActivity.class);
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

            }
        });
        edt_question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    imageView.setVisibility(View.INVISIBLE);
                    mSelectImgList = data.getParcelableArrayListExtra("selectImgList");
                    initImgList();
                    changeSendButtonBg();
                }else {
                    imageView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
    public void initImgList() {
        mRecyclerView.setVisibility(View.VISIBLE);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        ImgListAdapter imgListAdapter = new ImgListAdapter(mSelectImgList, mContext,imageView,mRecyclerView);
        imgListAdapter.setOnFooterViewClickListener(this);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(imgListAdapter);
    }
    private void changeSendButtonBg() {
        //如果有文本，或者有图片，或者是处于转发微博状态
        if (edt_name.getText().toString().length() > 0 && edt_question.getText().toString().length()>0 && mSelectImgList.size() > 0 ) {
            highlightSendButton();
        } else {
            normalSendButton();
        }
    }
    private void highlightSendButton() {
        btn_commit.setBackgroundResource(R.drawable.highlight_send_button_bg);
        btn_commit.setTextColor(getResources().getColor(R.color.highlight_send_button_text));
        btn_commit.setEnabled(true);
    }
    private void normalSendButton() {
        btn_commit.setBackgroundResource(R.drawable.normal_send_button_bg);
        btn_commit.setTextColor(getResources().getColor(R.color.normal_send_button_text));
        btn_commit.setEnabled(false);
    }
    @Override
    public void OnFooterViewClick() {
        Intent intent = new Intent(QuestionActivity.this, AlbumSwipeActivity.class);
        intent.putParcelableArrayListExtra("selectedImglist", mSelectImgList);
        startActivityForResult(intent, 0);
    }
    private TextWatcher watcher = new TextWatcher() {
        private CharSequence inputString;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            inputString = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            changeSendButtonBg();
//            setLimitTextColor(mLimitTextView, inputString.toString());

        }
    };
}
