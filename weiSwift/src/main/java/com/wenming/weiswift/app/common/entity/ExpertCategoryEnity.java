package com.wenming.weiswift.app.common.entity;

import java.util.List;

/**
 * Created by qhn on 2017/7/27.
 */

public class ExpertCategoryEnity {

    private List<CategoryBean> category;

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class CategoryBean {
        /**
         * user_official_category_id : 1
         * title : 植保系统专家
         * pid : 0
         * sort : 1
         */

        private String user_official_category_id;
        private String title;
        private String pid;
        private String sort;

        public String getUser_official_category_id() {
            return user_official_category_id;
        }

        public void setUser_official_category_id(String user_official_category_id) {
            this.user_official_category_id = user_official_category_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
