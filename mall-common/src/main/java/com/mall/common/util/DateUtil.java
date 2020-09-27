package com.mall.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtil
 *
 * @Author BessCroft
 * @Date 2020/9/27 14:37
 */
public class DateUtil {
    public static Date JSToJava(String timeStr) {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
        Date parse = null;
        try {
            Date dateTime = SDF.parse(timeStr);
//            System.out.println(dateTime);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            parse = simpleDateFormat.parse(dateTime.toString());
//            System.out.println(parse);
            return dateTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
