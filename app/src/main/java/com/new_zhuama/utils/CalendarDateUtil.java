package com.new_zhuama.utils;


import com.base.entity.Day;

import java.util.Calendar;

/**
 * Created by zhuama on 16/4/20.
 */
public class CalendarDateUtil {

    public static int getMonthFirstDay(Day day) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, day.getYear());
        time.set(Calendar.MONTH, day.getMonth() - 1);
        time.set(Calendar.DATE, 1);
        return time.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isRun(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        }
        return false;
    }

    public static int getMonthDay(boolean isRun, int month) {
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


    public static int getMaxRowForMonth(int firstDay, int sumDay) {
        int maxRow = 0;
        for (int day = 0; day < sumDay; day++) {
            int column = (day + firstDay - 1) % 7;
            int row = (day + firstDay - 1) / 7;
            maxRow = row;
        }
        return maxRow;
    }
}
