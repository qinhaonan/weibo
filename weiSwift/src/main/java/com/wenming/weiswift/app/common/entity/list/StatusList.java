/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wenming.weiswift.app.common.entity.list;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.wenming.weiswift.app.common.entity.Question;
import com.wenming.weiswift.app.common.entity.Status;
import com.wenming.weiswift.app.common.FillContentHelper;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pl.droidsonroids.gif.transforms.Transform;

/**
 * 微博列表结构。
 *
 * @author SINA
 * @see <a href="http://t.cn/zjM1a2W">常见返回对象数据结构</a>
 * @since 2013-11-22
 */
public class StatusList implements Parcelable {

    private static final String TAG = "StatusList";
    public ArrayList<Status> statuses = new ArrayList<Status>();
    public boolean hasvisible;
    public String previous_cursor;
    public String next_cursor;
    public int total_number;
    public long since_id;
    public long max_id;
    public long has_unread;
    public static Question[] questions;
    public static int i;

    public static StatusList myParse(String origin, String self) {
        i = 0;
        questions = new Gson().fromJson(self, Question[].class);
        StatusList statusList = StatusList.parse(origin);


        for (Status status : statusList.statuses) {
            status.text = questions[i].getFeed_content();
//            //转发字段
//            status.retweeted_status = null;
//            status.created_at=questions[2].getPublish_time();
//            status.id=questions[2].getUid();
//            status.favorited=false;
            status.source="";
            status.user.avatar_hd=questions[i].getAvatar_big();
            status.user.avatar_large=questions[i].getAvatar_middle();
            status.user.name=questions[i].getUname();
//            status.created_at= transform(questions[i].getPublish_time());
            FillContentHelper.setImgUrl(status,questions[i]);
            i++;
            if (i>=questions.length)
               break;
        }
        return statusList;

    }
//    public static String  transform(String time){
//    }

    public static StatusList parse(String jsonString) {

        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        StatusList statuses = new Gson().fromJson(jsonString, StatusList.class);
//        Status.PicUrlsBean pic=new Status.PicUrlsBean();
//        pic.thumbnail_pic="http://192.168.1.176/thinksns_v3.0/data/upload/2017/0706/18/595e0fa9cc4ea.png";
//       对status中的本地私有字段进行赋值
        for (Status status : statuses.statuses) {
            //服务器并没有返回我们单张图片的随机尺寸，这里我们手动需要随机赋值
            FillContentHelper.setSingleImgSizeType(status);
            //提取微博来源的关键字
            FillContentHelper.setSource(status);
            //设置三种类型图片的url地址
//            FillContentHelper.setImgUrl(status);
//            status.pic_urls.add(pic);
            if (status.retweeted_status != null) {
                //服务器并没有返回我们单张图片的随机尺寸，这里我们手动需要随机赋值
                FillContentHelper.setSingleImgSizeType(status.retweeted_status);
                //提取微博来源的关键字
                FillContentHelper.setSource(status.retweeted_status);
                //设置三种类型图片的url地址
                FillContentHelper.setImgUrl(status.retweeted_status);
            }

        }

        return statuses;
    }

    public StatusList() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.statuses);
        dest.writeByte(this.hasvisible ? (byte) 1 : (byte) 0);
        dest.writeString(this.previous_cursor);
        dest.writeString(this.next_cursor);
        dest.writeInt(this.total_number);
        dest.writeLong(this.since_id);
        dest.writeLong(this.max_id);
        dest.writeLong(this.has_unread);
    }

    protected StatusList(Parcel in) {
        this.statuses = in.createTypedArrayList(Status.CREATOR);
        this.hasvisible = in.readByte() != 0;
        this.previous_cursor = in.readString();
        this.next_cursor = in.readString();
        this.total_number = in.readInt();
        this.since_id = in.readLong();
        this.max_id = in.readLong();
        this.has_unread = in.readLong();
    }

    public static final Creator<StatusList> CREATOR = new Creator<StatusList>() {
        @Override
        public StatusList createFromParcel(Parcel source) {
            return new StatusList(source);
        }

        @Override
        public StatusList[] newArray(int size) {
            return new StatusList[size];
        }
    };
}
