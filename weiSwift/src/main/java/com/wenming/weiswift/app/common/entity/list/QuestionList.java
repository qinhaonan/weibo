package com.wenming.weiswift.app.common.entity.list;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.wenming.weiswift.app.common.entity.Question;
import com.wenming.weiswift.app.common.entity.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qhn on 2017/7/5.
 */



public class QuestionList {
        public ArrayList<Question> questions;


       public static QuestionList QuestionList(String jsonString){
           if (TextUtils.isEmpty(jsonString)) {
               return null;
           }
           Gson gson = new Gson();
           QuestionList questionList=gson.fromJson(jsonString, QuestionList.class);

           return questionList;
    }
}
