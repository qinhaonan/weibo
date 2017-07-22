package com.wenming.weiswift.app.common.entity;

import java.util.List;

/**
 * Created by qhn on 2017/7/21.
 */

public class Crop {


        /**
         * weiba_id : 1
         * cid : 1
         * weiba_name : test微吧名称
         * uid : 1
         * ctime : 1498877580
         * logo : 73
         * intro : test微吧简介
         * who_can_post : 0
         * who_can_reply : 0
         * follower_count : 1
         * thread_count : 3
         * admin_uid : 0
         * recommend : 0
         * status : 1
         * is_del : 0
         * notify : null
         * logo_url : http://192.168.1.176/thinksns_v3.0/data/upload/2017/0701/10/59570e76154b7.jpg
         * followstate : 1
         * post_status : {"status":1,"msg":"具有此贴吧的发帖权限"}
         */

        private String weiba_id;
        private String cid;
        private String weiba_name;
        private String uid;
        private String ctime;
        private String logo;
        private String intro;
        private String who_can_post;
        private String who_can_reply;
        private String follower_count;
        private String thread_count;
        private String admin_uid;
        private String recommend;
        private String status;
        private String is_del;
        private Object notify;
        private String logo_url;
        private int followstate;
        private PostStatusBean post_status;

        public String getWeiba_id() {
            return weiba_id;
        }

        public void setWeiba_id(String weiba_id) {
            this.weiba_id = weiba_id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getWeiba_name() {
            return weiba_name;
        }

        public void setWeiba_name(String weiba_name) {
            this.weiba_name = weiba_name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getWho_can_post() {
            return who_can_post;
        }

        public void setWho_can_post(String who_can_post) {
            this.who_can_post = who_can_post;
        }

        public String getWho_can_reply() {
            return who_can_reply;
        }

        public void setWho_can_reply(String who_can_reply) {
            this.who_can_reply = who_can_reply;
        }

        public String getFollower_count() {
            return follower_count;
        }

        public void setFollower_count(String follower_count) {
            this.follower_count = follower_count;
        }

        public String getThread_count() {
            return thread_count;
        }

        public void setThread_count(String thread_count) {
            this.thread_count = thread_count;
        }

        public String getAdmin_uid() {
            return admin_uid;
        }

        public void setAdmin_uid(String admin_uid) {
            this.admin_uid = admin_uid;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public Object getNotify() {
            return notify;
        }

        public void setNotify(Object notify) {
            this.notify = notify;
        }

        public String getLogo_url() {
            return logo_url;
        }

        public void setLogo_url(String logo_url) {
            this.logo_url = logo_url;
        }

        public int getFollowstate() {
            return followstate;
        }

        public void setFollowstate(int followstate) {
            this.followstate = followstate;
        }

        public PostStatusBean getPost_status() {
            return post_status;
        }

        public void setPost_status(PostStatusBean post_status) {
            this.post_status = post_status;
        }

        public  class PostStatusBean {
            /**
             * status : 1
             * msg : 具有此贴吧的发帖权限
             */

            private int status;
            private String msg;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }

}
