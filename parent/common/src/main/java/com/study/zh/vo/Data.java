package com.study.zh.vo;

import com.study.zh.bean.TMemberCert;
import com.study.zh.bean.TUser;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<TUser> userList = new ArrayList<TUser>();
    private List<TUser> datas = new ArrayList<TUser>();

    private List<Integer> ids;

    private List<TMemberCert> certimgs ;

    public List<TMemberCert> getCertimgs() {
        return certimgs;
    }

    public void setCertimgs(List<TMemberCert> certimgs) {
        this.certimgs = certimgs;
    }

    public List<TUser> getUserList() {
        return userList;
    }

    public void setUserList(List<TUser> userList) {
        this.userList = userList;
    }

    public List<TUser> getDatas() {
        return datas;
    }

    public void setDatas(List<TUser> datas) {
        this.datas = datas;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }


}
