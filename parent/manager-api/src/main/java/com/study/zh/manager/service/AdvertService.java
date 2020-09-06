package com.study.zh.manager.service;


import com.study.zh.bean.Advert;

import com.study.zh.util.Page;
import com.study.zh.vo.Data;

import java.util.Map;

public interface AdvertService {

    public Advert queryAdvert(Map<String, Object> advertMap);

    public Page<Advert> pageQuery(Map<String, Object> advertMap);

    public int queryCount(Map<String, Object> advertMap);

    public int insertAdvert(Advert advert);

    public Advert queryById(Integer id);

    public int updateAdvert(Advert advert);

    public int deleteAdvert(Integer id);

    public int deleteAdverts(Data ds);
}
