package com.study.zh.bean;

import java.io.Serializable;

public class TOrder implements Serializable {
    private Integer id;

    private Integer memberid;

    private Integer projectid;

    private Integer returnid;

    private String ordernum;

    private String createdate;

    private Integer money;

    private Integer rtncount;

    /**
     * 0 - 待付款， 1 - 交易完成， 9 - 交易关闭
     */
    private String status;

    private String address;

    /**
     * 0 - 不开发票， 1 - 开发票
     */
    private String invoice;

    private String invoictitle;

    private String remark;

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

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getReturnid() {
        return returnid;
    }

    public void setReturnid(Integer returnid) {
        this.returnid = returnid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getRtncount() {
        return rtncount;
    }

    public void setRtncount(Integer rtncount) {
        this.rtncount = rtncount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoictitle() {
        return invoictitle;
    }

    public void setInvoictitle(String invoictitle) {
        this.invoictitle = invoictitle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberid=").append(memberid);
        sb.append(", projectid=").append(projectid);
        sb.append(", returnid=").append(returnid);
        sb.append(", ordernum=").append(ordernum);
        sb.append(", createdate=").append(createdate);
        sb.append(", money=").append(money);
        sb.append(", rtncount=").append(rtncount);
        sb.append(", status=").append(status);
        sb.append(", address=").append(address);
        sb.append(", invoice=").append(invoice);
        sb.append(", invoictitle=").append(invoictitle);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}