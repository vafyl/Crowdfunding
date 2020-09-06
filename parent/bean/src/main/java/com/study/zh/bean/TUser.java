package com.study.zh.bean;



import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

public class TUser implements Serializable {

    @ExcelIgnore
    private Integer id;

    @ExcelProperty(value = "账号",index = 0)
    private String loginacct;

    @ExcelIgnore
    private String userpswd;

    @ExcelProperty(value = "名称",index = 1)
    private String username;
    @ExcelProperty(value = "邮箱",index = 2)
    private String email;
    @ExcelIgnore
    private String createtime;

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginacct=").append(loginacct);
        sb.append(", userpswd=").append(userpswd);
        sb.append(", username=").append(username);
        sb.append(", email=").append(email);
        sb.append(", createtime=").append(createtime);
        sb.append("]");
        return sb.toString();
    }
}