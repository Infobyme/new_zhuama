package com.new_zhuama.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.new_zhuama.R;
import com.base.entity.Day;
import com.base.entity.Schedule;
import com.new_zhuama.utils.CalendarDateUtil;
import com.new_zhuama.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 时间控件
 * Created by tongyang on 16/4/20.
 */
public class CalendarView extends View {

    public static final int CALENDAR_MODE_PRIVATE = 1;
    public static final int CALENDAR_MODE_PUBLIC = 2;
    public static final String PAGE_FLAG = "THIS_MY_PERSONAL";

    private final int MONTH_ADD = 1;//月份+1
    private final int MONTH_SUB = 2;//月份-1

    public static final int MAX_NUM_ROW = 6;
    private static final int MAX_NUM_COLUMN = 7;

    private GestureDetectorCompat mDetector;

    private Paint mTextPaint;
    private Paint mLinePaint;
    private Paint mOvalPaint;
    private Paint mTrigonPaint;


    private Calendar mCalendar;
    private DisplayMetrics dm;


    private int mRowSize;
    private int mColumnSize;
    private int mDefauleMode = CALENDAR_MODE_PUBLIC;
    private String mPageFlag;

    private int mYear;//几年
    private int mMonth;//几月
    private int mDay;//几号
    private int mMonthday;//当月的天数
    private int mWeekDay;
    private int mMonthFirstDay;//当月第一天星期几

    private int[] mCurrentTime;

    private OnCalendViewClickListener mClickListener;
    private boolean isShowDetail = false;//是否在查看详情
    private int isAddOrSub = -1;//1:为相加,2为相减
    private int mFirstRowX;
    private int mMaxRow = 0;

    private int mClickRow;
    private int mClickColumn;
    private Day mClickDay;


    private List<Day> sumDays = new ArrayList<>();
    private int[][] days;

    private List<Schedule> mData;
    private List<Integer> mMarkDay = new ArrayList<>();

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

        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.parseColor("#d6d6d6"));
        mLinePaint.setTextSize(12 * dm.density);

        mTrigonPaint = new Paint();
        mTrigonPaint.setAntiAlias(true);
        mTrigonPaint.setColor(getResources().getColor(R.color.c_FF8308));
        mTrigonPaint.setStyle(Paint.Style.FILL);
        mTrigonPaint.setTextSize(12 * dm.density);

        mOvalPaint = new Paint();
        mOvalPaint.setAntiAlias(true);
        mOvalPaint.setColor(getContext().getResources().getColor(R.color.c_FF8308));

        String time =
//                "2016-8-1";
                new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        mCurrentTime = Utils.getDay(time);
        mYear = mCurrentTime[0];
        mMonth = mCurrentTime[1];
        mDay = mCurrentTime[2];
        mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.DATE, 1);
        mWeekDay = mCalendar.get(Calendar.DAY_OF_WEEK);//日:1

        mMonthday = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(mYear), mMonth);

        mMonthFirstDay = mWeekDay;

        mDetector = new GestureDetectorCompat(getContext(), new MyGestureListener());

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
        drawLine(canvas);
        mMarkDay.clear();
        if (!isShowDetail) {
            drawMonthDay(canvas);
        } else {
            drawWeekDay(canvas);
        }

    }

    /**
     * 绘制整月事件
     *
     * @param canvas
     */
    private void drawMonthDay(Canvas canvas) {
        for (int day = 0; day < mMonthday; day++) {
            mTextPaint.setColor(Color.BLACK);
            int dayInt = day + 1;
            int column = (day + mMonthFirstDay - 1) % 7;
            int row = (day + mMonthFirstDay - 1) / 7;

            mMaxRow = row;
            days[row][column] = day + 1;

            int startX = (int) (mColumnSize * column + (mColumnSize - mTextPaint.measureText(String.valueOf(dayInt))) / 2);
            int startY = (int) (mRowSize * row + mRowSize / 2 - (mTextPaint.ascent() + mTextPaint.descent()) / 2);
            if (mCurrentTime[0] == mYear && mCurrentTime[1] == mMonth && mCurrentTime[2] == dayInt) {
                mTextPaint.setColor(getContext().getResources().getColor(R.color.c_FF8308));
            }
            canvas.drawText(String.valueOf(dayInt), startX, startY, mTextPaint);

            if (mDefauleMode == CALENDAR_MODE_PUBLIC) {
                if (mData != null) {
                    for (int j = 0; j < mData.size(); j++) {
                        Schedule schedule = mData.get(j);
                        if (drawPublicLine(canvas, dayInt, startX, startY, schedule, mYear + "-" + mMonth)) {
                            break;
                        }
                    }
                }
            }
            if (mDefauleMode == CALENDAR_MODE_PRIVATE && mData != null) {
                for (int j = 0; j < mData.size(); j++) {
                    Schedule schedule = mData.get(j);
                    if (drawTrigon(canvas, dayInt, row, column, schedule, mYear + "-" + mMonth)) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * draw 一周的日子和事件
     *
     * @param canvas
     */
    private void drawWeekDay(Canvas canvas) {
        int start = sumDays.get(0).getDay();
        int end = sumDays.get(sumDays.size() - 1).getDay();
        for (int i = 0; i < sumDays.size(); i++) {
            mTextPaint.setColor(Color.BLACK);
            int day = sumDays.get(i).getDay();
            int startX = (int) (mColumnSize * i + (mColumnSize - mTextPaint.measureText(String.valueOf(day))) / 2);
            int startY = (int) (mRowSize * 0 + mRowSize / 2 - (mTextPaint.ascent() + mTextPaint.descent()) / 2);

            //当前时间
            if (mCurrentTime[0] == mYear && mCurrentTime[1] == mMonth && mCurrentTime[2] == day) {
                mTextPaint.setColor(getContext().getResources().getColor(R.color.c_FF8308));
            }
            //当前点击的日期
            if (mClickDay != null) {
                if (sumDays.get(i).getYear() == mClickDay.getYear()
                        && sumDays.get(i).getMonth() == mClickDay.getMonth()
                        && day == mClickDay.getDay()) {
                    mTextPaint.setColor(Color.WHITE);
                    canvas.drawCircle(mColumnSize * i + mColumnSize / 2, mRowSize / 2, 30, mOvalPaint);
                }
            }
            canvas.drawText(String.valueOf(day), startX, startY, mTextPaint);

            if (mMarkDay.contains(day)) {
                continue;
            }
            if (mData != null && mData.size() != 0) {
                for (int j = 0; j < mData.size(); j++) {

                    Schedule schedule = mData.get(j);

                    if (mClickRow == 0) {
                        if (day > 20) {
                            int[] time = subMonth();
                            if (drawTrigon(canvas, day, 0, i, schedule, time[0] + "-" + time[1])) {
                                break;
                            }
                        } else {
                            if (drawTrigon(canvas, day, 0, i, schedule, mYear + "-" + mMonth)) {
                                break;
                            }
                        }
                    } else if (mClickRow == mMaxRow) {
                        if (day > 20) {
                            int[] time = subMonth();
                            if (drawTrigon(canvas, day, 0, i, schedule, time[0] + "-" + time[1])) {
                                break;
                            }
                        } else {
                            if (drawTrigon(canvas, day, 0, i, schedule, mYear + "-" + mMonth)) {
                                break;
                            }
                        }
                    } else {
                        if (start + 6 == end) {
                            if (drawTrigon(canvas, day, 0, i, schedule, mYear + "-" + mMonth)) {
                                break;
                            }
                        } else {
                            int sumDay = 0;
                            if (isAddOrSub == MONTH_ADD) {
                                int[] time = subMonth();
                                sumDay = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(time[0]), time[1]);
                                if (rangeInDefined(day, start, sumDay)) {
                                    if (drawTrigon(canvas, day, 0, i, schedule, time[0] + "-" + time[1])) {
                                        break;
                                    }
                                } else {
                                    if (drawTrigon(canvas, day, 0, i, schedule, mYear + "-" + mMonth)) {
                                        break;
                                    }
                                }
                            } else if (isAddOrSub == MONTH_SUB) {
                                int[] time = addMonth();
                                sumDay = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(time[0]), time[1]);
                                if (day > 20) {
                                    if (drawTrigon(canvas, day, 0, i, schedule, mYear + "-" + mMonth)) {
                                        break;
                                    }
                                } else {
                                    if (drawTrigon(canvas, day, 0, i, schedule, time[0] + "-" + time[1])) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * 减一个月
     *
     * @return
     */
    private int[] subMonth() {
        int year = mYear;
        int month = mMonth;
        year = month - 1 == 0 ? year - 1 : year;
        month = month - 1 == 0 ? 12 : month - 1;
        return new int[]{year, month};
    }

    /**
     * 加一个月
     *
     * @return
     */
    public int[] addMonth() {
        int year = mYear;
        int month = mMonth;
        month = month == 12 ? 1 : month + 1;
        year = month == 12 ? year + 1 : year;
        return new int[]{year, month};
    }


    /**
     * 当天有事情的时候绘制小点
     *
     * @param canvas
     * @param day          当前的天
     * @param row          开始绘制的行
     * @param colum        开始绘制的列
     * @param schedule     对象
     * @param yearAndMonth 所在月份
     */
    private boolean drawTrigon(Canvas canvas, int day, int row, int colum, Schedule schedule, String yearAndMonth) {
        if (schedule.getTimeSpan().containsKey(yearAndMonth)) {
            Integer[] span = schedule.getTimeSpan().get(yearAndMonth);
            if (rangeInDefined(day, span[0], span[1])) {
                mMarkDay.add(day);
                int columSize = mColumnSize * (colum + 1);
                int rowSize = mRowSize * row;

                Path path = new Path();
                path.moveTo(columSize - mColumnSize / 3, rowSize);
                path.lineTo(columSize, rowSize);
                path.lineTo(columSize, rowSize + mRowSize / 4);
                path.close();
                canvas.drawPath(path, mTrigonPaint);
                return true;
            }
        }
        return false;
    }


    /**
     * 画线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        for (int i = 1; i <= MAX_NUM_COLUMN; i++) {
            if (i < MAX_NUM_COLUMN) {
                int columnLine = i * mColumnSize;
                canvas.drawLine(columnLine, 0, columnLine, getHeight(), mLinePaint);
            }
        }
        for (int i = 1; i <= MAX_NUM_ROW; i++) {

            if (i < MAX_NUM_ROW) {
                int rowLine = i * mRowSize;
                if (i == 1) {
                    mFirstRowX = rowLine;
                }
                canvas.drawLine(0, rowLine, getWidth(), rowLine, mLinePaint);
            }
        }
    }

    /**
     * draw 共有的时候的事件线
     *
     * @param canvas
     * @param day
     * @param startX
     * @param startY
     * @param schedule
     * @param yearAndMonth
     * @return
     */
    private boolean drawPublicLine(Canvas canvas, int day, int startX, int startY, Schedule schedule, String yearAndMonth) {
        if (schedule.getTimeSpan().containsKey(yearAndMonth)) {
            Integer[] span = schedule.getTimeSpan().get(yearAndMonth);
            if (rangeInDefined(day, span[0], span[1])) {
                mTextPaint.setColor(getContext().getResources().getColor(R.color.c_FF8308));
                canvas.drawLine(startX, startY + 10, startX + mTextPaint.measureText(day + ""), startY + 10, mTextPaint);
                return true;
            }
        }
        return false;
    }

    private int dwonX, dwonY, upX, upY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    /**
     * 处理点击某日的点击事件
     *
     * @param upX
     * @param upY
     */
    private void doClickAction(int upX, int upY) {
        int row = upY / mRowSize;
        int column = upX / mColumnSize;


        if (mDefauleMode == CALENDAR_MODE_PRIVATE) {

            if (row < MAX_NUM_ROW && column < MAX_NUM_COLUMN) {
                if (!isShowDetail) {
                    int value = days[row][column];
                    if (value != 0) {
                        Day clickDay = new Day();
                        clickDay.setDay(value);
                        clickDay.setYear(mYear);
                        clickDay.setMonth(mMonth);
                        mClickDay = clickDay;
                        if (PAGE_FLAG.equals(mPageFlag)) {
                            mClickListener.onJump(mPageFlag);
                            return;
                        }
                        boolean isKuaYue = false;
                        if (row == mMaxRow) {
                            if (mMonthday - value + column != 6) {
                                isAddOrSub = MONTH_ADD;
                                mYear = mMonth == 12 ? mYear + 1 : mYear;
                                mMonth = mMonth == 12 ? 1 : mMonth + 1;
                                isKuaYue = true;
                            }
                        }
                        sumDays.clear();
                        mClickListener.onDateClick(clickDay, row, column, isKuaYue);
                    }
                } else {
                    mClickDay = sumDays.get(column);
                    mClickListener.onDateClick(mClickDay, row, column, false);
                }
            }
        } else {
            if (row < MAX_NUM_ROW && column < MAX_NUM_COLUMN) {
                int value = days[row][column];
                if (value != 0) {
                    Day clickDay = new Day();
                    clickDay.setDay(value);
                    clickDay.setYear(mYear);
                    clickDay.setMonth(mMonth);
                    mClickDay = clickDay;
                    mClickListener.onJump(mPageFlag);
                }
            }

        }
    }

    /**
     * 点击后显示一行数据
     *
     * @param row
     * @param column
     * @param value
     */
    public void showDetailRow(int row, int column, int value, boolean isKuayue) {
        int[] time = subMonth();
        int sumDay = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(time[0]), time[1]);
        int distance = mMonthday - value;
        isShowDetail = true;
        mClickRow = row;
        mClickColumn = column;
        for (int i = 1; i <= MAX_NUM_COLUMN; i++) {
            int day = i;
            Day dayClazz = new Day();
            if (row == 0) {//当点击第一行的时候
                if (i < mMonthFirstDay) {
                    day = sumDay - mMonthFirstDay + i + 1;
                } else {
                    day = i - (mMonthFirstDay - 1);
                }
            } else if (row == mMaxRow) {//当点击最后一行的时候
                if (i < (column + 1)) {
                    day = value - column + i - 1;
                } else if (i == (column + 1)) {
                    day = value;
                } else {
                    if (i <= (column + 1 + distance)) {
                        day = sumDays.get(i - 2).getDay() + 1;
                    } else {
                        if (sumDays.get(i - 2).getDay() == mMonthday) {
                            day = 1;
                        } else {
                            day = sumDays.get(i - 2).getDay() + 1;
                        }
                    }
                }
            } else {
                if (i <= column) {
                    day = value - column + i - 1;
                } else {
                    day = value + (i - 1) - column;
                }
            }

            dayClazz.setDay(day);
            if (row == mMaxRow) {
                int[] nextTime = addMonth();
                if (day > 20) {
                    dayClazz.setMonth(mMonth);
                    dayClazz.setYear(mYear);
                } else {
                    dayClazz.setMonth(nextTime[1]);
                    dayClazz.setYear(nextTime[0]);
                }

            } else if (row == 0) {
                if (day < 20) {
                    dayClazz.setMonth(mMonth);
                    dayClazz.setYear(mYear);
                } else {
                    int[] beforeTime = subMonth();
                    dayClazz.setMonth(beforeTime[1]);
                    dayClazz.setYear(beforeTime[0]);
                }
            } else {
                dayClazz.setMonth(mMonth);
                dayClazz.setYear(mYear);
            }
            sumDays.add(dayClazz);
        }

        setYearMonthDay(isKuayue);
    }

    /**
     * 初始化控件每行每列的大小
     */
    private void initSize() {
        mRowSize = getHeight() / MAX_NUM_ROW;
        mColumnSize = getWidth() / MAX_NUM_COLUMN;
//        mRowAveSize = (mRowSize - NUM_COLUMN) / 5;
    }


    public void setData(List<Schedule> data) {
        this.mData = data;
        postInvalidate();
    }

    public void setMode(int mode) {
        this.mDefauleMode = mode;
    }

    public void setPageFlag(String flag) {
        this.mPageFlag = flag;
    }

    public void setIsShowDetail(boolean isShow) {
        this.isShowDetail = isShow;
        postInvalidate();
    }

    public boolean getIsShowDetail() {
        return isShowDetail;
    }

    public void setOnCalendViewClickListener(OnCalendViewClickListener listener) {
        this.mClickListener = listener;
    }

    public int getFristRowX() {
        return mFirstRowX;
    }

    public int getCurrentYear() {
        return mYear;
    }

    public int getCurrentMonth() {
        return mMonth;
    }

    public Day getClickDay() {
        return mClickDay;
    }

    public void setClickDay(Day day) {
        this.mClickDay = day;
    }

    /**
     * 设置当前年份
     *
     * @param year
     */
    public void setCurrentYear(int year) {
        if (!isShowDetail) {
            this.mYear = year;
            setYearMonthDay(true);
        } else {
            int lastDay = sumDays.get(sumDays.size() - 1).getDay();
            OperateYearOrMonth(year, 0, lastDay);
        }
    }

    /**
     * 获取某月的最大行数
     *
     * @param firstDay
     * @param sumDay
     */
    private void getMaxRowForMonth(int firstDay, int sumDay) {
        for (int day = 0; day < sumDay; day++) {
            int column = (day + firstDay - 1) % 7;
            int row = (day + firstDay - 1) / 7;
            mMaxRow = row;
        }
    }

    /**
     * 获取某月的第一日是星期几
     *
     * @param year
     * @param month
     * @return
     */
    private int getMonthFirstDayWeek(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);
        time.set(Calendar.DATE, 1);
        return time.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取某日在当前月份中坐所在的行列
     *
     * @param day
     * @param firstdayweek
     * @return
     */
    public int[] getDayRowAndColum(int day, int firstdayweek) {
        int row = (day - 1 + firstdayweek - 1) / 7;
        int column = (day - 1 + firstdayweek - 1) % 7;
        return new int[]{row, column};
    }

    public int getRowSize() {
        return mRowSize;
    }

    /**
     * 设置当前的月份
     *
     * @param year
     * @param month
     */
    public void setCurrentMonth(int year, int month, int day) {
        if (!isShowDetail) {
            this.mYear = year;
            this.mMonth = month;
            setYearMonthDay(true);
        } else {
            int lastDay = 0;
            if (day == 0) {
                lastDay = sumDays.get(sumDays.size() - 1).getDay();
            } else {
                lastDay = day;
            }
            OperateYearOrMonth(year, month, lastDay);
        }
    }

    private void OperateYearOrMonth(int year, int month, int day) {
        this.mYear = year;
        if (month != 0) {
            this.mMonth = month;
        }
        getInitTime();
        int column = ((day - 1) + mMonthFirstDay - 1) % 7;
        int row = ((day - 1) + mMonthFirstDay - 1) / 7;
        sumDays.clear();
        showDetailRow(row, column, day, true);
    }

    /**
     * 看是否在某个范围里
     *
     * @param current
     * @param min
     * @param max
     * @return
     */
    public boolean rangeInDefined(int current, int min, int max) {
        return Math.max(min, current) == Math.min(current, max);
    }

    /**
     * 再次初始化时间
     */
    private void getInitTime() {
        Calendar time = Calendar.getInstance();
        time.set(Calendar.YEAR, mYear);
        time.set(Calendar.MONTH, mMonth - 1);
        time.set(Calendar.DATE, 1);
        mWeekDay = time.get(Calendar.DAY_OF_WEEK);
        mMonthday = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(mYear), mMonth);
        mMonthFirstDay = mWeekDay;
        mMaxRow = CalendarDateUtil.getMaxRowForMonth(mMonthFirstDay, mMonthday);
    }

    private void setYearMonthDay(boolean isKuYue) {
        getInitTime();
        if (mClickListener != null) {
            if (isKuYue) {
                mClickListener.onScroll(mYear, mMonth);
            }
        }
        postInvalidate();
    }


    public interface OnCalendViewClickListener {

        void onDateClick(Day value, int row, int column, boolean isKuaYue);

        void onScroll(int year, int month);

        void onJump(String flag);

    }

    /**
     * 点击事件和滑动事件的处理
     */
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        private int verticalMinDistance = 60;

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            int x = (int) event1.getX();
            int nextX = (int) event2.getX();

            int distance = x - nextX;
            mClickRow = -1;
            if (mClickListener != null && !isShowDetail) {//做整月翻页
                if (distance > 0 && Math.abs(distance) > verticalMinDistance) {//向左滑动 mMonth+1
                    mYear = mMonth == 12 ? mYear + 1 : mYear;
                    mMonth = mMonth == 12 ? 1 : mMonth + 1;
                } else if (distance < 0 && Math.abs(distance) > verticalMinDistance) {//distance<0向右滑动
                    mYear = mMonth - 1 == 0 ? mYear - 1 : mYear;
                    mMonth = mMonth - 1 == 0 ? 12 : mMonth - 1;
                }
                setYearMonthDay(true);
            } else if (mClickListener != null && isShowDetail) {
                Day start = sumDays.get(0);
                Day end = sumDays.get(sumDays.size() - 1);

                if (distance > 0 && Math.abs(distance) > verticalMinDistance) {//向左滑动

                    int endYear = end.getYear();
                    int endMonth = end.getMonth();
                    int endDay = end.getDay();

                    int endSumDay = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(endYear), endMonth);

                    if (endDay + 7 > endSumDay) {
                        isAddOrSub = MONTH_ADD;
                        mYear = endMonth == 12 ? endYear + 1 : endYear;
                        mMonth = endMonth == 12 ? 1 : endMonth + 1;
                        mCalendar.set(Calendar.YEAR, mYear);
                        mCalendar.set(Calendar.MONTH, mMonth - 1);
                        mCalendar.set(Calendar.DATE, 1);
                        mMonthFirstDay = mCalendar.get(Calendar.DAY_OF_WEEK);//日:1
                        int sumDay = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(mYear), mMonth);
                        int[] rowAndColumn = getDayRowAndColum(1, mMonthFirstDay);
                        sumDays.clear();
                        getMaxRowForMonth(mMonthFirstDay, sumDay);
                        showDetailRow(rowAndColumn[0], rowAndColumn[1], 1, true);

                    } else {
                        int nextDay = endDay + 1;
                        int[] rowAndColumn = getDayRowAndColum(nextDay, mMonthFirstDay);
                        sumDays.clear();
                        showDetailRow(rowAndColumn[0], rowAndColumn[1], nextDay, false);
                    }
                } else if (distance < 0 && Math.abs(distance) > verticalMinDistance) {//distance<0向右滑动

                    int startYear = start.getYear();
                    int startMonth = start.getMonth();
                    int startDay = start.getDay();
                    int beforeDay = startDay - 1;

                    int startSumDay = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(startYear), startMonth);

                    if (end.getDay() - 7 > 0) {
                        int week = getMonthFirstDayWeek(end.getYear(), end.getMonth());
                        int[] rowAndColumn = getDayRowAndColum(beforeDay, week);
                        sumDays.clear();
                        showDetailRow(rowAndColumn[0], rowAndColumn[1], beforeDay, true);
                    } else {
                        isAddOrSub = MONTH_SUB;
                        mYear = startMonth == 0 ? end.getYear() - 1 : end.getYear();
                        mMonth = startMonth == 0 ? 12 : end.getMonth() - 1;
                        mCalendar.set(Calendar.YEAR, mYear);
                        mCalendar.set(Calendar.MONTH, mMonth - 1);
                        mCalendar.set(Calendar.DATE, 1);
                        mMonthFirstDay = mCalendar.get(Calendar.DAY_OF_WEEK);//日:1
                        int sumDay = CalendarDateUtil.getMonthDay(CalendarDateUtil.isRun(mYear), mMonth);
                        if (startDay == 1) {
                            beforeDay = sumDay;
                        }
                        int[] rowAndColumn = getDayRowAndColum(beforeDay, mMonthFirstDay);
                        sumDays.clear();
                        getMaxRowForMonth(mMonthFirstDay, sumDay);
                        showDetailRow(rowAndColumn[0], rowAndColumn[1], beforeDay, true);
                    }
                }
            }
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            upX = (int) e.getX();
            upY = (int) e.getY();

            if (mClickListener != null) {
                doClickAction(upX, upY);
            }
            return true;
        }
    }

}
