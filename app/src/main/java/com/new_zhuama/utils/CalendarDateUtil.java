package com.new_zhuama.utils;

import android.util.Log;

/**
 * Created by zhuama on 16/4/20.
 */
public class CalendarDateUtil {

    public static int getMonthFirstDay(int year,int month) {
        boolean isRun = false;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            isRun = true;
        }
        int startToCurrentSumDay = 0;//从1900到当前日期的总天数
        for (int i = 1900; i < year; i++) {
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
                startToCurrentSumDay += 366;
            else
                startToCurrentSumDay += 365;
        }

        int currentYearMonthDay = 0;//当前年份时,当前过的天数
        for (int i = 1; i <=month ; i++) {
            currentYearMonthDay += getMonthDay(isRun, i);
        }
        int sumDays = startToCurrentSumDay + currentYearMonthDay;

        int weekday = sumDays % 7 ;
//        if (weekday == 7) {
//            weekday = 0;
//        }
        return weekday;

    }

    private static int getMonthDay(boolean isRun, int month) {
        int sum = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                sum = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                sum = 30;
                break;
            case 2:
                sum = isRun ? 29 : 28;
                break;
        }
        return sum;
    }
}
