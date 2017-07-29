package com.wenming.weiswift.app.common.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by qhn on 2017/7/29.
 */

public class QuestionByIdEntity {

    /**
     * post_id : 8
     * weiba_id : 8
     * post_uid : 5
     * title : 西瓜出现xxx虫害
     * content : 用xxx牌杀虫剂
     * post_time : 1501297289
     * reply_count : 0
     * read_count : 1
     * last_reply_uid : 5
     * last_reply_time : 1501297289
     * digest : 0
     * top : 0
     * lock : 0
     * recommend : 0
     * recommend_time : 0
     * is_del : 0
     * feed_id : 13
     * reply_all_count : 0
     * attach : null
     * author_info : {"uid":"5","login":"zhangsan@zhangsan.cn","login_salt":"45784","uname":"张三","email":"zhangsan@zhangsan.cn","sex":"1","location":"浙江省 绍兴市 新昌县","is_audit":"1","is_active":"1","is_init":"1","ctime":"1501295242","identity":"1","api_key":null,"domain":"","province":"330000","city":"330600","area":"330624","reg_ip":"192.168.1.176","lang":"zh-cn","timezone":"PRC","is_del":"0","first_letter":"Z","intro":null,"last_login_time":"1501295236","last_feed_id":"0","last_post_time":"0","search_key":"张三 zhangsan","invite_code":null,"feed_email_time":null,"send_email_time":null,"avatar_original":"http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original.jpg","avatar_big":"http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original_200_200.jpg?v1501295263","avatar_middle":"http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original_100_100.jpg?v1501295263","avatar_small":"http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original_50_50.jpg?v1501295263","avatar_tiny":"http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original_30_30.jpg?v1501295263","avatar_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Attach&act=avatar&uid=5","space_url":"http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=5","space_link":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=5' target='_blank' uid='5' event-node='face_card'>张三<\/a>","space_link_no":"<a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=5' title='张三' target='_blank'>张三<\/a>","medals":[],"api_user_group":{"1":{"user_group_id":"5","user_group_name":"个人认证","ctime":"1350012209","user_group_icon":"v_01.gif","user_group_type":"0","app_name":"public","is_authenticate":"1","user_group_icon_url":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif"}},"user_group":{"1":{"user_group_id":"5","user_group_name":"个人认证","ctime":"1350012209","user_group_icon":"v_01.gif","user_group_type":"0","app_name":"public","is_authenticate":"1","user_group_icon_url":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif"}},"group_icon":"<img title=\"个人认证\" src=\"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif\" style=\"width:auto;height:auto;display:inline;cursor:pointer;\" />"}
     * favorite : 0
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
    private AuthorInfoBean author_info;
    private int favorite;

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


    public AuthorInfoBean getAuthor_info() {
        return author_info;
    }

    public void setAuthor_info(AuthorInfoBean author_info) {
        this.author_info = author_info;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public static class AuthorInfoBean {
        /**
         * uid : 5
         * login : zhangsan@zhangsan.cn
         * login_salt : 45784
         * uname : 张三
         * email : zhangsan@zhangsan.cn
         * sex : 1
         * location : 浙江省 绍兴市 新昌县
         * is_audit : 1
         * is_active : 1
         * is_init : 1
         * ctime : 1501295242
         * identity : 1
         * api_key : null
         * domain :
         * province : 330000
         * city : 330600
         * area : 330624
         * reg_ip : 192.168.1.176
         * lang : zh-cn
         * timezone : PRC
         * is_del : 0
         * first_letter : Z
         * intro : null
         * last_login_time : 1501295236
         * last_feed_id : 0
         * last_post_time : 0
         * search_key : 张三 zhangsan
         * invite_code : null
         * feed_email_time : null
         * send_email_time : null
         * avatar_original : http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original.jpg
         * avatar_big : http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original_200_200.jpg?v1501295263
         * avatar_middle : http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original_100_100.jpg?v1501295263
         * avatar_small : http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original_50_50.jpg?v1501295263
         * avatar_tiny : http://192.168.1.176/thinksns_v3.0/data/upload/avatar/e4/da/3b/original_30_30.jpg?v1501295263
         * avatar_url : http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Attach&act=avatar&uid=5
         * space_url : http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=5
         * space_link : <a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=5' target='_blank' uid='5' event-node='face_card'>张三</a>
         * space_link_no : <a href='http://192.168.1.176/thinksns_v3.0/index.php?app=public&mod=Profile&act=index&uid=5' title='张三' target='_blank'>张三</a>
         * medals : []
         * api_user_group : {"1":{"user_group_id":"5","user_group_name":"个人认证","ctime":"1350012209","user_group_icon":"v_01.gif","user_group_type":"0","app_name":"public","is_authenticate":"1","user_group_icon_url":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif"}}
         * user_group : {"1":{"user_group_id":"5","user_group_name":"个人认证","ctime":"1350012209","user_group_icon":"v_01.gif","user_group_type":"0","app_name":"public","is_authenticate":"1","user_group_icon_url":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif"}}
         * group_icon : <img title="个人认证" src="http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif" style="width:auto;height:auto;display:inline;cursor:pointer;" />
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
        private Object api_key;
        private String domain;
        private String province;
        private String city;
        private String area;
        private String reg_ip;
        private String lang;
        private String timezone;
        private String is_del;
        private String first_letter;
        private Object intro;
        private String last_login_time;
        private String last_feed_id;
        private String last_post_time;
        private String search_key;
        private Object invite_code;
        private Object feed_email_time;
        private Object send_email_time;
        private String avatar_original;
        private String avatar_big;
        private String avatar_middle;
        private String avatar_small;
        private String avatar_tiny;
        private String avatar_url;
//        private String space_url;
//        private String space_link;
//        private String space_link_no;
//        private ApiUserGroupBean api_user_group;
//        private UserGroupBean user_group;
//        private String group_icon;
//        private List<?> medals;

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

        public Object getApi_key() {
            return api_key;
        }

        public void setApi_key(Object api_key) {
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

        public Object getIntro() {
            return intro;
        }

        public void setIntro(Object intro) {
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

        public Object getInvite_code() {
            return invite_code;
        }

        public void setInvite_code(Object invite_code) {
            this.invite_code = invite_code;
        }

        public Object getFeed_email_time() {
            return feed_email_time;
        }

        public void setFeed_email_time(Object feed_email_time) {
            this.feed_email_time = feed_email_time;
        }

        public Object getSend_email_time() {
            return send_email_time;
        }

        public void setSend_email_time(Object send_email_time) {
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

//        public String getSpace_url() {
//            return space_url;
//        }
//
//        public void setSpace_url(String space_url) {
//            this.space_url = space_url;
//        }
//
//        public String getSpace_link() {
//            return space_link;
//        }
//
//        public void setSpace_link(String space_link) {
//            this.space_link = space_link;
//        }
//
//        public String getSpace_link_no() {
//            return space_link_no;
//        }
//
//        public void setSpace_link_no(String space_link_no) {
//            this.space_link_no = space_link_no;
//        }

//        public ApiUserGroupBean getApi_user_group() {
//            return api_user_group;
//        }
//
//        public void setApi_user_group(ApiUserGroupBean api_user_group) {
//            this.api_user_group = api_user_group;
//        }
//
//        public UserGroupBean getUser_group() {
//            return user_group;
//        }
//
//        public void setUser_group(UserGroupBean user_group) {
//            this.user_group = user_group;
//        }
//
//        public String getGroup_icon() {
//            return group_icon;
//        }
//
//        public void setGroup_icon(String group_icon) {
//            this.group_icon = group_icon;
//        }
//
//        public List<?> getMedals() {
//            return medals;
//        }
//
//        public void setMedals(List<?> medals) {
//            this.medals = medals;
//        }

        public static class ApiUserGroupBean {
            /**
             * 1 : {"user_group_id":"5","user_group_name":"个人认证","ctime":"1350012209","user_group_icon":"v_01.gif","user_group_type":"0","app_name":"public","is_authenticate":"1","user_group_icon_url":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif"}
             */

            @SerializedName("1")
            private _$1Bean _$1;

            public _$1Bean get_$1() {
                return _$1;
            }

            public void set_$1(_$1Bean _$1) {
                this._$1 = _$1;
            }

            public static class _$1Bean {
                /**
                 * user_group_id : 5
                 * user_group_name : 个人认证
                 * ctime : 1350012209
                 * user_group_icon : v_01.gif
                 * user_group_type : 0
                 * app_name : public
                 * is_authenticate : 1
                 * user_group_icon_url : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif
                 */

                private String user_group_id;
                private String user_group_name;
                private String ctime;
                private String user_group_icon;
                private String user_group_type;
                private String app_name;
                private String is_authenticate;
                private String user_group_icon_url;

                public String getUser_group_id() {
                    return user_group_id;
                }

                public void setUser_group_id(String user_group_id) {
                    this.user_group_id = user_group_id;
                }

                public String getUser_group_name() {
                    return user_group_name;
                }

                public void setUser_group_name(String user_group_name) {
                    this.user_group_name = user_group_name;
                }

                public String getCtime() {
                    return ctime;
                }

                public void setCtime(String ctime) {
                    this.ctime = ctime;
                }

                public String getUser_group_icon() {
                    return user_group_icon;
                }

                public void setUser_group_icon(String user_group_icon) {
                    this.user_group_icon = user_group_icon;
                }

                public String getUser_group_type() {
                    return user_group_type;
                }

                public void setUser_group_type(String user_group_type) {
                    this.user_group_type = user_group_type;
                }

                public String getApp_name() {
                    return app_name;
                }

                public void setApp_name(String app_name) {
                    this.app_name = app_name;
                }

                public String getIs_authenticate() {
                    return is_authenticate;
                }

                public void setIs_authenticate(String is_authenticate) {
                    this.is_authenticate = is_authenticate;
                }

                public String getUser_group_icon_url() {
                    return user_group_icon_url;
                }

                public void setUser_group_icon_url(String user_group_icon_url) {
                    this.user_group_icon_url = user_group_icon_url;
                }
            }
        }

        public static class UserGroupBean {
            /**
             * 1 : {"user_group_id":"5","user_group_name":"个人认证","ctime":"1350012209","user_group_icon":"v_01.gif","user_group_type":"0","app_name":"public","is_authenticate":"1","user_group_icon_url":"http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif"}
             */

            @SerializedName("1")
            private _$1BeanX _$1;

            public _$1BeanX get_$1() {
                return _$1;
            }

            public void set_$1(_$1BeanX _$1) {
                this._$1 = _$1;
            }

            public static class _$1BeanX {
                /**
                 * user_group_id : 5
                 * user_group_name : 个人认证
                 * ctime : 1350012209
                 * user_group_icon : v_01.gif
                 * user_group_type : 0
                 * app_name : public
                 * is_authenticate : 1
                 * user_group_icon_url : http://192.168.1.176/thinksns_v3.0/addons/theme/stv1/_static/image/usergroup/v_01.gif
                 */

                private String user_group_id;
                private String user_group_name;
                private String ctime;
                private String user_group_icon;
                private String user_group_type;
                private String app_name;
                private String is_authenticate;
                private String user_group_icon_url;

                public String getUser_group_id() {
                    return user_group_id;
                }

                public void setUser_group_id(String user_group_id) {
                    this.user_group_id = user_group_id;
                }

                public String getUser_group_name() {
                    return user_group_name;
                }

                public void setUser_group_name(String user_group_name) {
                    this.user_group_name = user_group_name;
                }

                public String getCtime() {
                    return ctime;
                }

                public void setCtime(String ctime) {
                    this.ctime = ctime;
                }

                public String getUser_group_icon() {
                    return user_group_icon;
                }

                public void setUser_group_icon(String user_group_icon) {
                    this.user_group_icon = user_group_icon;
                }

                public String getUser_group_type() {
                    return user_group_type;
                }

                public void setUser_group_type(String user_group_type) {
                    this.user_group_type = user_group_type;
                }

                public String getApp_name() {
                    return app_name;
                }

                public void setApp_name(String app_name) {
                    this.app_name = app_name;
                }

                public String getIs_authenticate() {
                    return is_authenticate;
                }

                public void setIs_authenticate(String is_authenticate) {
                    this.is_authenticate = is_authenticate;
                }

                public String getUser_group_icon_url() {
                    return user_group_icon_url;
                }

                public void setUser_group_icon_url(String user_group_icon_url) {
                    this.user_group_icon_url = user_group_icon_url;
                }
            }
        }
    }
}
