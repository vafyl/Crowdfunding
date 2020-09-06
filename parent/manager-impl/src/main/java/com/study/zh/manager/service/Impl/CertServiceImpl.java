package com.study.zh.manager.service.Impl;

import java.util.List;
import java.util.Map;

import com.study.zh.bean.TCert;
import com.study.zh.bean.TMemberCert;
import com.study.zh.manager.dao.TCertMapper;
import com.study.zh.manager.service.CertService;
import com.study.zh.util.Page;
import com.study.zh.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private TCertMapper certMapper;

	public TCert queryCert(Map<String, Object> paramMap) {
		return certMapper.queryCert(paramMap);
	}

	public Page<TCert> pageQuery(Map<String, Object> paramMap) {
		Page<TCert> certPage = new Page<TCert>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		paramMap.put("startIndex", certPage.getStartIndex());
		List<TCert> certs = certMapper.pageQuery(paramMap);
		// 获取数据的总条数
		int count = certMapper.queryCount(paramMap);
		certPage.setData(certs);
		certPage.setTotalsize(count);
		return certPage;
	}

	public int queryCount(Map<String, Object> paramMap) {
		return certMapper.queryCount(paramMap);
	}

	public void insertCert(TCert cert) {
		certMapper.insertCert(cert);
	}

	public TCert queryById(Integer id) {
		return certMapper.queryById(id);
	}

	public int updateCert(TCert cert) {
		return certMapper.updateCert(cert);
	}

	public int deleteCert(Integer id) {
		return certMapper.deleteCert(id);
	}

	public int deleteCerts(Data ds) {
		return certMapper.deleteCerts(ds);
	}

	public List<TCert> queryCertByAccttype(String accttype) {
		return certMapper.queryCertByAccttype(accttype);
	}


	public List<TCert> queryAllCert() {
		return certMapper.queryAllCert();
	}


	public List<Map<String, Object>> queryAllAccttypeCert() {
		return certMapper.queryAllAccttypeCert();
	}


	public int insertAccttypeCert(Map<String, Object> map) {
		return certMapper.insertAccttypeCert(map);
	}


	public int deleteAccttypeCert(Map<String, Object> map) {
		return certMapper.deleteAccttypeCert(map);
	}

	public void saveMemberCert(List<TMemberCert> certimgs) {
		for (TMemberCert memberCert:certimgs){
			certMapper.insertMemberCert(memberCert);
		}
	}

}
