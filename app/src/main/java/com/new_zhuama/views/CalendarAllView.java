package com.new_zhuama.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.new_zhuama.R;
import com.new_zhuama.adapter.CalendarListAdapter;
import com.new_zhuama.entity.Day;
import com.new_zhuama.entity.Schedule;
import com.new_zhuama.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期控件整合View
 * Created by tongyang on 16/6/30.
 */
public class CalendarAllView extends RelativeLayout implements CalendarView.OnCalendViewClickListener, View.OnClickListener, AdapterView.OnItemClickListener {


    private TextView tv_today//今天
            , tv_sub_year//减一年
            , tv_sub_month//减一个月
            , tv_current_date//当前的时间
            , tv_add_month//加一个月
            , tv_add_year;//加一年

    private View nullview;
    public CalendarView mCalendar;
    private LinearLayout scroll_view;
    private ListView calendar_list;
    private CalendarListAdapter mAdapter;

    private boolean isFristSetHeight = true;
    private int mFristRowY;
    private int mCurrentYear;
    private int mCurrentMonth;
    private int[] mCurrentDate;
    private String mPageType ="";
//            ScheduleManagerActivity.PAGE_NORMAL;
    private List<Schedule> mData;
    private List<Schedule> mAdapterData = new ArrayList<>();

    private OnCalendArAllViewListener mListener;

    public CalendarAllView(Context context) {
        this(context, null, 0);
    }

    public CalendarAllView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CalendarAllView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        String time =
                new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        mCurrentDate = Utils.getDay(time);

    }

    /**
     * 第一次进来设置全部数据
     *
     * @param data
     */
    public void setData(List<Schedule> data) {
        mData = data;
        mCalendar.setData(mData);
    }

    /**
     * 点击日期后查询数据,显示详情
     *
     * @param data
     * @param day
     */
    public void setClickData(List<Schedule> data, Day day) {
        notifyDataChanged(data, day);
    }

    /**
     * 设置日期控件的类型
     *
     * @param mode
     */
    public void setCalendarMode(int mode) {
        mCalendar.setMode(mode);
        mCalendar.postInvalidate();
    }

    /**
     * @param flag
     */
    public void setPageFlag(String flag) {
        mCalendar.setPageFlag(flag);
    }

    public void setCoverVisibility(int visibility) {
        scroll_view.setVisibility(visibility);
    }

    public void closeCover() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(scroll_view, "translationY", scroll_view.getTranslationY(), getResources().getDisplayMetrics().heightPixels);
        animator.setDuration(100);
        animator.start();
        mCalendar.setIsShowDetail(false);
    }

    public int getCurrentYear() {
        return mCurrentYear;
    }

    public int getCurrentMonth() {
        return mCurrentMonth;
    }

    public int[] getDayRowAndColum(int day, int firstdayweek) {
        return mCalendar.getDayRowAndColum(day, firstdayweek);
    }

    public void setFirstRowY(int firstRowY) {
        this.mFristRowY = firstRowY;
    }

    public void setPageType(String pageType) {
        this.mPageType = pageType;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_today = (TextView) findViewById(R.id.tv_today);
        tv_sub_year = (TextView) findViewById(R.id.tv_sub_year);
        tv_sub_month = (TextView) findViewById(R.id.tv_sub_month);
        tv_current_date = (TextView) findViewById(R.id.tv_current_date);
        tv_add_month = (TextView) findViewById(R.id.tv_add_month);
        tv_add_year = (TextView) findViewById(R.id.tv_add_year);
        mCalendar = (CalendarView) findViewById(R.id.calendar);
        scroll_view = (LinearLayout) findViewById(R.id.scroll_view);
        calendar_list = (ListView) findViewById(R.id.calendar_list);
        nullview = findViewById(R.id.nullview);

        String timeStr = new SimpleDateFormat("yyyy年MM月").format(new Date(System.currentTimeMillis()));
        String yearAndMonth = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        final int[] time = Utils.getDay(yearAndMonth);
        mCurrentYear = time[0];
        mCurrentMonth = time[1];
        tv_current_date.setText(timeStr);

        mAdapter = new CalendarListAdapter(getContext(), mAdapterData, mPageType);
        calendar_list.setAdapter(mAdapter);

        scroll_view.setTranslationY(getResources().getDisplayMetrics().heightPixels);

        tv_today.setOnClickListener(this);
        mCalendar.setOnCalendViewClickListener(this);
        tv_sub_year.setOnClickListener(this);
        tv_sub_month.setOnClickListener(this);
        tv_add_month.setOnClickListener(this);
        tv_add_year.setOnClickListener(this);
//        scroll_view.setOnClickListener(this);
        findViewById(R.id.iv_calendar_arrow).setOnClickListener(this);
        findViewById(R.id.no_data).setOnClickListener(this);
        findViewById(R.id.tv_click_add).setOnClickListener(this);

    }

    @Override
    public void onDateClick(final Day value, final int row, final int column, final boolean isKuaYue) {

        if (isFristSetHeight) {
            LayoutParams lp = (LayoutParams) scroll_view.getLayoutParams();
            lp.height = mFristRowY * 5;
            scroll_view.setLayoutParams(lp);
            isFristSetHeight = false;
        }

        if (!mCalendar.getIsShowDetail()) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(scroll_view, "translationY", scroll_view.getTranslationY(), mFristRowY);
            animator.setDuration(300);
            animator.start();

            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (mListener != null) {
                        mListener.onDateCilck(value);
                    }
                    mCalendar.showDetailRow(row, column, value.getDay(), isKuaYue);
                }
            });
        } else {
            if (mListener != null) {
                mListener.onDateCilck(value);
            }
            mCalendar.postInvalidate();
        }

    }


    private void notifyDataChanged(List<Schedule> data, Day value) {
        List<Schedule> filterData = filterData(data, value);
        mAdapter.setClickDay(value);
        mAdapterData.clear();
        mAdapterData.addAll(filterData);
        mAdapter.setPageType(mPageType);
        mAdapter.notifyDataSetChanged();
    }

    private List<Schedule> filterData(List<Schedule> data, Day day) {
        List<Schedule> filterData = new ArrayList<>();

        String time = day.getYear() + "-" + day.getMonth();
        for (int i = 0; i < data.size(); i++) {
            Schedule schedule = data.get(i);
            if (schedule.getTimeSpan().containsKey(time)) {
                Integer[] span = schedule.getTimeSpan().get(time);
                if (rangeInDefined(day.getDay(), span[0], span[1])) {
                    filterData.add(schedule);
                }
            }
        }
        return filterData;
    }

    @Override
    public void onScroll(int year, int month) {
        String monthStr = "";
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = String.valueOf(month);
        }
        mCurrentYear = year;
        mCurrentMonth = month;
        tv_current_date.setText(year + "年" + monthStr + "月");
        if (mListener != null) {
            mListener.onScroll(year, month);
        }
    }

    @Override
    public void onJump(String flag) {
        if (mListener != null)
            mListener.onJump(flag, mCalendar.getClickDay());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_today:
                mCurrentYear = mCurrentDate[0];
                mCurrentMonth = mCurrentDate[1];
                mCalendar.setCurrentMonth(mCurrentYear, mCurrentMonth, mCurrentDate[2]);
                break;
            case R.id.tv_sub_year:
                mCurrentYear = mCurrentYear - 1;
                mCalendar.setCurrentYear(mCurrentYear);
                break;
            case R.id.tv_sub_month:
                mCurrentYear = mCurrentMonth == 1 ? mCurrentYear - 1 : mCurrentYear;
                mCurrentMonth = mCurrentMonth == 1 ? 12 : mCurrentMonth - 1;
                mCalendar.setCurrentMonth(mCurrentYear, mCurrentMonth, 0);
                break;
            case R.id.tv_add_month:
                mCurrentYear = mCurrentMonth == 12 ? mCurrentYear + 1 : mCurrentYear;
                mCurrentMonth = mCurrentMonth == 12 ? 1 : mCurrentMonth + 1;
                mCalendar.setCurrentMonth(mCurrentYear, mCurrentMonth, 0);
                break;
            case R.id.tv_add_year:
                mCurrentYear = mCurrentYear + 1;
                mCalendar.setCurrentYear(mCurrentYear);
                break;
            case R.id.iv_calendar_arrow:
                if (mCalendar.getIsShowDetail()) {
                    ObjectAnimator animator = ObjectAnimator.ofFloat(scroll_view, "translationY", scroll_view.getTranslationY(), getResources().getDisplayMetrics().heightPixels);
                    animator.setDuration(300);
                    animator.start();
                    mCalendar.setIsShowDetail(false);
                }
                break;
            case R.id.tv_click_add:
//                CreateCalendarFragment.show((Activity) getContext(), mCalendar.getClickDay());
                break;
        }
    }

    public boolean rangeInDefined(int current, int min, int max) {
        return Math.max(min, current) == Math.min(current, max);
    }

    public void setOnCalendarAllViewListener(OnCalendArAllViewListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//        Schedule schedule = mAdapterData.get(position);
//        Toaster.shortToast(getContext(), schedule.getTitle());
//        DetailSchedulFragment.show((Activity) getContext(), schedule.getId());
    }


    public interface OnCalendArAllViewListener {
        void onDateCilck(Day value);

        void onScroll(int year, int month);

        void onJump(String flag, Day day);
    }


}
