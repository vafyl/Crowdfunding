package com.study.zh.manager.service;

import com.study.zh.bean.TCert;
import com.study.zh.bean.TMemberCert;
import com.study.zh.util.Page;
import com.study.zh.vo.Data;

import java.util.List;
import java.util.Map;




public interface CertService {
	public TCert queryCert(Map<String, Object> paramMap);

	public Page<TCert> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void insertCert(TCert cert);

	public TCert queryById(Integer id);

	public int updateCert(TCert cert);

	public int deleteCert(Integer id);

	public int deleteCerts(Data ds);

	public List<TCert> queryCertByAccttype(String accttype);

	public List<TCert> queryAllCert();

	public List<Map<String, Object>> queryAllAccttypeCert();

	public int insertAccttypeCert(Map<String, Object> map);

	public int deleteAccttypeCert(Map<String, Object> map);

	void saveMemberCert(List<TMemberCert> certimgs);
}
