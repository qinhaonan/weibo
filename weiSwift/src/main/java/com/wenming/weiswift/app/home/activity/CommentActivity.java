package com.wenming.weiswift.app.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.login.Constants;
import com.wenming.weiswift.utils.RequestUtil;

/**
 * Created by qhn on 2017/8/1.
 */

public class CommentActivity extends BaseAppCompatActivity {

    private EditText edt_comment;
    private boolean isEmptyQuestion=true;
    private Button btn_commit;
    private String strComment;
    private String strCommentTemp="";
    private TextView tv_comment_num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comment);
        initView();

    }

    private void initView() {
        tv_comment_num = (TextView) findViewById(R.id.tv_comment_num);
        btn_commit = (Button) findViewById(R.id.btn_commit_comment);
        edt_comment = (EditText)findViewById(R.id.edt_comment);
        edt_comment.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(140)});
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strComment.isEmpty()||strComment.length()<10) {
                    Toast.makeText(mContext, "提交内容不能少于10个字", Toast.LENGTH_SHORT).show();
                } else if (strCommentTemp.equals(strComment)){
                    Toast.makeText(mContext, "不能发表内容重复的评论", Toast.LENGTH_SHORT).show();
                }else {
                    submitComment(strComment);
                }
            }
        });
        edt_comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0) {
                    isEmptyQuestion=true;
                }else {
                    isEmptyQuestion=false;
                }
                tv_comment_num.setText(String.valueOf(s.length())+"/"+ Constants.NUMBER_OF_WORDS);
                strComment = s.toString();
            }
        });
    }

    private void submitComment(String comment) {
        strCommentTemp=comment;
        String s=getIntent().getStringExtra("post_id");
        RequestParams params = new RequestParams();
        params.addBodyParameter("app", "api");
        params.addBodyParameter("mod", "Weiba");
        params.addBodyParameter("act", "comment_post");
        params.addBodyParameter("id", getIntent().getStringExtra("post_id"));
        params.addBodyParameter("content", comment);
        params.addBodyParameter("oauth_token", "988b491a22040ef7634eb5b8f52e0986");
        params.addBodyParameter("oauth_token_secret", "2a3d67f5f7bb03035e619518b364912e");
        RequestUtil.requestPost( params, Constants.ZHONGZHIWULIANG_REQUEST_URL, new RequestCallBack() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
            if (((String)responseInfo.result).equals("1")){
                Log.d("qwer", "onSuccess: ");
                Toast.makeText(mContext,"发表评论成功",Toast.LENGTH_SHORT).show();
            }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.d("qwer", "onFailure: ");
                Toast.makeText(mContext,"发表评论失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
