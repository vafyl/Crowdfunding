package com.study.zh.bean;

import java.io.Serializable;

public class TTicket implements Serializable {
    private Integer id;

    private Integer memberid;

    private String piid;

    /**
    * 0 - 审核中， 1 - 审核完毕
    */
    private String status;

    private String authcode;

    /**
    * accttype-账户类型，basicinfo-基本信息，uploadfile-资质文件上传，checkemail-邮箱确认
    */
    private String pstep;

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

    public String getPiid() {
        return piid;
    }

    public void setPiid(String piid) {
        this.piid = piid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public String getPstep() {
        return pstep;
    }

    public void setPstep(String pstep) {
        this.pstep = pstep;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberid=").append(memberid);
        sb.append(", piid=").append(piid);
        sb.append(", status=").append(status);
        sb.append(", authcode=").append(authcode);
        sb.append(", pstep=").append(pstep);
        sb.append("]");
        return sb.toString();
    }
}