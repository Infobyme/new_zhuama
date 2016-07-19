package com.new_zhuama.entity;

import java.io.Serializable;

/**
 * Created by zhuama on 16/7/19.
 */
public class User implements Serializable {

    private String token;//:验证登录信息
    private String upload;//:是否上传过通讯录 1上传过 0没有
    private String uid;//:用户id
    private String face;//:头像
    private String name;//:昵称
    private String type;//:行业
    private String mobile;//:手机号
    private String sex;//:性别 0男 1女
    private String level;//:是否加v   0没有 1加v
    private String email;//:邮箱
    private String rtoken;//:融云token
    private String company;//:公司
    private String post;//:职位
    private String age;//:年龄
    private String address;//:工作地点
    private String qq;//:QQ号
    private String wei;//:微信号
    private String birthday;//:生日
    private String city;//:所在城市
    private String home;//:家乡
    private String sign;//:个性签名
    private String source;//:影响力
    private String xuex;//：血型
    private String honny;//：特长
    private String school;//：学校
    private String constellation;//：星座

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRtoken() {
        return rtoken;
    }

    public void setRtoken(String rtoken) {
        this.rtoken = rtoken;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWei() {
        return wei;
    }

    public void setWei(String wei) {
        this.wei = wei;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getXuex() {
        return xuex;
    }

    public void setXuex(String xuex) {
        this.xuex = xuex;
    }

    public String getHonny() {
        return honny;
    }

    public void setHonny(String honny) {
        this.honny = honny;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }
}
