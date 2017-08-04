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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wenming.weiswift.app.common.entity.Question;
import com.wenming.weiswift.app.common.entity.QuestionEntity;
import com.wenming.weiswift.app.common.entity.Status;
import com.wenming.weiswift.app.common.FillContentHelper;
import com.wenming.weiswift.utils.CutOutUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public static QuestionEntity questionEntity;
    public static int i;

    public static StatusList myParse(String origin, String self) {
        i = 0;
//        questions = new Gson().fromJson(self, Question[].class);
        JsonParser jsonParser=new JsonParser();
        JsonArray jsonArray=jsonParser.parse(self).getAsJsonArray();
        Gson gson=new Gson();
        ArrayList<QuestionEntity> questionList=new ArrayList<>();
        for (JsonElement question:jsonArray){
            QuestionEntity questionEntity=gson.fromJson(question,QuestionEntity.class);
            questionList.add(questionEntity);
        }
//        questionEntity=new Gson().fromJson(self,QuestionEntity.class);
        StatusList statusList = StatusList.parse(origin);


        for (Status status : statusList.statuses) {
            if(questionList!=null&&questionList.size()>0) {
                    List<String> imgUrl = CutOutUtil.getImgSrc(questionList.get(i).getApi_source().getContent());

                status.text = CutOutUtil.getContent(questionList.get(i).getFeed_content());
//            //转发字段
//            status.retweeted_status = null;
//            status.created_at=questions[2].getPublish_time();
//            status.id=questions[2].getUid();
//            status.favorited=false;
                status.source = questionList.get(i).getApi_source().getTitle();
                status.user.location=questionList.get(i).getApi_source().getSource_user_info().getLocation();
                status.comments_count=Integer.valueOf(questionList.get(i).getComment_count());
                status.user.avatar_hd = questionList.get(i).getAvatar_big();
                status.user.avatar_large = questionList.get(i).getAvatar_middle();
                status.user.name = questionList.get(i).getUname();
                status.id=questionList.get(i).getApi_source().getPost_id();
//                status.favorited=questionList
//            status.created_at= transform(questions[i].getPublish_time());
//                FillContentHelper.setImgUrl(status, questionList);
                FillContentHelper.setImgUrl(status, imgUrl);
                i++;
                if (i >= questionList.size())
                    break;
            }
        }
        return statusList;

    }
    public static String  transform(String time){

        String date = timeStamp2Date(time+"000", "yyyy-MM-dd HH:mm:ss");
//        String date = timeStamp2Date(time+"000", "EEE MMM d HH:mm:ss Z yyyy");
        return date;
    }
/**
 6      * 时间戳转换成日期格式字符串
 7      * @param seconds 精确到秒的字符串
 8      * @param formatStr
 9      * @return
 10      */
     public static String timeStamp2Date(String seconds,String format) {
                 if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
                         return "";
                     }
                 if(format == null || format.isEmpty()){
                         format = "yyyy-MM-dd HH:mm:ss";
                     }
                 SimpleDateFormat sdf = new SimpleDateFormat(format);
                 return sdf.format(new Date(Long.valueOf(seconds)));
             }

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
