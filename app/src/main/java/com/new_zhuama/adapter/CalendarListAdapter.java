package com.new_zhuama.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.new_zhuama.R;
import com.new_zhuama.entity.Day;
import com.new_zhuama.entity.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuama on 16/7/4.
 */
public class CalendarListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Schedule> mData;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Calendar mCalendar;

    private String mPageType;

//    private int[] drawableResoures = {R.drawable.shape_oval_ffd193,
//            R.drawable.shape_oval_8fcef6,
//            R.drawable.shape_oval_79ecdb,
//            R.drawable.shape_oval_a3b5f3,
//            R.drawable.shape_oval_ffaca0};

    private Day mClickDay;

    public CalendarListAdapter(Context c, List<Schedule> d, String pageType) {
        this.mContext = c;
        this.mData = d;
        mCalendar = Calendar.getInstance();
        this.mPageType = pageType;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Schedule getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendarlist, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Schedule schedule = getItem(position);

        String emoj = "[schedule]";
        String title = schedule.getTitle();

//        if (!StringUtils.isEmpty(schedule.getOid())) {
//
//            title = title + emoj;
//            SpannableString ss = new SpannableString(title);
//            MyImageSpan span = new MyImageSpan(mContext, R.mipmap.ic_order_schedule);
//            if (title.indexOf(emoj) > 0) {
//                ss.setSpan(span, title.indexOf(emoj), title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            holder.tv_schedule_title.setText(ss);
//        } else {
//            holder.tv_schedule_title.setText(title);
//        }


        holder.tv_schedule_address.setText(schedule.getYaddress()+schedule.getAddress());

        try {
            Date startDate = sdf.parse(schedule.getFormatStartTime());
            mCalendar.setTime(startDate);
            int startMonth = mCalendar.get(Calendar.MONTH) + 1;
            int startDay = mCalendar.get(Calendar.DATE);
            int startHour = mCalendar.get(Calendar.HOUR_OF_DAY);
            int startSecond = mCalendar.get(Calendar.MINUTE);
            Date endDate = sdf.parse(schedule.getFormatEndTime());
            mCalendar.setTime(endDate);
            int endtMonth = mCalendar.get(Calendar.MONTH) + 1;
            int endDay = mCalendar.get(Calendar.DATE);
            int endHour = mCalendar.get(Calendar.HOUR_OF_DAY);
            int endSecond = mCalendar.get(Calendar.MINUTE);

            String time = "";
            String startTime = "";
            if (startMonth != endtMonth) {
                time = "00:00-24:00";
                startTime = "00:00";
            } else {
                String startHourStr = startHour < 10 ? "0" + startHour : startHour + "";
                String startSecondStr = startSecond < 10 ? "0" + startSecond : startSecond + "";
                String endHourStr = endHour < 10 ? "0" + endHour : endHour + "";
                String endSecondStr = endSecond < 10 ? "0" + endSecond : endSecond + "";
                if (startDay == endDay) {
                    time = startHourStr + ":" + startSecondStr + "-" + endHourStr + ":" + endSecondStr;
                    startTime = startHourStr + ":" + startSecondStr;
                } else {
                    if (startDay == mClickDay.getDay()) {
                        time = startHourStr + ":" + startSecondStr + "-24:00";
                        startTime = startHourStr + ":" + startSecondStr;
                    } else if (endDay == mClickDay.getDay()) {
                        time = "00:00-" + endHourStr + ":" + endSecondStr;
                        startTime = "00:00";
                    } else {
                        time = "00:00-24:00";
                        startTime = "00:00";
                    }
                }
            }
            holder.btn_schedule_time.setText(startTime);
            holder.tv_schedule_time.setText(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        if (mPageType.equals(ScheduleManagerActivity.PAGE_NORMAL)) {
//            holder.item_root.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DetailSchedulFragment.show((Activity) mContext, schedule.getId());
//                }
//            });
//        }else{
//            holder.item_root.setOnClickListener(null);
//        }
//
//        int random = new Random().nextInt(5);
//        holder.view_schedule_color.setBackgroundResource(drawableResoures[random]);


        return convertView;
    }


    private class ViewHolder {
        public RelativeLayout item_root;
        public Button btn_schedule_time;
        public TextView tv_schedule_title, tv_schedule_address, tv_schedule_time;
        public View view_schedule_color;

        public ViewHolder(View view) {
            btn_schedule_time = (Button) view.findViewById(R.id.btn_schedule_time);
            tv_schedule_title = (TextView) view.findViewById(R.id.tv_schedule_title);
            tv_schedule_address = (TextView) view.findViewById(R.id.tv_schedule_address);
            tv_schedule_time = (TextView) view.findViewById(R.id.tv_schedule_time);
            view_schedule_color = view.findViewById(R.id.view_schedule_color);
            item_root = (RelativeLayout) view.findViewById(R.id.item_root);
        }
    }

    public void setClickDay(Day day) {
        this.mClickDay = day;
    }

    public void setPageType(String pageType){
        this.mPageType=pageType;
    }
}
