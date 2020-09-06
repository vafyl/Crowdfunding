package com.study.zh.bean;

import java.io.Serializable;

public class TMember implements Serializable {
    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    /**
    * 实名认证状态 0 - 未实名认证， 1 - 实名认证申请中， 2 - 已实名认证
    */
    private String authstatus;

    /**
    *  0 - 个人， 1 - 企业
    */
    private String usertype;

    private String realname;

    private String cardnum;

    /**
    * 0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府
    */
    private String accttype;
    private String tel;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct;
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthstatus() {
        return authstatus;
    }

    public void setAuthstatus(String authstatus) {
        this.authstatus = authstatus;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "TMember{" +
                "id=" + id +
                ", loginacct='" + loginacct + '\'' +
                ", userpswd='" + userpswd + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", authstatus='" + authstatus + '\'' +
                ", usertype='" + usertype + '\'' +
                ", realname='" + realname + '\'' +
                ", cardnum='" + cardnum + '\'' +
                ", accttype='" + accttype + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}