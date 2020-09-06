package com.study.zh.bean;

import java.io.Serializable;

public class TReturn implements Serializable {
    private Integer id;

    private Integer projectid;

    /**
     * 0 - 实物回报， 1 虚拟物品回报
     */
    private String type;

    private Integer supportmoney;

    private String content;

    /**
     * “0”为不限回报数量
     */
    private Integer count;

    private Integer signalpurchase;

    private Integer purchase;

    private Integer freight;

    /**
     * 0 - 不开发票， 1 - 开发票
     */
    private String invoice;

    private Integer rtndate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSupportmoney() {
        return supportmoney;
    }

    public void setSupportmoney(Integer supportmoney) {
        this.supportmoney = supportmoney;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSignalpurchase() {
        return signalpurchase;
    }

    public void setSignalpurchase(Integer signalpurchase) {
        this.signalpurchase = signalpurchase;
    }

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Integer getRtndate() {
        return rtndate;
    }

    public void setRtndate(Integer rtndate) {
        this.rtndate = rtndate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectid=").append(projectid);
        sb.append(", type=").append(type);
        sb.append(", supportmoney=").append(supportmoney);
        sb.append(", content=").append(content);
        sb.append(", count=").append(count);
        sb.append(", signalpurchase=").append(signalpurchase);
        sb.append(", purchase=").append(purchase);
        sb.append(", freight=").append(freight);
        sb.append(", invoice=").append(invoice);
        sb.append(", rtndate=").append(rtndate);
        sb.append("]");
        return sb.toString();
    }
}