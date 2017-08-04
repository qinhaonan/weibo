package com.wenming.weiswift.app.common.entity;

import java.util.List;

/**
 * Created by qhn on 2017/8/1.
 */

public class CommentEntity {


    /**
     * reply_id : 2
     * weiba_id : 8
     * post_id : 8
     * post_uid : 5
     * uid : 1
     * to_reply_id : 0
     * to_uid : 0
     * ctime : 1501317761
     * content : 西瓜微吧帖子回复。
     * agree_count : 0
     * oppose_count : 0
     * is_del : 0
     * is_adopt : 0
     * comment_id : 2
     * storey : 1
     * author_info : {"uid":"1","login":"admin@admin.com","login_salt":"11111","uname":"管理员","email":"admin@admin.com","sex":"1","location":"北京市 北京市 海淀区","is_audit":"1","is_active":"1","is_init":"1","ctime":"1500736392","identity":"1","api_key":"","domain":"","province":"110000","city":"110100","area":"110108","reg_ip":"127.0.0.1","lang":"zh-cn","timezone":"PRC","is_del":"0","first_letter":"G","intro":"","last_login_time":"1501335469","last_feed_id":"6","last_post_time":"1501132710","search_key":"管理员 guanliyuan","invite_code":"","feed_email_time":"0","send_email_time":"0","adopt_count":"0","avatar_original":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg","avatar_big":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/big.jpg","avatar_middle":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/middle.jpg","avatar_small":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/small.jpg","avatar_tiny":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/noavatar/tiny.jpg","avatar_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Attach&act=avatar&uid=1","space_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1","space_link":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' target='_blank' uid='1' event-node='face_card'>管理员<\/a>","space_link_no":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=1' title='管理员' target='_blank'>管理员<\/a>","medals":[],"api_user_group":[],"user_group":[],"group_icon":""}
     */

    private String reply_id;
    private String weiba_id;
    private String post_id;
    private String post_uid;
    private String uid;
    private String to_reply_id;
    private String to_uid;
    private String ctime;
    private String content;
    private String agree_count;
    private String oppose_count;
    private String is_del;
    private String is_adopt;
    private String comment_id;
    private int storey;
    private AuthorInfoBean author_info;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {

        return status;
    }

    public String getReply_id() {
        return reply_id;
    }

    public void setReply_id(String reply_id) {
        this.reply_id = reply_id;
    }

    public String getWeiba_id() {
        return weiba_id;
    }

    public void setWeiba_id(String weiba_id) {
        this.weiba_id = weiba_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_uid() {
        return post_uid;
    }

    public void setPost_uid(String post_uid) {
        this.post_uid = post_uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTo_reply_id() {
        return to_reply_id;
    }

    public void setTo_reply_id(String to_reply_id) {
        this.to_reply_id = to_reply_id;
    }

    public String getTo_uid() {
        return to_uid;
    }

    public void setTo_uid(String to_uid) {
        this.to_uid = to_uid;
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

    public String getAgree_count() {
        return agree_count;
    }

    public void setAgree_count(String agree_count) {
        this.agree_count = agree_count;
    }

    public String getOppose_count() {
        return oppose_count;
    }

    public void setOppose_count(String oppose_count) {
        this.oppose_count = oppose_count;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getIs_adopt() {
        return is_adopt;
    }

    public void setIs_adopt(String is_adopt) {
        this.is_adopt = is_adopt;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public int getStorey() {
        return storey;
    }

    public void setStorey(int storey) {
        this.storey = storey;
    }

    public AuthorInfoBean getAuthor_info() {
        return author_info;
    }

    public void setAuthor_info(AuthorInfoBean author_info) {
        this.author_info = author_info;
    }

    public static class AuthorInfoBean {
        /**
         * uid : 1
         * login : admin@admin.com
         * login_salt : 11111
         * uname : 管理员
         * email : admin@admin.com
         * sex : 1
         * location : 北京市 北京市 海淀区
         * is_audit : 1
         * is_active : 1
         * is_init : 1
         * ctime : 1500736392
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
         * last_login_time : 1501335469
         * last_feed_id : 6
         * last_post_time : 1501132710
         * search_key : 管理员 guanliyuan
         * invite_code :
         * feed_email_time : 0
         * send_email_time : 0
         * adopt_count : 0
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
        private String adopt_count;
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
//        private List<?> medals;
//        private List<?> api_user_group;
//        private List<?> user_group;

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

        public String getAdopt_count() {
            return adopt_count;
        }

        public void setAdopt_count(String adopt_count) {
            this.adopt_count = adopt_count;
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

//        public List<?> getMedals() {
//            return medals;
//        }
//
//        public void setMedals(List<?> medals) {
//            this.medals = medals;
//        }
//
//        public List<?> getApi_user_group() {
//            return api_user_group;
//        }
//
//        public void setApi_user_group(List<?> api_user_group) {
//            this.api_user_group = api_user_group;
//        }
//
//        public List<?> getUser_group() {
//            return user_group;
//        }
//
//        public void setUser_group(List<?> user_group) {
//            this.user_group = user_group;
//        }
    }
}
