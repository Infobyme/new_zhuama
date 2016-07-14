package com.new_zhuama.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhuama on 16/6/24.
 */
public class Schedule implements Serializable {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String id;
    private String title;//	日程标题
    private String allday;//	是否全天   1是 0否
    private String startime;//	开始时间
    private String endtime;// 结束时间
    private String address;// 地址
    private String oid;//订单id（如果不为空则为订单事件）

    private HashMap<String, Integer[]> timeSpan;

    private String color;
    private String remark;
    private String remind;
    private String time;
    private String yaddress;
    private List<HashMap<String,String>> scontact;

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public List<HashMap<String, String>> getScontact() {
        return scontact;
    }

    public void setScontact(List<HashMap<String, String>> scontact) {
        this.scontact = scontact;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYaddress() {
        return yaddress;
    }

    public void setYaddress(String yaddress) {
        this.yaddress = yaddress;
    }

    public Schedule() {
    }

    public Schedule(String title, String allday, String startime, String endtime, String address, String oid) {
        this.title = title;
        this.allday = allday;
        this.startime = startime;
        this.endtime = endtime;
        this.address = address;
        this.oid = oid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Integer[]> getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(HashMap<String, Integer[]> timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAllday() {
        return allday;
    }

    public void setAllday(String allday) {
        this.allday = allday;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }


    public String getFormatStartTime() {
        Long time = Long.parseLong(getStartime()) * 1000;
        return sdf.format(time);
    }

    public String getFormatEndTime() {
        Long time = Long.parseLong(getEndtime()) * 1000;
        return sdf.format(time);
    }


}
