package com.mzc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeNow {
    public static String getCreateTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }
}
