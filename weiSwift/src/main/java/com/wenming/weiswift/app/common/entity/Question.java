package com.wenming.weiswift.app.common.entity;

import java.util.List;



public class Question {

    /**
     * feed_id : 9
     * uid : 1
     * type : postimage
     * app : public
     * publish_time : 1499323559
     * is_del : 0
     * from : 0
     * comment_count : 0
     * repost_count : 0
     * comment_all_count : 0
     * digg_count : 0
     * is_repost : 0
     * client_ip : 192.168.1.176
     * feed_content : test多组图
     * ctime : 2017-07-06 14:45
     * content : test多组图
     * uname : 管理员
     * user_group : []
     * avatar_big : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg
     * avatar_middle : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/middle.jpg
     * avatar_small : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/small.jpg
     * has_attach : 1
     * attach : [{"attach_id":"78","attach_name":"595dc32282af1.jpg","attach_url":"http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc8e5c416.jpg","extension":"jpg","size":"2124","attach_small":"http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc8e5c416_100_100.jpg","attach_middle":"http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc8e5c416_550_auto.jpg"},{"attach_id":"79","attach_name":"截图.png","attach_url":"http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc9c21890.png","extension":"png","size":"37340","attach_small":"http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc9c21890_100_100.png","attach_middle":"http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc9c21890_550_auto.png"}]
     * feedType : postimage
     * iscoll : {"colled":0}
     * api_source : null
     * is_digg : 0
     */

    private int feed_id;
    private String uid;
    private String type;
    private String app;
    private String publish_time;
    private String is_del;
    private String from;
    private String comment_count;
    private String repost_count;
    private String comment_all_count;
    private String digg_count;
    private String is_repost;
    private String client_ip;
    private String feed_content;
    private String ctime;
    private String content;
    private String uname;
    private String avatar_big;
    private String avatar_middle;
    private String avatar_small;
    private int has_attach;
    private String feedType;
    private IscollBean iscoll;
    private Object api_source;
    private int is_digg;
    private List<?> user_group;
    private List<AttachBean> attach;

    public int getFeed_id() {
        return feed_id;
    }

    public void setFeed_id(int feed_id) {
        this.feed_id = feed_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getRepost_count() {
        return repost_count;
    }

    public void setRepost_count(String repost_count) {
        this.repost_count = repost_count;
    }

    public String getComment_all_count() {
        return comment_all_count;
    }

    public void setComment_all_count(String comment_all_count) {
        this.comment_all_count = comment_all_count;
    }

    public String getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(String digg_count) {
        this.digg_count = digg_count;
    }

    public String getIs_repost() {
        return is_repost;
    }

    public void setIs_repost(String is_repost) {
        this.is_repost = is_repost;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getFeed_content() {
        return feed_content;
    }

    public void setFeed_content(String feed_content) {
        this.feed_content = feed_content;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAvatar_big() {
        return avatar_big;
    }

    public void setAvatar_big(String avatar_big) {
        this.avatar_big = avatar_big;
    }

    public String getAvatar_middle() {
        return avatar_middle;
    }

    public void setAvatar_middle(String avatar_middle) {
        this.avatar_middle = avatar_middle;
    }

    public String getAvatar_small() {
        return avatar_small;
    }

    public void setAvatar_small(String avatar_small) {
        this.avatar_small = avatar_small;
    }

    public int getHas_attach() {
        return has_attach;
    }

    public void setHas_attach(int has_attach) {
        this.has_attach = has_attach;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public IscollBean getIscoll() {
        return iscoll;
    }

    public void setIscoll(IscollBean iscoll) {
        this.iscoll = iscoll;
    }

    public Object getApi_source() {
        return api_source;
    }

    public void setApi_source(Object api_source) {
        this.api_source = api_source;
    }

    public int getIs_digg() {
        return is_digg;
    }

    public void setIs_digg(int is_digg) {
        this.is_digg = is_digg;
    }

    public List<?> getUser_group() {
        return user_group;
    }

    public void setUser_group(List<?> user_group) {
        this.user_group = user_group;
    }

    public List<AttachBean> getAttach() {
        return attach;
    }

    public void setAttach(List<AttachBean> attach) {
        this.attach = attach;
    }

    public static class IscollBean {
        /**
         * colled : 0
         */

        private int colled;

        public int getColled() {
            return colled;
        }

        public void setColled(int colled) {
            this.colled = colled;
        }
    }

    public static class AttachBean {
        /**
         * attach_id : 78
         * attach_name : 595dc32282af1.jpg
         * attach_url : http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc8e5c416.jpg
         * extension : jpg
         * size : 2124
         * attach_small : http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc8e5c416_100_100.jpg
         * attach_middle : http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/14/595ddc8e5c416_550_auto.jpg
         */

        private String attach_id;
        private String attach_name;
        private String attach_url;
        private String extension;
        private String size;
        private String attach_small;
        private String attach_middle;

        public String getAttach_id() {
            return attach_id;
        }

        public void setAttach_id(String attach_id) {
            this.attach_id = attach_id;
        }

        public String getAttach_name() {
            return attach_name;
        }

        public void setAttach_name(String attach_name) {
            this.attach_name = attach_name;
        }

        public String getAttach_url() {
            return attach_url;
        }

        public void setAttach_url(String attach_url) {
            this.attach_url = attach_url;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getAttach_small() {
            return attach_small;
        }

        public void setAttach_small(String attach_small) {
            this.attach_small = attach_small;
        }

        public String getAttach_middle() {
            return attach_middle;
        }

        public void setAttach_middle(String attach_middle) {
            this.attach_middle = attach_middle;
        }
    }
}
