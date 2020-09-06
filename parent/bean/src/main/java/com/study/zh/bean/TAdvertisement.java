package com.study.zh.bean;

import java.io.Serializable;

public class TAdvertisement implements Serializable {
    private Integer id;

    private String name;

    private String iconpath;

    /**
     * 0 - 草稿， 1 - 未审核， 2 - 审核完成， 3 - 发布
     */
    private String status;

    private String url;

    private Integer userid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", iconpath=").append(iconpath);
        sb.append(", status=").append(status);
        sb.append(", url=").append(url);
        sb.append(", userid=").append(userid);
        sb.append("]");
        return sb.toString();
    }
}