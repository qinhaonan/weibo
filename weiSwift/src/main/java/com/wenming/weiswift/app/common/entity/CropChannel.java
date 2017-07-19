package com.wenming.weiswift.app.common.entity;

import com.cesards.cropimageview.CropImageView;
import com.google.gson.annotations.SerializedName;
import com.mob.tools.gui.PullToRequestAdatper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qhn on 2017/7/18.
 */

public class CropChannel {
    public HashMap<String, CropType> cropTypeMap;

    public static class CropType {
        private String channel_category_id;
        private String title;
        private String pid;
        private String sort;
        private Object icon_url;

        public String getChannel_category_id() {
            return channel_category_id;
        }

        public void setChannel_category_id(String channel_category_id) {
            this.channel_category_id = channel_category_id;
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

        public Object getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(Object icon_url) {
            this.icon_url = icon_url;
        }
    }
}
