package com.newx.staging.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Newx on 2017/10/27.
 */
@Component
public class DateUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter entryTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String localDateFormat(LocalDateTime localDateTime){
        String outputDate = localDateTime.format(formatter);
        return outputDate;
    }

    public static String entryDateFormat(LocalDateTime localDateTime){
        String outputDate = localDateTime.format(entryTime);
        return outputDate;
    }

}
