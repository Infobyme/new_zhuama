package com.new_zhuama.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.new_zhuama.utils.CalendarDateUtil;

import java.util.Calendar;

/**
 * 时间控件
 * Created by tongyang on 16/4/20.
 */
public class CalendarView extends View {

    private static final int MAX_NUM_ROW = 6;
    private static final int MAX_NUM_COLUMN = 7;

    private Paint mTextPaint;
    private Paint mOvalPaint;


    private Calendar mCalendar;
    private DisplayMetrics dm;

    private int mStrokeEnd = 9;
    private int mStrokeStart = 1;

    private int mSpaceCount;
    private int mCurrentRowStart;

    private int mRowSize;
    private int mColumnSize;

    private int mYear;//几年
    private int mMonth;//几月
    private int mDay;//几号
    private int mMonthday;//当月的天数
    private int mWeekDay;//星期几
    private int mMonthFirstDay;//当月第一天星期几


    private int[][] days;
    private String[] weekStrings = new String[]{"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        dm = getResources().getDisplayMetrics();

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(12 * dm.density);

        mOvalPaint = new Paint();
        mOvalPaint.setAntiAlias(true);
        mOvalPaint.setColor(Color.BLUE);

        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mWeekDay = mCalendar.get(Calendar.DAY_OF_WEEK);

        mCalendar.set(Calendar.DATE, 1);
        mCalendar.roll(Calendar.DATE, -1);
        mMonthday = mCalendar.get(Calendar.DATE);

        mMonthFirstDay = CalendarDateUtil.getMonthFirstDay(mYear, mMonth);


//        Log.v("TAG", mYear + " " + mMonth + " " + mDay + " " + mMonthday + " " + mWeekDay + " " +
//                mMonthFirstDay);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initSize();
        days = new int[6][7];
        mCurrentRowStart = mStrokeStart;
//        drawWeek(canvas);
        int dayInt;
        for (int i = 0; i < mMonthday; i++) {
            dayInt=i+1;
            int row = (i + mMonthFirstDay - 1) / 7;
            int nextRow = (i + 1 + mMonthFirstDay - 1) / 7;//下一个数字所在行数
            if (dayInt <= mStrokeEnd && dayInt >= mStrokeStart) {
                mSpaceCount++;
                if (row == nextRow) {
                    if (dayInt == mStrokeEnd) {
                        drawOval(canvas);
                    }
                } else {
                    drawOval(canvas);
                    mCurrentRowStart=dayInt+1;
                    mSpaceCount = 0;
                }
            }
        }

        dayInt=0;
        for (int day = 0; day < mMonthday; day++) {
            dayInt = day + 1;
            int column = (day + mMonthFirstDay - 1) % 7;
            int row = (day + mMonthFirstDay - 1) / 7;
            int nextRow = (day + 1 + mMonthFirstDay - 1) / 7;//下一个数字所在行数

            days[row][column] = day + 1;

            int startX = (int) (mColumnSize * column + (mColumnSize - mTextPaint.measureText(String.valueOf(dayInt))) / 2);
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mTextPaint.ascent() + mTextPaint.descent()) / 2);

            canvas.drawText(String.valueOf(dayInt), startX, startY, mTextPaint);
        }

    }

    private void drawOval(Canvas canvas) {

        int drawColumn = (mCurrentRowStart + mMonthFirstDay - 2) % 7;
        int drawRow = (mCurrentRowStart + mMonthFirstDay - 2) / 7;

        int rectX = mColumnSize * drawColumn;
        int rectY = mRowSize * drawRow;

        int endRectX = rectX + mColumnSize * mSpaceCount;
        int endRectY = rectY + mRowSize;


        RectF rectF = new RectF(rectX, rectY+20, endRectX, endRectY-20);

        canvas.drawRoundRect(rectF, 100, 100, mOvalPaint);
        mSpaceCount = 0;
    }

    private void drawWeek(Canvas canvas) {


        for (int week = 0; week < weekStrings.length; week++) {

            String weekString = weekStrings[week];

            int startX = (int) (mColumnSize * week + (mColumnSize - mTextPaint.measureText(weekString)) / 2);

            int startY = (int) (mRowSize + mRowSize / 2 - (mTextPaint.ascent() + mTextPaint.descent()) / 2);

            canvas.drawText(weekString, startX, startY, mTextPaint);

        }
    }

    private int dwonX, dwonY, upX, upY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                upX = (int) event.getX();
                upY = (int) event.getY();

                doClickAction(upX, upY);
                break;
        }
        return true;

    }

    private void doClickAction(int upX, int upY) {

        int row = upY / mRowSize;
        int column = upX / mColumnSize;

//        Log.v("tag", row + " " + column);
//
//        Log.v("TAG", days[row][column] + "");


    }

    private void initSize() {
        mRowSize = getHeight() / MAX_NUM_ROW;
        mColumnSize = getWidth() / MAX_NUM_COLUMN;
    }
}
