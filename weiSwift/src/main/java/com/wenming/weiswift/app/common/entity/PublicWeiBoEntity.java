package com.wenming.weiswift.app.common.entity;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

/**
 * Created by qhn on 2017/7/24.
 */

public class PublicWeiBoEntity {
    public HashMap<String, WeiBo> weiBoMap;
    public class WeiBo{
    /**
     * feed_id : 72
     * uid : 1
     * type : weiba_post
     * app : weiba
     * publish_time : 1500542414
     * is_del : 0
     * from : 0
     * comment_count : 0
     * repost_count : 0
     * comment_all_count : 0
     * digg_count : 0
     * is_repost : 0
     * client_ip : 192.168.1.176
     * feed_content : 【小麦出现了xxx杂草】使用xxx牌除草剂&nbsp;
     * ctime : 2017-07-20 17:20
     * content : 【小麦出现了xxx杂草】使用xxx牌除草剂 
     * uname : 管理员
     * user_group : []
     * avatar_big : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg
     * avatar_middle : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/middle.jpg
     * avatar_small : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/small.jpg
     * has_attach : 0
     * feedType : weiba_post
     * iscoll : {"colled":0}
     * api_source : {"post_id":"6","weiba_id":"3","post_uid":"1","title":"小麦出现了xxx杂草","content":"使用xxx牌除草剂","post_time":"1500542414","reply_count":"0","read_count":"3","last_reply_uid":"1","last_reply_time":"1500542414","digest":"0","top":"0","lock":"0","recommend":"0","recommend_time":"0","is_del":"0","feed_id":"72","reply_all_count":"0","attach":null,"source_user_info":{"uid":"1","login":"admin@admin.com","login_salt":"90296","uname":"管理员","email":"admin@admin.com","sex":"1","location":"北京市 北京市 海淀区","is_audit":"1","is_active":"1","is_init":"1","ctime":"1498469410","identity":"1","api_key":"","domain":"","province":"110000","city":"110100","area":"110108","reg_ip":"127.0.0.1","lang":"zh-cn","timezone":"PRC","is_del":"0","first_letter":"G","intro":"","last_login_time":"1500868274","last_feed_id":"67","last_post_time":"1500458873","search_key":"管理员 guanliyuan","invite_code":"","feed_email_time":"0","send_email_time":"0","avatar_original":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg","avatar_big":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg","avatar_middle":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/middle.jpg","avatar_small":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/small.jpg","avatar_tiny":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/tiny.jpg","avatar_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Attach&act=avatar&uid=1","space_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1","space_link":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' target='_blank' uid='1' event-node='face_card'>管理员<\/a>","space_link_no":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' title='管理员' target='_blank'>管理员<\/a>","medals":[],"api_user_group":[],"user_group":[],"group_icon":""},"source_user":"我","source_type":"PUBLIC_WEIBA","source_title":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' target='_blank' uid='1' event-node='face_card'>管理员<\/a>","source_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=weiba&mod=Index&act=postDetail&post_id=6","ctime":"1500542414","source_content":"【小麦出现了xxx杂草】使用xxx牌除草剂&nbsp;","app_row_table":"weiba_post","app_row_id":"6","source_table":"weiba_post","source_id":"6","groupData":{"1":[]}}
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
    private ApiSourceBean api_source;
    private int is_digg;
    private List<?> user_group;

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

    public ApiSourceBean getApi_source() {
        return api_source;
    }

    public void setApi_source(ApiSourceBean api_source) {
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

    public  class IscollBean {
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

    public class ApiSourceBean {
        /**
         * post_id : 6
         * weiba_id : 3
         * post_uid : 1
         * title : 小麦出现了xxx杂草
         * content : 使用xxx牌除草剂
         * post_time : 1500542414
         * reply_count : 0
         * read_count : 3
         * last_reply_uid : 1
         * last_reply_time : 1500542414
         * digest : 0
         * top : 0
         * lock : 0
         * recommend : 0
         * recommend_time : 0
         * is_del : 0
         * feed_id : 72
         * reply_all_count : 0
         * attach : null
         * source_user_info : {"uid":"1","login":"admin@admin.com","login_salt":"90296","uname":"管理员","email":"admin@admin.com","sex":"1","location":"北京市 北京市 海淀区","is_audit":"1","is_active":"1","is_init":"1","ctime":"1498469410","identity":"1","api_key":"","domain":"","province":"110000","city":"110100","area":"110108","reg_ip":"127.0.0.1","lang":"zh-cn","timezone":"PRC","is_del":"0","first_letter":"G","intro":"","last_login_time":"1500868274","last_feed_id":"67","last_post_time":"1500458873","search_key":"管理员 guanliyuan","invite_code":"","feed_email_time":"0","send_email_time":"0","avatar_original":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg","avatar_big":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg","avatar_middle":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/middle.jpg","avatar_small":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/small.jpg","avatar_tiny":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/tiny.jpg","avatar_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Attach&act=avatar&uid=1","space_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1","space_link":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' target='_blank' uid='1' event-node='face_card'>管理员<\/a>","space_link_no":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' title='管理员' target='_blank'>管理员<\/a>","medals":[],"api_user_group":[],"user_group":[],"group_icon":""}
         * source_user : 我
         * source_type : PUBLIC_WEIBA
         * source_title : <a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' target='_blank' uid='1' event-node='face_card'>管理员</a>
         * source_url : http://192.168.1.176/thinksns_v3.0/index.php?app=weiba&mod=Index&act=postDetail&post_id=6
         * ctime : 1500542414
         * source_content : 【小麦出现了xxx杂草】使用xxx牌除草剂&nbsp;
         * app_row_table : weiba_post
         * app_row_id : 6
         * source_table : weiba_post
         * source_id : 6
         * groupData : {"1":[]}
         */

        private String post_id;
        private String weiba_id;
        private String post_uid;
        private String title;
        private String content;
        private String post_time;
        private String reply_count;
        private String read_count;
        private String last_reply_uid;
        private String last_reply_time;
        private String digest;
        private String top;
        private String lock;
        private String recommend;
        private String recommend_time;
        private String is_del;
        private String feed_id;
        private String reply_all_count;
        private Object attach;
        private SourceUserInfoBean source_user_info;
        private String source_user;
        private String source_type;
        private String source_title;
        private String source_url;
        private String ctime;
        private String source_content;
        private String app_row_table;
        private String app_row_id;
        private String source_table;
        private String source_id;
        private GroupDataBean groupData;

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getWeiba_id() {
            return weiba_id;
        }

        public void setWeiba_id(String weiba_id) {
            this.weiba_id = weiba_id;
        }

        public String getPost_uid() {
            return post_uid;
        }

        public void setPost_uid(String post_uid) {
            this.post_uid = post_uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPost_time() {
            return post_time;
        }

        public void setPost_time(String post_time) {
            this.post_time = post_time;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public String getRead_count() {
            return read_count;
        }

        public void setRead_count(String read_count) {
            this.read_count = read_count;
        }

        public String getLast_reply_uid() {
            return last_reply_uid;
        }

        public void setLast_reply_uid(String last_reply_uid) {
            this.last_reply_uid = last_reply_uid;
        }

        public String getLast_reply_time() {
            return last_reply_time;
        }

        public void setLast_reply_time(String last_reply_time) {
            this.last_reply_time = last_reply_time;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getLock() {
            return lock;
        }

        public void setLock(String lock) {
            this.lock = lock;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getRecommend_time() {
            return recommend_time;
        }

        public void setRecommend_time(String recommend_time) {
            this.recommend_time = recommend_time;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getFeed_id() {
            return feed_id;
        }

        public void setFeed_id(String feed_id) {
            this.feed_id = feed_id;
        }

        public String getReply_all_count() {
            return reply_all_count;
        }

        public void setReply_all_count(String reply_all_count) {
            this.reply_all_count = reply_all_count;
        }

        public Object getAttach() {
            return attach;
        }

        public void setAttach(Object attach) {
            this.attach = attach;
        }

        public SourceUserInfoBean getSource_user_info() {
            return source_user_info;
        }

        public void setSource_user_info(SourceUserInfoBean source_user_info) {
            this.source_user_info = source_user_info;
        }

        public String getSource_user() {
            return source_user;
        }

        public void setSource_user(String source_user) {
            this.source_user = source_user;
        }

        public String getSource_type() {
            return source_type;
        }

        public void setSource_type(String source_type) {
            this.source_type = source_type;
        }

        public String getSource_title() {
            return source_title;
        }

        public void setSource_title(String source_title) {
            this.source_title = source_title;
        }

        public String getSource_url() {
            return source_url;
        }

        public void setSource_url(String source_url) {
            this.source_url = source_url;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getSource_content() {
            return source_content;
        }

        public void setSource_content(String source_content) {
            this.source_content = source_content;
        }

        public String getApp_row_table() {
            return app_row_table;
        }

        public void setApp_row_table(String app_row_table) {
            this.app_row_table = app_row_table;
        }

        public String getApp_row_id() {
            return app_row_id;
        }

        public void setApp_row_id(String app_row_id) {
            this.app_row_id = app_row_id;
        }

        public String getSource_table() {
            return source_table;
        }

        public void setSource_table(String source_table) {
            this.source_table = source_table;
        }

        public String getSource_id() {
            return source_id;
        }

        public void setSource_id(String source_id) {
            this.source_id = source_id;
        }

        public GroupDataBean getGroupData() {
            return groupData;
        }

        public void setGroupData(GroupDataBean groupData) {
            this.groupData = groupData;
        }

        public  class SourceUserInfoBean {
            /**
             * uid : 1
             * login : admin@admin.com
             * login_salt : 90296
             * uname : 管理员
             * email : admin@admin.com
             * sex : 1
             * location : 北京市 北京市 海淀区
             * is_audit : 1
             * is_active : 1
             * is_init : 1
             * ctime : 1498469410
             * identity : 1
             * api_key :
             * domain :
             * province : 110000
             * city : 110100
             * area : 110108
             * reg_ip : 127.0.0.1
             * lang : zh-cn
             * timezone : PRC
             * is_del : 0
             * first_letter : G
             * intro :
             * last_login_time : 1500868274
             * last_feed_id : 67
             * last_post_time : 1500458873
             * search_key : 管理员 guanliyuan
             * invite_code :
             * feed_email_time : 0
             * send_email_time : 0
             * avatar_original : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg
             * avatar_big : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg
             * avatar_middle : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/middle.jpg
             * avatar_small : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/small.jpg
             * avatar_tiny : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/tiny.jpg
             * avatar_url : http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Attach&act=avatar&uid=1
             * space_url : http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1
             * space_link : <a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' target='_blank' uid='1' event-node='face_card'>管理员</a>
             * space_link_no : <a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' title='管理员' target='_blank'>管理员</a>
             * medals : []
             * api_user_group : []
             * user_group : []
             * group_icon :
             */

            private String uid;
            private String login;
            private String login_salt;
            private String uname;
            private String email;
            private String sex;
            private String location;
            private String is_audit;
            private String is_active;
            private String is_init;
            private String ctime;
            private String identity;
            private String api_key;
            private String domain;
            private String province;
            private String city;
            private String area;
            private String reg_ip;
            private String lang;
            private String timezone;
            private String is_del;
            private String first_letter;
            private String intro;
            private String last_login_time;
            private String last_feed_id;
            private String last_post_time;
            private String search_key;
            private String invite_code;
            private String feed_email_time;
            private String send_email_time;
            private String avatar_original;
            private String avatar_big;
            private String avatar_middle;
            private String avatar_small;
            private String avatar_tiny;
            private String avatar_url;
            private String space_url;
            private String space_link;
            private String space_link_no;
            private String group_icon;
            private List<?> medals;
            private List<?> api_user_group;
            private List<?> user_group;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public String getLogin_salt() {
                return login_salt;
            }

            public void setLogin_salt(String login_salt) {
                this.login_salt = login_salt;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getIs_audit() {
                return is_audit;
            }

            public void setIs_audit(String is_audit) {
                this.is_audit = is_audit;
            }

            public String getIs_active() {
                return is_active;
            }

            public void setIs_active(String is_active) {
                this.is_active = is_active;
            }

            public String getIs_init() {
                return is_init;
            }

            public void setIs_init(String is_init) {
                this.is_init = is_init;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public String getApi_key() {
                return api_key;
            }

            public void setApi_key(String api_key) {
                this.api_key = api_key;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getReg_ip() {
                return reg_ip;
            }

            public void setReg_ip(String reg_ip) {
                this.reg_ip = reg_ip;
            }

            public String getLang() {
                return lang;
            }

            public void setLang(String lang) {
                this.lang = lang;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public String getFirst_letter() {
                return first_letter;
            }

            public void setFirst_letter(String first_letter) {
                this.first_letter = first_letter;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getLast_login_time() {
                return last_login_time;
            }

            public void setLast_login_time(String last_login_time) {
                this.last_login_time = last_login_time;
            }

            public String getLast_feed_id() {
                return last_feed_id;
            }

            public void setLast_feed_id(String last_feed_id) {
                this.last_feed_id = last_feed_id;
            }

            public String getLast_post_time() {
                return last_post_time;
            }

            public void setLast_post_time(String last_post_time) {
                this.last_post_time = last_post_time;
            }

            public String getSearch_key() {
                return search_key;
            }

            public void setSearch_key(String search_key) {
                this.search_key = search_key;
            }

            public String getInvite_code() {
                return invite_code;
            }

            public void setInvite_code(String invite_code) {
                this.invite_code = invite_code;
            }

            public String getFeed_email_time() {
                return feed_email_time;
            }

            public void setFeed_email_time(String feed_email_time) {
                this.feed_email_time = feed_email_time;
            }

            public String getSend_email_time() {
                return send_email_time;
            }

            public void setSend_email_time(String send_email_time) {
                this.send_email_time = send_email_time;
            }

            public String getAvatar_original() {
                return avatar_original;
            }

            public void setAvatar_original(String avatar_original) {
                this.avatar_original = avatar_original;
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

            public String getAvatar_tiny() {
                return avatar_tiny;
            }

            public void setAvatar_tiny(String avatar_tiny) {
                this.avatar_tiny = avatar_tiny;
            }

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getSpace_url() {
                return space_url;
            }

            public void setSpace_url(String space_url) {
                this.space_url = space_url;
            }

            public String getSpace_link() {
                return space_link;
            }

            public void setSpace_link(String space_link) {
                this.space_link = space_link;
            }

            public String getSpace_link_no() {
                return space_link_no;
            }

            public void setSpace_link_no(String space_link_no) {
                this.space_link_no = space_link_no;
            }

            public String getGroup_icon() {
                return group_icon;
            }

            public void setGroup_icon(String group_icon) {
                this.group_icon = group_icon;
            }

            public List<?> getMedals() {
                return medals;
            }

            public void setMedals(List<?> medals) {
                this.medals = medals;
            }

            public List<?> getApi_user_group() {
                return api_user_group;
            }

            public void setApi_user_group(List<?> api_user_group) {
                this.api_user_group = api_user_group;
            }

            public List<?> getUser_group() {
                return user_group;
            }

            public void setUser_group(List<?> user_group) {
                this.user_group = user_group;
            }
        }

        public  class GroupDataBean {
            @SerializedName("1")
            private List<?> _$1;

            public List<?> get_$1() {
                return _$1;
            }

            public void set_$1(List<?> _$1) {
                this._$1 = _$1;
            }
        }
    }
    }
}
