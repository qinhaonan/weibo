package com.wenming.weiswift.app.mvp.view;

import com.wenming.weiswift.app.common.entity.Comment;
import com.wenming.weiswift.app.common.entity.Status;

import java.util.ArrayList;

/**
 * Created by wenmingvs on 16/5/15.
 */
public interface MentionActivityView {

    public void updateMentionListView(ArrayList<Status> statuselist, boolean resetAdapter);

    public void updateCommentListView(ArrayList<Comment> statuselist, boolean resetAdapter);

    /**
     * 显示loading动画
     */
    public void showLoadingIcon();

    /**
     * 隐藏loadding动画
     */
    public void hideLoadingIcon();

    /**
     * 显示正在加载的FooterView
     */
    public void showLoadFooterView(int currentgroup);

    /**
     * 隐藏FooterView
     */
    public void hideFooterView();

    /**
     * 显示FooterView，提示没有任何内容了
     */
    public void showEndFooterView();

    /**
     * 显示FooterView，提示没有网络
     */
    public void showErrorFooterView();

    /**
     * 滑动到顶部
     */
    public void scrollToTop(boolean refreshData);


}
