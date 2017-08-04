package com.wenming.weiswift.app.common.entity;

import java.util.List;

/**
 * Created by qinhaonan on 2017/8/3.
 */

public class WatchListEntity {

    /**
     * follow_id : 3
     * uid : 6
     * remark :
     * ctime : 1501773600
     * uname : 李五
     * space_url : http://192.168.2.108/ThinkSNS_V3.0/index.php?app=public&mod=Profile&act=index&uid=6
     * follow_state : {"following":1,"follower":0}
     * profile : []
     * avatar_big : http://192.168.2.108/ThinkSNS_V3.0/data/upload/avatar/16/79/09/original_200_200.jpg?v1501767814
     * avatar_middle : http://192.168.2.108/ThinkSNS_V3.0/data/upload/avatar/16/79/09/original_100_100.jpg?v1501767814
     * avatar_small : http://192.168.2.108/ThinkSNS_V3.0/data/upload/avatar/16/79/09/original_50_50.jpg?v1501767814
     * sex : 1
     * intro : null
     * count_info : {"follower_count":1,"new_folower_count":1,"following_count":0,"feed_count":0,"favorite_count":0,"weibo_count":0}
     */

    private String follow_id;
    private String uid;
    private String remark;
    private String ctime;
    private String uname;
    private String space_url;
    private FollowStateBean follow_state;
    private String avatar_big;
    private String avatar_middle;
    private String avatar_small;
    private String sex;
    private Object intro;
    private CountInfoBean count_info;
    private List<?> profile;

    public String getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(String follow_id) {
        this.follow_id = follow_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSpace_url() {
        return space_url;
    }

    public void setSpace_url(String space_url) {
        this.space_url = space_url;
    }

    public FollowStateBean getFollow_state() {
        return follow_state;
    }

    public void setFollow_state(FollowStateBean follow_state) {
        this.follow_state = follow_state;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Object getIntro() {
        return intro;
    }

    public void setIntro(Object intro) {
        this.intro = intro;
    }

    public CountInfoBean getCount_info() {
        return count_info;
    }

    public void setCount_info(CountInfoBean count_info) {
        this.count_info = count_info;
    }

    public List<?> getProfile() {
        return profile;
    }

    public void setProfile(List<?> profile) {
        this.profile = profile;
    }

    public static class FollowStateBean {
        /**
         * following : 1
         * follower : 0
         */

        private int following;
        private int follower;

        public int getFollowing() {
            return following;
        }

        public void setFollowing(int following) {
            this.following = following;
        }

        public int getFollower() {
            return follower;
        }

        public void setFollower(int follower) {
            this.follower = follower;
        }
    }

    public static class CountInfoBean {
        /**
         * follower_count : 1
         * new_folower_count : 1
         * following_count : 0
         * feed_count : 0
         * favorite_count : 0
         * weibo_count : 0
         */

        private int follower_count;
        private int new_folower_count;
        private int following_count;
        private int feed_count;
        private int favorite_count;
        private int weibo_count;

        public int getFollower_count() {
            return follower_count;
        }

        public void setFollower_count(int follower_count) {
            this.follower_count = follower_count;
        }

        public int getNew_folower_count() {
            return new_folower_count;
        }

        public void setNew_folower_count(int new_folower_count) {
            this.new_folower_count = new_folower_count;
        }

        public int getFollowing_count() {
            return following_count;
        }

        public void setFollowing_count(int following_count) {
            this.following_count = following_count;
        }

        public int getFeed_count() {
            return feed_count;
        }

        public void setFeed_count(int feed_count) {
            this.feed_count = feed_count;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public int getWeibo_count() {
            return weibo_count;
        }

        public void setWeibo_count(int weibo_count) {
            this.weibo_count = weibo_count;
        }
    }
}
