package com.study.zh.bean;

import java.io.Serializable;

public class TAccountTypeCert implements Serializable {
    private Integer id;

    private String accttype;

    private Integer certid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype;
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accttype=").append(accttype);
        sb.append(", certid=").append(certid);
        sb.append("]");
        return sb.toString();
    }
}