package com.study.zh.bean;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class TMemberCert implements Serializable {
    private Integer id;

    private Integer memberid;

    private Integer certid;

    private String iconpath;

    private MultipartFile fileImg;;

    public MultipartFile getFileImg() {
        return fileImg;
    }

    public void setFileImg(MultipartFile fileImg) {
        this.fileImg = fileImg;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    @Override
    public String toString() {
        return "TMemberCert{" +
                "id=" + id +
                ", memberid=" + memberid +
                ", certid=" + certid +
                ", iconpath='" + iconpath + '\'' +
                ", fileImg=" + fileImg +
                '}';
    }
}