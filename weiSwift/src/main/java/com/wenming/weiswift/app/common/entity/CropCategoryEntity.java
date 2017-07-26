package com.wenming.weiswift.app.common.entity;

import java.util.List;

/**
 * Created by qhn on 2017/7/26.
 */

public class CropCategoryEntity {

    /**
     * count : 2
     * totalPages : 1
     * totalRows : 2
     * nowPage : 1
     * html : null
     * data : [{"weiba_id":"2","cid":"1","weiba_name":"荞麦","uid":"1","ctime":"1500537298","logo":"http://192.168.1.176/thinksns_v3.0/data/upload/2017/0722/14/5972f0afbaa7d_100_100.png","intro":"水稻简介","who_can_post":"0","who_can_reply":"0","follower_count":"0","thread_count":"2","admin_uid":"0","recommend":"0","status":"1","is_del":"0","notify":"","following":0},{"weiba_id":"3","cid":"1","weiba_name":"小麦","uid":"1","ctime":"1500537397","logo":"http://192.168.1.176/thinksns_v3.0/data/upload/2017/0722/14/5972f04d7e62c_100_100.png","intro":"小麦简介","who_can_post":"0","who_can_reply":"0","follower_count":"0","thread_count":"1","admin_uid":"0","recommend":"0","status":"1","is_del":"0","notify":"","following":0}]
     */

    private String count;
    private int totalPages;
    private String totalRows;
    private int nowPage;
    private Object html;
    private List<DataBean> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public Object getHtml() {
        return html;
    }

    public void setHtml(Object html) {
        this.html = html;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * weiba_id : 2
         * cid : 1
         * weiba_name : 荞麦
         * uid : 1
         * ctime : 1500537298
         * logo : http://192.168.1.176/thinksns_v3.0/data/upload/2017/0722/14/5972f0afbaa7d_100_100.png
         * intro : 水稻简介
         * who_can_post : 0
         * who_can_reply : 0
         * follower_count : 0
         * thread_count : 2
         * admin_uid : 0
         * recommend : 0
         * status : 1
         * is_del : 0
         * notify :
         * following : 0
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
        private String notify;
        private int following;

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

        public String getNotify() {
            return notify;
        }

        public void setNotify(String notify) {
            this.notify = notify;
        }

        public int getFollowing() {
            return following;
        }

        public void setFollowing(int following) {
            this.following = following;
        }
    }
}
