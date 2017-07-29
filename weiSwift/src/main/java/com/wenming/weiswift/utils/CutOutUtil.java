package com.wenming.weiswift.utils;

/**
 * Created by qhn on 2017/7/27.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CutOutUtil {
    public static List<String> getImgSrc(String htmlStr) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<String>();
//       String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            img = img + "," + m_image.group();
            // Matcher m =
            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add("http://192.168.1.176"+m.group(1));
            }
        }
        return pics;
    }
    public static String getText(String str){
        String patternString = "<[^>]+>";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        String resultString =  matcher.replaceAll("");
        return resultString;
    }
    public static String getContent(String str){
        String content=str.substring((str.indexOf("】"))+1,str.length());
        content=content.replaceAll("&nbsp;","");
        return content;
    }
}
