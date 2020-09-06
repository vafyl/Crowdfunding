package com.study.zh.bean;

import java.io.Serializable;

public class TCheckcode implements Serializable {
    private Integer id;

    private Integer checkcode;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(Integer checkcode) {
        this.checkcode = checkcode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", checkcode=").append(checkcode);
        sb.append("]");
        return sb.toString();
    }
}